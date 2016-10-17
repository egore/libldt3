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

import libldt3.annotations.Feld;
import libldt3.model.objekte.Fliesstext;

import java.lang.reflect.Field;

class KontextregelHelper {

	static boolean containsString(Field field, Object owner) throws IllegalAccessException {
		field.setAccessible(true);
		Object value = field.get(owner);
		if (value instanceof String) {
			String o = (String) value;
			return !o.isEmpty();
		}
		if (value instanceof Fliesstext) {
			Fliesstext fliesstext = ((Fliesstext) value);
			if (fliesstext.getText() != null) {
				for (String s : fliesstext.getText()) {
					if (s != null && !s.isEmpty()) {
						return true;
					}
				}
			}
			if (fliesstext.getBase64text() != null) {
				for (String s : fliesstext.getBase64text()) {
					if (s != null && !s.isEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	static Field findField(Object owner, String fieldtype) {
		for (Field f : owner.getClass().getDeclaredFields()) {
			Feld annotation = f.getAnnotation(Feld.class);
			if (annotation != null && annotation.value().equals(fieldtype)) {
				return f;
			}
		}
		return null;
	}

}
