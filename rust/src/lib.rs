use crate::model::enums::Satzart::Satzart;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::saetze::Auftrag::Auftrag;
use crate::model::saetze::Befund::Befund;
use crate::model::saetze::LaborDatenpaketAbschluss::LaborDatenpaketAbschluss;
use crate::model::saetze::LaborDatenpaketHeader::LaborDatenpaketHeader;
use crate::model::saetze::PraxisDatenpaketAbschluss::PraxisDatenpaketAbschluss;
use crate::model::saetze::PraxisDatenpaketHeader::PraxisDatenpaketHeader;
use crate::model::saetze::Satz::Satz;
use crate::model::Kontext::Kontext;
use std::fs::File;
use std::io::{BufRead, BufReader};
use log::debug;
use log::warn;
use log::error;
use model::regel::Regel::Regel;
use substring::Substring;
#[macro_use]
extern crate libldt3_macros;

pub mod model;


/// Determines how the reader deals with invalid LDT */
enum Mode {
    /// In case invalid LDT is detected, the reader will abort with an exception
    STRICT,
    /// When the reader encounters invalid LDT, it will log as error and continue
    RELAXED
}

fn read(path: &str, mode: Mode) -> Vec<dyn Satz> {
    let file = File::open(path).unwrap();
    let reader = BufReader::new(file);

    let stack = Vec::new();
    let data = Vec::new();
    for (lineNo, line) in reader.lines().enumerate() {
        let line = line.unwrap();
        handle_input(&line, &stack, &data, lineNo);
    }

    return data;
}

fn handle_input(line: &String, stack: &Vec<dyn Kontext>, data: &Vec<dyn Satz>, line_no: i32, mode: Mode) -> Result<i32, i32> {
    debug!("Reading line {}", line);

    // Check if the line meets the minimum requirements (3 digits for
    // length, 4 digits for the identifier)
    if line.len() < 7 {
        match mode {
            Mode::STRICT => {
                return Err(format!("IllegalArgumentException: Line '{}' was less than 7 characters, aborting", line));
            },
            Mode::RELAXED => {
                error!("Line '{}' was less than 7 characters, continuing anyway", line);
            }
        }
    }

    // Read the length and check whether it had the correct length
    let length = line[0..3].parse::<usize>().unwrap();
    if length != (line.len() + 2) {
        match mode {
            Mode::STRICT => {
                return Err(format!("IllegalArgumentException: Line '{}' should have length {}, but was {}",
                        line, (line.len() + 2), length));
            },
            Mode::RELAXED => {
                warn!("Line '{}' should have length {}, but was {}. Ignoring specified length", line,
                        (line.len() + 2), length);
                length = line.len() + 2;
            }
        }
    }

    // Read identifier and payload
    let identifier = line[3..7].to_string();
    let payload = line.substring(7, length - 2);

    match identifier.as_str() {
    "8000" => {
        // Start: Satz
        assure_length(line, length, 13, mode);
        if !stack.empty {
            match mode {
                Mode::STRICT => {
                    return Err(format!("IllegalStateException: Stack must be empty when starting a new Satz, but was {} long", stack.size()));
                },
                Mode::RELAXED => {
                    error!("Stack must be empty when starting a new Satz, but was {}. Clearing and continuing", stack);
                    stack.clear();
                }
            }
        }

        // Extract Satzart from payload and create Satz matching it
        let satzart = get_satzart(payload);
        match satzart {
        Satzart::BEFUND => stack.push(Befund::new()),
        Satzart::AUFTRAG => stack.push(Auftrag::new()),
        Satzart::LABOR_DATENPAKET_HEADER => stack.push(LaborDatenpaketHeader::new()),
        Satzart::LABOR_DATENPAKET_ABSCHLUSS => stack.push(LaborDatenpaketAbschluss::new()),
        Satzart::PRAXIS_DATENPAKET_HEADER => stack.push(PraxisDatenpaketHeader::new()),
        Satzart::PRAXIS_DATENPAKET_ABSCHLUSS => stack.push(PraxisDatenpaketAbschluss::new()),
        default => return Err(format!("IllegalArgumentException: Unsupported Satzart '{}' found", payload))
        }
        return;
    },
    "8001" => {
        // End: Satz
        assure_length(line, length, 13, mode);
        let o = stack.pop();
        let annotation = o.getClass().getAnnotation(Datenpaket.class);
        if annotation.is_some() {
            evaluate_context_rules(o, annotation.kontextregeln(), mode);
        }
        if stack.isEmpty() {
            data.push(o);
        }
        return;
    },
    "8002" => {
        // Start: Objekt
        assure_length(line, length, 17, mode);
        let current_object = peek_current_object(stack);
        let annotation = current_object.getClass().getAnnotation(Objekt.class);
        if annotation.is_some() {
            if annotation.value().isEmpty() {
                // If annotation is empty, the parent object would actually
                // be the one to deal with
            } else {
                // Match found, everything is fine
                if payload.equals("Obj_" + annotation.value()) {
                    return;
                }

                // No match found, abort or inform the developer
                if mode == Mode::STRICT {
                    return Err(format!("IllegalArgumentException: In line '{}' ({}) expected Obj_{}, got {}", line, line_no, annotation.value(), payload));
                } else {
                    error!("In line '{}' ({}) expected Obj_{}, got {}", line, line_no, annotation.value(), payload);
                    return;
                }
            }
        }
        if mode == Mode::STRICT {
            return Err(format!("IllegalArgumentException: Line '{}' started an unexpeted object", line));
        } else {
            warn!("Line '{}' started an unexpeted object, stack was {}", line, stack);
        }
    },
    "8003" => {
        // End: Objekt
        assure_length(line, length, 17, mode);
        let o;
        let annotation;
        while {
            o = stack.pop();
            annotation = o.objekt();
            if annotation.is_some() {
                if !annotation.value().isEmpty() && !("Obj_" + annotation.value()).equals(payload) {
                    warn!("Line: {} ({}), annotation {}, payload {}", line, line_no, annotation.value(), payload);
                }
                evaluate_context_rules(o, annotation.kontextregeln(), mode);
            }
            if !(annotation.is_some() && annotation.value().isEmpty()) {
                return;
            }
        } {}
        if stack.isEmpty() {
            data.push(o);
        }
        return;
    },
    default => {
        // Any line not starting or completing a Satz or Objekt
        let current_object = peek_current_object(stack);
        if current_object.is_some() {
            return Err(format!("IllegalStateException: No object when applying line {}", line));
        }
        // XXX iterating the fields could be replaced by a map to be a bit
        // faster when dealing with the same class
        for field in current_object.getClass().getDeclaredFields() {

            // Check if we found a Feld annotation, if not this is not our
            // field
            let annotation = field.getAnnotation(Feld.class);
            if annotation.isNone() {
                continue;
            }

            // Check if the annotation matches the identifier, if not, this
            // is not our field
            if !identifier.equals(annotation.value()) {
                continue;
            }

            // Field found, make it accessible and set the value
            field.setAccessible(true);
            try {
                // Check if there is currently a value set
                let object = field.get(current_object);
                if (object != null && !List.class.isAssignableFrom(field.getType())) {
                    if mode == Mode::STRICT {
                        return Err(format!("IllegalStateException: Line '{}' would overwrite existing value {}", line, object));
                    } else {
                        warn!("Line '{}' would overwrite existing value {}", line, object);
                    }
                }

                validate_field_payload(field, payload);

                // Convert the value to its target type ...
                let value = convert_type(field, field.getType(), payload, stack);

                // .. and set the value on the target object
                field.set(current_object, value);
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException
                    | InvocationTargetException | InstantiationException e) {
                if mode == Mode::STRICT {
                    throw new IllegalStateException(e);
                } else {
                    LOG.error(e.getMessage(), e);
                }
            }
            // We are done with this line
            return;
        }

        // No field with a matching Feld annotation found, check if we are
        // an Objekt with an empty value (anonymous object), if so try our
        // parent
        let annotation = current_object.getClass().getAnnotation(Objekt.class);
        if annotation.is_some() && annotation.value().isEmpty() {
            stack.pop();
            handle_input(line, stack, data, line_no, mode);
            return;
        }

        // Neither we nor our parent could deal with this line
        if mode == Mode::STRICT {
            return Err(format!("IllegalArgumentException: Failed reading line {} ({}), current stack: {}, skipping line", line, line_no, stack));
        } else {
            warn!("Failed reading line {} ({}), current stack: {}, skipping line", line, line_no, stack);
        }
        return;
    }
    }
}

fn evaluate_context_rules(o: Object, kontextRegeln: Vec<Class<Kontextregel>>, mode: Mode) {
    for kontextregel in kontextRegeln {
        try {
            if (!kontextregel.newInstance().isValid(o)) {
                if mode == Mode::STRICT {
                    return Err(format!("IllegalArgumentException: Context rule {} failed on object {}", kontextregel.getSimpleName(), o));
                } else {
                    warn!("Context rule {} failed on object {}", kontextregel.getSimpleName(), o);
                }
            }
        } catch (IllegalAccessException | InstantiationException e) {
            if mode == Mode::STRICT {
                return Err(format!("IllegalArgumentException: Context rule {} failed on object {}", kontextregel.getSimpleName(), o));
            } else {
                warn!("Context rule {} failed on object {}", kontextregel.getSimpleName(), o);
            }
        }
    }
}

fn validate_field_payload(field: Field, payload: String, mode: Mode) -> void {
    /*outer:*/ for regelsatz in field.regelsatz()  {

        if regelsatz.laenge() >= 0 {
            if payload.length() != regelsatz.laenge() {
                validation_failed(format!("{}.{}: Value {} did not match expected length {}, was {}",
                    field.getDeclaringClass().getSimpleName(), field.getName(), payload, regelsatz.laenge(), payload.length()), mode);
            }
        }

        if regelsatz.minLaenge() >= 0 {
            if payload.length() < regelsatz.minLaenge() {
                validation_failed(format!("{}.{}: Value {} did not match expected minimum length {}, was {}",
                    field.getDeclaringClass().getSimpleName(), field.getName(), payload, regelsatz.minLaenge(), payload.length()), mode);
            }
        }

        if regelsatz.maxLaenge() >= 0 {
            if payload.length() > regelsatz.maxLaenge() {
                validation_failed(format!("{}.{}: Value {} did not match expected maximum length {}, was {}",
                    field.getDeclaringClass().getSimpleName(), field.getName(), payload, regelsatz.maxLaenge(), payload.length()), mode);
            }
        }

        // No specific rules given, likely only length checks
        if regelsatz.value().length == 0 {
            continue;
        }

        for regel in regelsatz.value() {
            if get_regel(regel).isValid(payload) {
                continue outer;
            }
        }
        validation_failed(format!("{}.{}: Value {} did not confirm to any rule of {}",
            field.getDeclaringClass().getSimpleName(), field.getName(), payload, toString(regelsatz.value())), mode);
    }
}

fn validation_failed(message: String, mode: Mode) -> Result<String, String> {
    if mode == Mode::STRICT {
        return Err(format!("IllegalStateException: {}", message));
    } else {
        warn!("{}", message);
    }
}

fn get_regel(regel: Class<Regel> ) -> dyn Regel {
    let instance;
    /*let instance = regelCache.get(regel);*/
    /*if (instance == null) {*/
        instance = regel::new();
        /*regelCache.put(regel, instance);*/
    /* } */
    return instance;
}

/// Extract the Satzart form a given payload
///
/// @param payload the payload of the line
/// @return the Satzart or {@code null}
fn get_satzart(payload: &str) -> Option<Satzart<'static>> {
    for sa in Satzart::iter() {
        if sa.code.equals(payload) {
            return sa;
        }
    }
    return None;
}

/**
 * Peek the current objekt from the stack, if any.
 * 
 * @param stack the stack to peek the object from
 * @return the current top level element of the stack or {@code null}
 */
fn peek_current_object(stack: Vec<dyn Kontext>) -> Option<dyn Kontext> {
    return stack.last();
}

/// Check if the line matches the expected length.
///
/// @param line   the line to check
/// @param length the actual length
/// @param target the length specified by the line
fn assure_length(line: &str, length: usize, target: usize, mode: Mode) -> Result<String, String> {
    if length != target {
        if mode == Mode::STRICT {
            return Err(format!("IllegalArgumentException: Line '{}' must have length {}, was {}", line, target, length));
        } else {
            warn!("Line '{}' must have length {}, was {}", line, target, length);
        }
    }
    return Ok("")
}

#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        let result = 2 + 2;
        assert_eq!(result, 4);
    }
}
