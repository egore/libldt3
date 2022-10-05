/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package libldt3;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import libldt3.annotations.Regelsatz;
import libldt3.model.regel.Regel;
import libldt3.model.regel.kontext.Kontextregel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import libldt3.LdtConstants.Mode;
import libldt3.annotations.Datenpaket;
import libldt3.annotations.Feld;
import libldt3.annotations.Objekt;
import libldt3.model.Kontext;
import libldt3.model.enums.Satzart;
import libldt3.model.saetze.Auftrag;
import libldt3.model.saetze.Befund;
import libldt3.model.saetze.LaborDatenpaketAbschluss;
import libldt3.model.saetze.LaborDatenpaketHeader;
import libldt3.model.saetze.PraxisDatenpaketAbschluss;
import libldt3.model.saetze.PraxisDatenpaketHeader;
import libldt3.model.saetze.Satz;

/**
 * Simple, reflection and annotation based reader for LDT 3.0.
 * 
 * @author Christoph Brill &lt;opensource@christophbrill.de&gt;
 */
public class LdtReader {

    private static final Logger LOG = LoggerFactory.getLogger(LdtReader.class);

    private final Map<Class<? extends Regel>, Regel> regelCache = new HashMap<>();

    private final Mode mode;

    public LdtReader(Mode mode) {
        this.mode = mode;
    }

    /**
     * Read the LDT found on a given path.
     * 
     * @param path the path of the LDT file (any format handled by NIO {@link Path})
     * @return the list of Satz elements found in the LDT file
     * @throws IOException thrown if reading the file failed
     */
    public List<Satz> read(String path) throws IOException {
        return read(Paths.get(path));
    }

    /**
     * Read the LDT found on a given path.
     * 
     * @param path the path of the LDT file
     * @return the list of Satz elements found in the LDT file
     * @throws IOException thrown if reading the file failed
     */
    public List<Satz> read(Path path) throws IOException {
        try (Stream<String> stream = Files.lines(path, StandardCharsets.ISO_8859_1)) {
            return read(stream);
        }
    }

    /**
     * Read the LDT from a given string stream.
     * 
     * @param stream the LDT lines as string stream
     * @return the list of Satz elements found in the LDT file
     */
    public List<Satz> read(Stream<String> stream) {
        Stack<Kontext> stack = new Stack<>();
        List<Satz> data = new ArrayList<>();
        AtomicInteger integer = new AtomicInteger();
        stream.forEach(line -> handleInput(line, stack, data, integer.incrementAndGet()));
        return data;
    }

    private void handleInput(String line, Stack<Kontext> stack, List<Satz> data, int lineNo) {
        LOG.trace("Reading line {}", line);

        // Check if the line meets the minimum requirements (3 digits for
        // length, 4 digits for the identifier)
        if (line.length() < 7) {
            if (mode == Mode.STRICT) {
                throw new IllegalArgumentException("Line '" + line + "' was less than 7 characters, aborting");
            } else {
                LOG.error("Line '{}' was less than 7 characters, continuing anyway", line);
            }
        }

        // Read the length and check whether it had the correct length
        int length = Integer.parseInt(line.substring(0, 3));
        if (length != line.length() + 2) {
            if (mode == Mode.STRICT) {
                throw new IllegalArgumentException(
                        "Line '" + line + "' should have length " + (line.length() + 2) + ", but was " + length);
            } else {
                LOG.warn("Line '{}' should have length {}, but was {}. Ignoring specified length", line,
                        (line.length() + 2), length);
                length = line.length() + 2;
            }
        }

        // Read identifier and payload
        String identifier = line.substring(3, 7);
        String payload = line.substring(7, length - 2);

        switch (identifier) {
        case "8000": {
            // Start: Satz
            assureLength(line, length, 13);
            if (!stack.isEmpty()) {
                if (mode == Mode.STRICT) {
                    throw new IllegalStateException(
                            "Stack must be empty when starting a new Satz, but was " + stack.size() + " long");
                } else {
                    LOG.error("Stack must be empty when starting a new Satz, but was {}. Clearing and continuing",
                            stack);
                    stack.clear();
                }
            }

            // Extract Satzart from payload and create Satz matching it
            Satzart satzart = getSatzart(payload);
            switch (satzart) {
            case Befund:
                stack.push(new Befund());
                break;
            case Auftrag:
                stack.push(new Auftrag());
                break;
            case LaborDatenpaketHeader:
                stack.push(new LaborDatenpaketHeader());
                break;
            case LaborDatenpaketAbschluss:
                stack.push(new LaborDatenpaketAbschluss());
                break;
            case PraxisDatenpaketHeader:
                stack.push(new PraxisDatenpaketHeader());
                break;
            case PraxisDatenpaketAbschluss:
                stack.push(new PraxisDatenpaketAbschluss());
                break;
            default:
                throw new IllegalArgumentException("Unsupported Satzart '" + payload + "' found");
            }
            break;
        }
        case "8001": {
            // End: Satz
            assureLength(line, length, 13);
            Object o = stack.pop();
            Datenpaket annotation = o.getClass().getAnnotation(Datenpaket.class);
            if (annotation != null) {
                evaluateContextRules(o, annotation.kontextregeln());
            }
            if (stack.isEmpty()) {
                data.add((Satz) o);
            }
            break;
        }
        case "8002": {
            // Start: Objekt
            assureLength(line, length, 17);
            Kontext currentObject = peekCurrentObject(stack);
            Objekt annotation = currentObject.getClass().getAnnotation(Objekt.class);
            if (annotation != null) {
                if (annotation.value().isEmpty()) {
                    // If annotation is empty, the parent object would actually
                    // be the one to deal with
                } else {
                    // Match found, everything is fine
                    if (payload.equals("Obj_" + annotation.value())) {
                        break;
                    }

                    // No match found, abort or inform the developer
                    if (mode == Mode.STRICT) {
                        throw new IllegalArgumentException("In line '" + line + "' (" + lineNo + ") expected Obj_"
                                + annotation.value() + ", got " + payload);
                    } else {
                        LOG.error("In line '{}' ({}) expected Obj_{}, got {}", line, lineNo, annotation.value(),
                                payload);
                        break;
                    }
                }
            }
            if (mode == Mode.STRICT) {
                throw new IllegalArgumentException("Line '" + line + "' started an unexpeted object");
            } else {
                LOG.warn("Line '{}' started an unexpeted object, stack was {}", line, stack);
            }
            break;
        }
        case "8003": {
            // End: Objekt
            assureLength(line, length, 17);
            Object o;
            Objekt annotation;
            do {
                o = stack.pop();
                annotation = o.getClass().getAnnotation(Objekt.class);
                if (annotation != null) {
                    if (!annotation.value().isEmpty() && !("Obj_" + annotation.value()).equals(payload)) {
                        LOG.warn("Line: {} ({}), annotation {}, payload {}", line, lineNo, annotation.value(), payload);
                    }
                    evaluateContextRules(o, annotation.kontextregeln());
                }
            } while (annotation != null && annotation.value().isEmpty());
            if (stack.isEmpty()) {
                data.add((Satz) o);
            }
            break;
        }
        default:
            // Any line not starting or completing a Satz or Objekt
            Kontext currentObject = peekCurrentObject(stack);
            if (currentObject == null) {
                throw new IllegalStateException("No object when applying line " + line);
            }
            // XXX iterating the fields could be replaced by a map to be a bit
            // faster when dealing with the same class
            for (Field field : currentObject.getClass().getDeclaredFields()) {

                // Check if we found a Feld annotation, if not this is not our
                // field
                Feld annotation = field.getAnnotation(Feld.class);
                if (annotation == null) {
                    continue;
                }

                // Check if the annotation matches the identifier, if not, this
                // is not our field
                if (!identifier.equals(annotation.value())) {
                    continue;
                }

                // Field found, make it accessible and set the value
                field.setAccessible(true);
                try {
                    // Check if there is currently a value set
                    Object object = field.get(currentObject);
                    if (object != null && !List.class.isAssignableFrom(field.getType())) {
                        if (mode == Mode.STRICT) {
                            throw new IllegalStateException(
                                    "Line '" + line + "' would overwrite existing value " + object);
                        } else {
                            LOG.warn("Line '{}' would overwrite existing value {}", line, object);
                        }
                    }

                    validateFieldPayload(field, payload);

                    // Convert the value to its target type ...
                    Object value = convertType(field, field.getType(), payload, stack);

                    // .. and set the value on the target object
                    field.set(currentObject, value);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException
                        | InvocationTargetException | InstantiationException e) {
                    if (mode == Mode.STRICT) {
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
            Objekt annotation = currentObject.getClass().getAnnotation(Objekt.class);
            if (annotation != null && annotation.value().isEmpty()) {
                stack.pop();
                handleInput(line, stack, data, lineNo);
                return;
            }

            // Neither we nor our parent could deal with this line
            if (mode == Mode.STRICT) {
                throw new IllegalArgumentException(
                        "Failed reading line " + line + " (" + lineNo + "), current stack: " + stack);
            } else {
                LOG.warn("Failed reading line {} ({}), current stack: {}, skipping line", line, lineNo, stack);
            }
            break;
        }
    }

    private void evaluateContextRules(Object o, Class<? extends Kontextregel>[] kontextRegeln) {
        for (Class<? extends Kontextregel> kontextregel : kontextRegeln) {
            try {
                if (!kontextregel.newInstance().isValid(o)) {
                    if (mode == Mode.STRICT) {
                        throw new IllegalArgumentException(
                                "Context rule " + kontextregel.getSimpleName() + " failed on object " + o);
                    } else {
                        LOG.warn("Context rule {} failed on object {}", kontextregel.getSimpleName(), o);
                    }
                }
            } catch (IllegalAccessException | InstantiationException e) {
                if (mode == Mode.STRICT) {
                    throw new IllegalArgumentException(
                            "Context rule " + kontextregel.getSimpleName() + " failed on object " + o, e);
                } else {
                    LOG.warn("Context rule {} failed on object {}", kontextregel.getSimpleName(), o, e);
                }
            }
        }
    }

    private void validateFieldPayload(Field field, String payload)
            throws IllegalAccessException, InstantiationException {
        outer: for (Regelsatz regelsatz : field.getAnnotationsByType(Regelsatz.class)) {

            if (regelsatz.laenge() >= 0) {
                if (payload.length() != regelsatz.laenge()) {
                    validationFailed(field.getDeclaringClass().getSimpleName() + "." + field.getName() + ": Value "
                            + payload + " did not match expected length " + regelsatz.laenge() + ", was "
                            + payload.length());
                }
            }

            if (regelsatz.minLaenge() >= 0) {
                if (payload.length() < regelsatz.minLaenge()) {
                    validationFailed(field.getDeclaringClass().getSimpleName() + "." + field.getName() + ": Value "
                            + payload + " did not match expected minimum length " + regelsatz.minLaenge() + ", was "
                            + payload.length());
                }
            }

            if (regelsatz.maxLaenge() >= 0) {
                if (payload.length() > regelsatz.maxLaenge()) {
                    validationFailed(field.getDeclaringClass().getSimpleName() + "." + field.getName() + ": Value "
                            + payload + " did not match expected maximum length " + regelsatz.maxLaenge() + ", was "
                            + payload.length());
                }
            }

            // No specific rules given, likely only length checks
            if (regelsatz.value().length == 0) {
                continue;
            }

            for (Class<? extends Regel> regel : regelsatz.value()) {
                if (getRegel(regel).isValid(payload)) {
                    continue outer;
                }
            }
            validationFailed(field.getDeclaringClass().getSimpleName() + "." + field.getName() + ": Value " + payload
                    + " did not confirm to any rule of " + toString(regelsatz.value()));
        }
    }

    private void validationFailed(String message) {
        if (mode == Mode.STRICT) {
            throw new IllegalStateException(message);
        } else {
            LOG.warn(message);
        }
    }

    private String toString(Class<? extends Regel>[] regeln) {
        StringBuilder buffer = new StringBuilder();
        for (Class<? extends Regel> regel : regeln) {
            if (buffer.length() > 0) {
                buffer.append(" or ");
            }
            buffer.append(regel.getSimpleName());
        }
        return buffer.toString();
    }

    private Regel getRegel(Class<? extends Regel> regel) throws IllegalAccessException, InstantiationException {
        Regel instance = regelCache.get(regel);
        if (instance == null) {
            instance = regel.newInstance();
            regelCache.put(regel, instance);
        }
        return instance;
    }

    /**
     * Extract the Satzart form a given payload
     * 
     * @param payload the payload of the line
     * @return the Satzart or {@code null}
     */
    private Satzart getSatzart(String payload) {
        Satzart satzart = null;
        for (Satzart sa : Satzart.values()) {
            if (sa.code.equals(payload)) {
                satzart = sa;
                break;
            }
        }
        return satzart;
    }

    /**
     * Peek the current objekt from the stack, if any.
     * 
     * @param stack the stack to peek the object from
     * @return the current top level element of the stack or {@code null}
     */
    private static Kontext peekCurrentObject(Stack<Kontext> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    /**
     * Check if the line matches the expected length.
     * 
     * @param line   the line to check
     * @param length the actual length
     * @param target the length specified by the line
     */
    private void assureLength(String line, int length, int target) {
        if (length != target) {
            if (mode == Mode.STRICT) {
                throw new IllegalArgumentException(
                        "Line '" + line + "' must have length " + target + ", was " + length);
            } else {
                LOG.warn("Line '{}' must have length {}, was {}", line, target, length);
            }
        }
    }

    /**
     * Convert the string payload into a target class. (Note: There are certainly
     * better options out there but this one is simple enough for our needs.)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Object convertType(Field field, Type type, String payload, Stack<Kontext> stack)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, InstantiationException {
        if (type == String.class) {
            return payload;
        }
        if (type == Float.class) {
            return Float.valueOf(payload);
        }
        if (type == Integer.class) {
            return Integer.valueOf(payload);
        }
        if (type == Boolean.class) {
            return "1".equals(payload);
        }
        if (type == LocalDate.class) {
            return LocalDate.parse(payload, LdtConstants.FORMAT_DATE);
        }
        if (type == LocalTime.class) {
            return LocalTime.parse(payload, LdtConstants.FORMAT_TIME);
        }
        if ((type instanceof Class && ((Class<?>) type).isEnum())) {
            Method method = ((Class) type).getDeclaredMethod("getCode");
            if (method != null) {
                for (Object e : EnumSet.allOf((Class<? extends Enum>) type)) {
                    String code = (String) method.invoke(e);
                    if (payload.equals(code)) {
                        return e;
                    }
                }
                return null;
            }
        }
        if ((type instanceof Class && ((Class<?>) type).isAssignableFrom(List.class))) {
            Object currentObject = peekCurrentObject(stack);
            List object = (List) field.get(currentObject);
            if (object == null) {
                object = new ArrayList<>();
                field.set(currentObject, object);
            }
            object.add(convertType(field, ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0],
                    payload, stack));
            return object;
        }
        if (type instanceof Class && ((Class) type).getAnnotation(Objekt.class) != null) {
            Kontext instance = (Kontext) ((Class) type).newInstance();
            stack.push(instance);
            try {
                Field declaredField = ((Class) type).getDeclaredField("value");
                declaredField.setAccessible(true);
                declaredField.set(instance, convertType(declaredField, declaredField.getType(), payload, stack));
            } catch (NoSuchFieldException e) {
                // OK
            }
            return instance;
        }
        throw new IllegalArgumentException("Don't know how to handle type " + type);
    }

}
