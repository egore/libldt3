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
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import libldt3.annotations.Feldart;
import libldt3.annotations.Regelsatz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import libldt3.LdtConstants.Mode;
import libldt3.annotations.Datenpaket;
import libldt3.annotations.Feld;
import libldt3.annotations.Objekt;
import libldt3.model.saetze.Satz;

/**
 * Simple, reflection and annotation based writer for LDT 3.0.
 * 
 * @author Christoph Brill &lt;opensource@christophbrill.de&gt;
 */
public class LdtWriter {

    private static final Logger LOG = LoggerFactory.getLogger(LdtWriter.class);

    private final Mode mode;

    public LdtWriter(Mode mode) {
        this.mode = mode;
    }

    /**
     * Write a given set of Satz elements to a given path
     * 
     * @param data
     *            the Satz elements to write
     * @param path
     *            the path to write to
     * @throws IOException
     *             if writing the Satz elements failed
     */
    public void write(List<Satz> data, String path) throws IOException {
        try (PrintWriter w = new PrintWriter(path, "ISO-8859-1")) {
            write(data, w);
        }
    }

    /**
     * Write a given set of Satz elements to a given path
     * 
     * @param data
     *            the Satz elements to write
     * @param path
     *            the path to write to
     * @throws IOException
     *             if writing the Satz elements failed
     */
    public void write(List<Satz> data, Path path) throws IOException {
        write(data, path.toString());
    }

    /**
     * Write a given set of Satz elements to a given writer
     * 
     * @param data
     *            the Satz elements to write
     * @param writer
     *            the writer to write to
     * @throws IOException
     *             if writing the Satz elements failed
     */
    public void write(List<Satz> data, PrintWriter writer) throws IOException {
        try {
            for (Satz o : data) {
                handleOutput(o, writer);
            }
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException
                | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleOutput(Object o, PrintWriter writer) throws IllegalArgumentException,
            IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
        Datenpaket datenpaket = o.getClass().getAnnotation(Datenpaket.class);
        if (datenpaket != null) {
            writer.printf("0138000%s\r\n", datenpaket.value().code);
        }
        writeObjekt(o, writer);
        if (datenpaket != null) {
            writer.printf("0138001%s\r\n", datenpaket.value().code);
        }
    }

    @SuppressWarnings("rawtypes")
    private void writeObjekt(Object o, PrintWriter writer)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Objekt objekt = o.getClass().getAnnotation(Objekt.class);
        if (objekt != null && !objekt.value().isEmpty()) {
            writer.printf("0178002Obj_%s\r\n", objekt.value());
        }
        for (Field field : o.getClass().getDeclaredFields()) {
            Feld feld = field.getAnnotation(Feld.class);
            if (feld != null) {
                field.setAccessible(true);
                Object object = field.get(o);
                if (object == null) {
                    continue;
                }
                if (object instanceof List) {
                    for (Object o1 : (List) object) {
                        writeTextualRepresentation(field, writer, feld, o1);
                        handleOutput(o1, writer);
                    }
                } else {
                    writeTextualRepresentation(field, writer, feld, object);
                    handleOutput(object, writer);
                }
            }
        }
        if (objekt != null && !objekt.value().isEmpty()) {
            writer.printf("0178003Obj_%s\r\n", objekt.value());
        }
    }

    private void writeLdtLine(PrintWriter writer, Feld feld, String text) {
        writer.printf("%03d%s%s\r\n", (text.length() + 9), feld.value(), text);
    }

    /**
     * Transform an object into its LDT 3.0 represenation
     */
    private void writeTextualRepresentation(Field field, PrintWriter writer, Feld feld, Object object) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (feld.feldart() == Feldart.muss && object == null) {
            if (mode == Mode.STRICT) {
                throw new IllegalArgumentException(
                        "Cannot get textual representation of null when writing feld " + feld);
            } else {
                LOG.warn("Cannot get textual representation of null when writing feld {}, assuming empty string", feld);
                writeLdtLine(writer, feld, "");
                return;
            }
        }
        if (object instanceof String) {
            String value = (String) object;
            for (Regelsatz regelsatz : field.getAnnotationsByType(Regelsatz.class)) {
                if (regelsatz.maxLaenge() >= 0) {
                    if (value.length() > regelsatz.maxLaenge()) {
                        if (mode == Mode.STRICT) {
                            throw new IllegalArgumentException("Value " + value + " must have maximum length of " + regelsatz.maxLaenge() + ", but was " + value.length());
                        } else {
                            LOG.warn("{}.{}: Value {} must have maximum length of {}, but was {}, trimming", field.getDeclaringClass().getSimpleName(), field.getName(), value, regelsatz.maxLaenge(), value.length());
                            value = value.substring(0, Math.min(value.length(), regelsatz.maxLaenge()));
                        }
                    }
                } else if (regelsatz.laenge() >= 0) {
                    if (value.length() > regelsatz.laenge()) {
                        if (mode == Mode.STRICT) {
                            throw new IllegalArgumentException(field.getDeclaringClass().getSimpleName() + "." + field.getName() + ": Value " + value + " must have exact length of " + regelsatz.laenge() + ", but was " + value.length());
                        } else {
                            LOG.warn("{}.{}: Value {} must have exact length of {}, but was {}, trimming", field.getDeclaringClass().getSimpleName(), field.getName(), value, regelsatz.laenge(), value.length());
                            value = value.substring(0, regelsatz.laenge());
                        }
                    } else if (value.length() < regelsatz.laenge()) {
                        if (mode == Mode.STRICT) {
                            throw new IllegalArgumentException(field.getDeclaringClass().getSimpleName() + "." + field.getName() + ": Value " + value + " must have exact length of " + regelsatz.laenge() + ", but was " + value.length());
                        } else {
                            LOG.warn("{}.{}: Value {} must have exact length of {}, but was {}, ignoring", field.getDeclaringClass().getSimpleName(), field.getName(), value, regelsatz.laenge(), value.length());
                        }
                    }
                }
            }
            writeLdtLine(writer, feld, value);
            return;
        }
        if (object instanceof Float) {
            writeLdtLine(writer, feld, object.toString());
            return;
        }
        if (object instanceof Integer) {
            writeLdtLine(writer, feld, object.toString());
            return;
        }
        if (object instanceof Boolean) {
            writeLdtLine(writer, feld, object.equals(Boolean.TRUE) ? "1" : "0");
            return;
        }
        if (object instanceof LocalDate) {
            writeLdtLine(writer, feld, ((LocalDate) object).format(LdtConstants.FORMAT_DATE));
            return;
        }
        if (object instanceof LocalTime) {
            writeLdtLine(writer, feld, ((LocalTime) object).format(LdtConstants.FORMAT_TIME));
            return;
        }
        if (Enum.class.isAssignableFrom(object.getClass())) {
            Method method = object.getClass().getDeclaredMethod("getCode");
            if (method != null) {
                writeLdtLine(writer, feld, (String) method.invoke(object));
                return;
            }
        }
        Objekt annotation = object.getClass().getAnnotation(Objekt.class);
        if (annotation != null && annotation.value().isEmpty()) {
            try {
                Field declaredField = object.getClass().getDeclaredField("value");
                declaredField.setAccessible(true);
                Object innerObject = declaredField.get(object);
                writeTextualRepresentation(declaredField, writer, feld, innerObject);
                Objekt innerAnnotation = innerObject.getClass().getAnnotation(Objekt.class);
                if (innerAnnotation != null && !innerAnnotation.value().isEmpty()) {
                    writeObjekt(innerObject, writer);
                }
                return;
            } catch (NoSuchFieldException e) {
                if (mode == Mode.STRICT) {
                    throw new IllegalStateException(e);
                } else {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        String name = feld.name();
        if (name.isEmpty()) {
            if (annotation != null && !annotation.name().isEmpty()) {
                name = annotation.name();
            } else {
                name = object.getClass().getSimpleName();
            }
        }
        writeLdtLine(writer, feld, name);
    }
}
