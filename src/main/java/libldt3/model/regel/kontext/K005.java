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
import static libldt3.model.regel.kontext.KontextregelHelper.findFieldsRecursive;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import libldt3.model.enums.Abrechnungsinfo;
import libldt3.model.enums.Befundtyp;
import libldt3.model.saetze.Befund;

public class K005 implements Kontextregel {
	
	private static final Logger LOG = LoggerFactory.getLogger(K005.class);

	private static final Set<String> fieldtypes = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("4121", "7303")));

	@Override
	public boolean isValid(Object owner) throws IllegalAccessException {

		Befund befund = (Befund) owner;

		Field field = findField(befund.getBefundinformationen(), "8401");
		if (field == null) {
			LOG.error("Class of " + befund.getBefundinformationen() + " must have field 8401");
			return false;
		}

		Befundtyp befundtyp = (Befundtyp) field.get(befund.getBefundinformationen());

		if (befundtyp != Befundtyp.Endbefund || befundtyp != Befundtyp.Nachforderungsendbefund) {
			Map<Object, List<Field>> findFieldsRecursive2 = findFieldsRecursive(owner, fieldtypes);

			for (Map.Entry<Object, List<Field>> entry : findFieldsRecursive2.entrySet()) {
				if (entry.getValue().size() != 2) {
					// Likely a different type, e.g. Veranladssungsgrund
					continue;
				}

				Abrechnungsinfo abrechnungsinfo;
				Object other;
				Object o1 = entry.getValue().get(0).get(entry.getKey());
				Object o2 = entry.getValue().get(1).get(entry.getKey());
				if (o1 instanceof Abrechnungsinfo) {
					abrechnungsinfo = (Abrechnungsinfo) o1;
					other = o2;
				} else {
					other = o1;
					abrechnungsinfo = (Abrechnungsinfo) o2;
				}

				if (abrechnungsinfo == Abrechnungsinfo.GkvLaborfacharzt || abrechnungsinfo == Abrechnungsinfo.GkvLg ||
						abrechnungsinfo == Abrechnungsinfo.Asv || abrechnungsinfo == Abrechnungsinfo.GkvLaborfacharztPraeventiv ||
						abrechnungsinfo == Abrechnungsinfo.GkgLgPraeventiv) {

					if (containsAnyString(other, owner)) {
						return false;
					}
				}
			}
		}

		return true;
	}

}
