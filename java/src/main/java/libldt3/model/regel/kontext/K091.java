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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Falls die FK 4109 vorhanden ist und der Feldinhalt >= "01.01.2015" sowie der
 * Inhalt der Stellen 3 – 5 der FK 4104 < 800, dann müssen die FK 3119 und FK 4133
 * vorhanden sein.
 *
 * Da seit dem  01.01.2015 im Bereich der GKV-Kostenträgern KVKs nicht
 * mehr zulässig sind, können Behandlungen auf Basis von eingelesen
 * KVKs bei GKV-Kostenträgern nicht durchgeführt werden.
 */
public class K091 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K091.class);

    private static final Set<String> FIELDTYPES = Set.of("4109", "4104", "3119", "4133");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        LocalDate feld4109 = (LocalDate) fields.get("4109").get(owner);
        String feld4104 = (String) fields.get("4109").get(owner);

        if (feld4109 != null && !feld4109.isBefore(LocalDate.parse("01.01.2015"))) {
            if (Integer.parseInt(feld4104.substring(3, 5)) < 800) {
                return containsAnyString(fields.get("3119"), owner) && containsAnyString(fields.get("4133"), owner);
            }
        }

        return true;
    }

}
