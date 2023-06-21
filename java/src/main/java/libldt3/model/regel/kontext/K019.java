/*
 * Copyright 2016-2023  Christoph Brill <opensource@christophbrill.de>
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
import libldt3.model.enums.Gebuehrenordnung;
import libldt3.model.objekte.Untersuchungsabrechnung;
import libldt3.model.regel.format.F009;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.findFields;
import static libldt3.model.regel.kontext.KontextregelHelper.findFieldsRecursive;
import static libldt3.model.regel.kontext.KontextregelHelper.getFieldValue;

/**
 * Wenn Inhalt von FK 4121 = 0, 1 oder 2, dann gilt für den Inhalt FK 5001 die
 * Regel F009.
 */
public class K019 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K019.class);

    private static final Set<String> FIELDTYPES = Set.of("4121", "5001");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<Kontext, Map<String, Field>> fields = findFieldsRecursive(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        Untersuchungsabrechnung.Untersuchungsabrechnung_Gebuehrenordnung gebuehrenordnung = (Untersuchungsabrechnung.Untersuchungsabrechnung_Gebuehrenordnung) getFieldValue(fields.get(owner).get("4121"), owner);
        if (gebuehrenordnung == null) {
            return true;
        }

        if (gebuehrenordnung.value == Gebuehrenordnung.EBM || gebuehrenordnung.value == Gebuehrenordnung.BMAe || gebuehrenordnung.value == Gebuehrenordnung.EGO) {
            List<Untersuchungsabrechnung.Gebuehrennummer> gebuehrennummern = (List<Untersuchungsabrechnung.Gebuehrennummer>) getFieldValue(fields.get(gebuehrenordnung).get("5001"), gebuehrenordnung);
            for (Untersuchungsabrechnung.Gebuehrennummer gebuehrennummer : gebuehrennummern) {
                if (!new F009().isValid(gebuehrennummer.value)) {
                    LOG.error("Invalid number " + gebuehrennummer.value);
                    return false;
                }
            }
        }
        return true;
    }

}
