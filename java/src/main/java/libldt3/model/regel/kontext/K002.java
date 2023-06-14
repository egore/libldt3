/*
 * Copyright 2016-2017  Christoph Brill <opensource@christophbrill.de>
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

import libldt3.model.enums.EinheitMesswert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Wenn zu einem Ergebniswert Maßeinheit angegeben wird, muss angegeben werden,
 * ob es sich bei der Maßeinheit um eine konventionelle oder SI-Einheit
 * handelt. Wenn zu einem Ergebniswert keine Maßeinheit angegeben wird, muss
 * angegeben werden, dass es sich bei dem Ergebniswert um eine sogenannte
 * "dimensionslose Größe" handelt.
 */
public class K002 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K002.class);

    private static final Set<String> FIELDTYPES = Set.of("8419", "8421");

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        EinheitMesswert einheitMesswert = (EinheitMesswert) fields.get("8419").get(owner);
        if (einheitMesswert == null) {
            return true;
        }

        // Wenn Feldinhalt von FK 8419 = 1 oder 2, muss FK 8421 vorkommen.
        if (einheitMesswert == EinheitMesswert.SI_Einheit || einheitMesswert == EinheitMesswert.konventionelle_Einheit) {
            return containsAnyString(fields.get("8421"), owner);
        }

        // Wenn Feldinhalt von FK 8419 = 9, darf FK 8421 nicht vorkommen.
        if (einheitMesswert == EinheitMesswert.dimensionslose_Groesse) {
            return !containsAnyString(fields.get("8421"), owner);
        }

        return true;
    }

}
