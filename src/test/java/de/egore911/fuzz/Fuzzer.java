/*
 * Copyright 2016  Christoph Brill <egore911@gmail.com>
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
package de.egore911.fuzz;

import org.reflections.Reflections;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 * @author Christoph Brill &lt;egore911@gmail.com&gt;
 * @since 14.05.16 15:47
 */
public class Fuzzer {

    public interface CustomHandler {
        Object randomValue(Field field);
    }

    private final Random RANDOM = new Random();

    private final int MAX_DEPTH = 20;
    private final int MAX_COLLECTION_ELEMENTS = 2;
    private final CustomHandler customHandler;

    private final Map<String, Reflections> REFLECTIONS_CACHE = new HashMap<>();

    public Fuzzer() {
        this(null);
    }

    public Fuzzer(@Nullable CustomHandler customHandler) {
        this.customHandler = customHandler;
    }

    public <T> T fuzz(Class<T> klass, String packageName) {
        if (packageName == null) {
            packageName = klass.getPackage().getName();
        }
        return fuzz(klass, packageName, 0);
    }

    private <T> T instantiate(Class<T> klass, String packageName) {
        T t = null;
        try {
            if (Modifier.isAbstract(klass.getModifiers())) {
                Reflections reflections = REFLECTIONS_CACHE.computeIfAbsent(packageName, k -> new Reflections(packageName));
                Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(klass);
                Exception ie = null;
                for (Class<? extends T> subType : subTypes) {
                    try {
                        t = subType.newInstance();
                        break;
                    } catch (InstantiationException e) {
                        // Ok, let's try again
                        ie = e;
                    }
                }
                if (t == null) {
                    // Could not be instantiated
                    throw new RuntimeException("Could not instantiate " + klass + " in " + subTypes, ie);
                }
            } else {
                t = klass.newInstance();
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T fuzz(Class<T> klass, String packageName, int depth) {
        if (depth > MAX_DEPTH) {
            return null;
        }
        try {
            T t = instantiate(klass, packageName);

            Class<?> currentClass = t.getClass();
            while (currentClass != null) {
                Field[] fields = currentClass.getDeclaredFields();
                for (Field field : fields) {
                    // Don't fuzz static fields
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    Type type = field.getGenericType();
                    field.setAccessible(true);
                    Object value = null;
                    if (customHandler != null) {
                        value = customHandler.randomValue(field);
                    }
                    if (value == null) {
                        value = randomForType(type, packageName, depth);
                    }
                    field.set(t, value);
                }

                currentClass = currentClass.getSuperclass();
            }

            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Object randomForType(Type type, String packageName, int depth) throws IllegalAccessException, InstantiationException {
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return randomInt();
        } else if (type.equals(long.class) ||type.equals(Long.class)) {
            return randomLong();
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            return randomDouble();
        } else if (type.equals(float.class) || type.equals(Float.class)) {
            return randomFloat();
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return randomBoolean();
        } else if (type.equals(String.class)) {
            return randomString();
        } else if (type.equals(UUID.class)) {
            return randomUUID();
        } else if (type.equals(byte[].class)) {
            return randomString().getBytes();
        } else if (type.equals(Date.class)) {
            return randomDate();
        } else if (type.equals(LocalDateTime.class)) {
            return randomDateTime();
        } else if (type.equals(LocalDate.class)) {
            return randomLocalDate();
        } else if (type.equals(LocalTime.class)) {
            return randomLocalTime();
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class rawType = (Class) parameterizedType.getRawType();
            Object o;
            if (rawType.equals(Collection.class)) {
                o = new ArrayList();
            } else if (rawType.equals(List.class)) {
                o = new ArrayList();
            } else if (rawType.equals(Set.class)) {
                o = new HashSet();
            } else {
                o = rawType.newInstance();
            }
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type t : actualTypeArguments) {
                for (int i = 0; i < 1 + RANDOM.nextInt(MAX_COLLECTION_ELEMENTS); i++) {
                    Object e = randomForType(t, packageName, depth);
                    if (e != null) {
                        ((Collection) o).add(e);
                    }
                }
            }
            return o;
        } else if (type instanceof Class && ((Class<?>) type).isEnum()) {
            try {
                Method enumDir = Class.class.getDeclaredMethod("enumConstantDirectory");
                enumDir.setAccessible(true);
                Map<?, ?> dir = (Map<?, ?>) enumDir.invoke(type);
                return dir.values().iterator().next();
            } catch (NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            return fuzz((Class) type, packageName, depth + 1);
        }
    }

    private UUID randomUUID() {
        return UUID.randomUUID();
    }

    private double randomDouble() {
        return RANDOM.nextDouble();
    }

    private float randomFloat() {
        return RANDOM.nextFloat();
    }

    private boolean randomBoolean() {
        return RANDOM.nextBoolean();
    }

    private long randomLong() {
        return RANDOM.nextLong();
    }

    private int randomInt() {
        return RANDOM.nextInt();
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }

    private Date randomDate() {
        return Date.from(randomDateTime().atZone(ZoneId.systemDefault()).toInstant());
    }

    private LocalDate randomLocalDate() {
        return LocalDate.now().plusDays(RANDOM.nextInt(300)).minusDays(150);
    }

    private LocalTime randomLocalTime() {
        return LocalTime.now().plusHours(RANDOM.nextInt(24)).minusHours(12);
    }

    private LocalDateTime randomDateTime() {
        return LocalDateTime.now().plusDays(RANDOM.nextInt(300)).minusDays(150);
    }

}
