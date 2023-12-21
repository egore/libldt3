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
import libldt3.model.enums.Einsenderstatus;
import libldt3.model.enums.Scheinuntergruppe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Wenn Inhalt von FK 7321 = 03, 04, 05, 06, 08, 11, 12, 14 oder 16 ist, dann muss
 * FK 8147 vorhanden sein.
 */
public class K045 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K045.class);

    private static final Set<String> FIELDTYPES = Set.of("7321", "8147");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        List<Einsenderstatus> feld7321 = (List<Einsenderstatus>) fields.get("7321").get(owner);
        if (feld7321.contains(Einsenderstatus.Einsender_sonstige) ||
                feld7321.contains(Einsenderstatus.Versicherter) ||
                feld7321.contains(Einsenderstatus.Rechnungsempfaenger) ||
                feld7321.contains(Einsenderstatus.Bevollmaechtigter) ||
                feld7321.contains(Einsenderstatus.Leistungserbringer) ||
                feld7321.contains(Einsenderstatus.Halter) ||
                feld7321.contains(Einsenderstatus.Patient) ||
                feld7321.contains(Einsenderstatus.Ueberweiser) ||
                feld7321.contains(Einsenderstatus.sonstige_juristischePerson)) {
            return containsAnyString(fields.get("8147"), owner);
        }

        return true;
    }

}
