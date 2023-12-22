/*
 * Copyright 2016-2023  Christoph Brill &lt;opensource@christophbrill.de&gt;
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

import libldt3.model.Kontext;
import libldt3.model.enums.Scheinuntergruppe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyValue;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Wenn Inhalt von FK 4239 = 27 und FK 8240 vorhanden, dann muss eine der
 * folgenden Kombinationen vorhanden sein:
 * - FK 4217 und FK 4241 oder
 * - FK 4225 und FK 4241 oder
 * - FK 4225 und FK 4248.
 */
public class K041 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K041.class);

    private static final Set<String> FIELDTYPES = Set.of("4239", "8240", "4217", "4241", "4225", "4248");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        if (((Scheinuntergruppe) fields.get("4239").get(owner)) == Scheinuntergruppe.Muster10 && containsAnyValue(fields.get("8240"), owner)) {
            if (containsAnyValue(fields.get("4217"), owner) && containsAnyValue(fields.get("4241"), owner)) {
                return true;
            }
            if (containsAnyValue(fields.get("4225"), owner) && containsAnyValue(fields.get("4241"), owner)) {
                return true;
            }
            if (containsAnyValue(fields.get("4225"), owner) && containsAnyValue(fields.get("4248"), owner)) {
                return true;
            }
            return false;
        }

        return true;
    }

}
