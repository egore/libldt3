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

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyValue;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;
import static libldt3.model.regel.kontext.KontextregelHelper.getFieldValue;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import libldt3.model.Kontext;
import libldt3.model.enums.EinheitMesswert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wenn Feldinhalt von FK 8419 = 1 oder 2, muss FK 8421 vorkommen. Wenn Feldinhalt
 * von FK 8419 = 9, darf FK 8421 nicht vorkommen.
 *
 * Wenn zu einem Ergebniswert Maßeinheit angegeben wird, muss
 * angegeben werden, ob es sich bei der Maßeinheit um eine konventionelle
 * oder SI-Einheit handelt. Wenn zu einem Ergebniswert keine Maßeinheit
 * angegeben wird, muss angegeben werden, dass es sich bei dem
 * Ergebniswert um eine sogenannte "dimensionslose Größe" handelt.
 */
public class K002 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K002.class);

    private static final Set<String> FIELDTYPES = Set.of("8419", "8421");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        EinheitMesswert feld8419 = (EinheitMesswert) getFieldValue(fields.get("8419"), owner);
        if (feld8419 == null) {
            return true;
        }

        // Wenn Feldinhalt von FK 8419 = 1 oder 2, muss FK 8421 vorkommen
        if (feld8419 == EinheitMesswert.SI_Einheit ||
            feld8419 == EinheitMesswert.abweichendeEinheit) {
            if (!containsAnyValue(fields.get("8421"), owner)) {
                return false;
            }
        }

        // Wenn Feldinhalt von FK 8419 = 9, darf FK 8421 nicht vorkommen
        if (feld8419 == EinheitMesswert.dimensionsloseGroesse) {
            if (!containsAnyValue(fields.get("8421"), owner)) {
                return false;
            }
        }

        return true;
    }

}
