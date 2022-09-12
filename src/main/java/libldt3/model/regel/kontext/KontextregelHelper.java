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
package libldt3.model.regel.kontext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import libldt3.annotations.Feld;
import libldt3.annotations.Objekt;
import libldt3.model.objekte.Fliesstext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class KontextregelHelper {

	public static final Logger LOG = LoggerFactory.getLogger(KontextregelHelper.class);

	/**
	 * Check if a given field has any string content (either simply text or multiline Fliesstext)
	 */
	static boolean containsAnyString(Field field, Object owner) throws IllegalAccessException {
		if (field == null) {
			LOG.warn("No field given, cannot check for content");
			return false;
		}
		field.setAccessible(true);
		Object value = field.get(owner);
		return containsAnyString(value);
	}

	/**
	 * Check if a given field has any string content (either simply text or multiline Fliesstext)
	 */
	static boolean containsAnyString(Object value) throws IllegalAccessException {
		if (value instanceof String) {
			String o = (String) value;
			return !o.isEmpty();
		}
		if (value instanceof Fliesstext) {
			Fliesstext fliesstext = ((Fliesstext) value);
			if (fliesstext.text != null) {
				for (String s : fliesstext.text) {
					if (s != null && !s.isEmpty()) {
						return true;
					}
				}
			}
			if (fliesstext.base64text != null) {
				for (String s : fliesstext.base64text) {
					if (s != null && !s.isEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Find a field matching their {@link Feld#value()} with the given fieldtype. Not recursive, only a single field.
	 */
	static Field findField(Object owner, String fieldtype) {
		for (Field f : owner.getClass().getDeclaredFields()) {
			Feld annotation = f.getAnnotation(Feld.class);
			if (annotation != null && annotation.value().equals(fieldtype)) {
				f.setAccessible(true);
				return f;
			}
		}
		return null;
	}

	/**
	 * Find fields matching their {@link Feld#value()} with the given fieldtypes. Not recursive, but for multiple field.
	 */
	static Map<String, Field> findFields(Object owner, Set<String> fieldtypes) {
		Map<String, Field> result = new HashMap<>(fieldtypes.size());
		for (Field f : owner.getClass().getDeclaredFields()) {
			Feld annotation = f.getAnnotation(Feld.class);
			if (annotation != null && fieldtypes.contains(annotation.value())) {
				f.setAccessible(true);
				result.put(annotation.value(), f);
			}
		}
		return result;
	}

	/**
	 * Find fields matching their {@link Feld#value()} with the given fieldtypes. Recursive and for multiple field.
	 */
	static Map<Object, List<Field>> findFieldsRecursive(Object owner, Set<String> fieldtypes) throws IllegalArgumentException, IllegalAccessException {
		Map<Object, List<Field>> result = new HashMap<>();
		List<Field> fields = new ArrayList<>();
		for (Field f : owner.getClass().getDeclaredFields()) {
			Feld annotation = f.getAnnotation(Feld.class);
			if (annotation != null && fieldtypes.contains(annotation.value())) {
				f.setAccessible(true);
				fields.add(f);
				result.put(owner, fields);
			}
			f.setAccessible(true);
			Object object = f.get(owner);
			if (object != null) {
				if (object.getClass().getAnnotation(Objekt.class) != null) {
					result.putAll(findFieldsRecursive(object, fieldtypes));
				} else if (object instanceof Iterable) {
					for (Object o : (Iterable) object) {
						result.putAll(findFieldsRecursive(o, fieldtypes));
					}
				}
			}
		}
		return result;
	}

}
