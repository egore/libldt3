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

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findField;

import java.lang.reflect.Field;

import libldt3.model.enums.Abrechnungsinfo;

public class K004 implements Kontextregel {

	@Override
	public boolean isValid(Object owner) throws IllegalAccessException {
		Field source = findField(owner, "7303");
		if (source == null) {
			return true;
		}

		Object o = source.get(owner);
		if (o == null) {
			return true;
		}
		if (o instanceof Abrechnungsinfo) {
			switch ((Abrechnungsinfo) o) {
				case GkvLaborfacharzt:
				case GkvLg:
				case Asv:
				case GkvLaborfacharztPraeventiv:
				case GkgLgPraeventiv:
					Field f = findField(owner, "4241");
					return containsAnyString(f, owner);
				default:
					return true;
			}
		}
		return false;
	}

}
