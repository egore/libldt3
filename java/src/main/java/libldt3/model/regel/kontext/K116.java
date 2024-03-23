/*
 * Copyright 2016-2024  Christoph Brill &lt;opensource@christophbrill.de&gt;
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
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyValue;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;
import static libldt3.model.regel.kontext.KontextregelHelper.getFieldValue;

/**
 * Wenn Feldinhalt von FK 4239 ≠ 28 und wenn FK 0222 vorhanden ist, dann muss
 * entweder FK 0212 oder FK 0223 vorhanden sein. Wenn Feldinhalt von FK 4239 = 28
 * und wenn FK 0222 vorhanden ist, dann muss ein FK 0212 vorhanden sein. Die FK
 * 0223 darf nicht vorhanden sein.
 *
 * Es ist ausgeschlossen, dass ein Krankenhausarzt im Rahmen seiner
 * ASV-Berechtigung Mitglied einer Laborgemeinschaft ist und in diesem
 * Zusammenhang Laborleistungen auf Muster 10A anfordert, gemäß § 25
 * Abs. 3 S. 7 BMV-Ä.
 */
public class K116 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K116.class);

    private static final Set<String> FIELDTYPES = Set.of("4239", "0222", "0212", "0223");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        // FIXME: This field does not exist here and likely must be inherited from the context
        Scheinuntergruppe scheinuntergruppe = (Scheinuntergruppe) getFieldValue(fields.get("4239"), owner);
        if (scheinuntergruppe != Scheinuntergruppe.Muster10A && containsAnyValue(fields.get("0222"), owner)) {
            if (!containsAnyValue(fields.get("0212"), owner) && !containsAnyValue(fields.get("0223"), owner)) {
                LOG.error("For other than Muster 10A either FK 0212 or 0223 must be present");
                return false;
            }
        }
        if (scheinuntergruppe == Scheinuntergruppe.Muster10A && containsAnyValue(fields.get("0222"), owner)) {
            if (!containsAnyValue(fields.get("0212"), owner)) {
                LOG.error("For Muster 10A FK 0212 must be present");
                return false;
            }
            if (containsAnyValue(fields.get("0223"), owner)) {
                LOG.error("For Muster 10A FK 0223 must not be present");
                return false;
            }
        }

        return true;
    }

}
