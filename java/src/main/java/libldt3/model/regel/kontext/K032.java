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

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import libldt3.model.Kontext;
import libldt3.model.enums.Abrechnungsinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Für Satzart 8215 gilt: Wenn Inhalt von FK 7303 = 1 muss FK 4239 = 27 vorhanden
 * sein. Wenn Inhalt von FK 7303 = 2 muss FK 4239 = 28 vorhanden sein. Wenn Inhalt
 * von FK 7303 = 9 muss FK 4239 = 27 in Kombination mit FK 4221 = 2 vorhanden sein.
 * Wenn Inhalt von FK 7303 = 10 muss FK 4239 = 28 in Kombination mit FK 4221 = 2
 * vorhanden sein.
 *
 * Abhängigkeit der Abrechnungsinformation von den Abrechnungsobjekten
 * und deren Inhalten
 */
public class K032 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K032.class);

    private static final Set<String> FIELDTYPES = Set.of("7303", "4239");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        Abrechnungsinfo feld7303 = (Abrechnungsinfo) fields.get("7303").get(owner);

        // Wenn Inhalt von FK7303=1 muss FK4239 = 27 vorhanden sein .
        if (feld7303 == Abrechnungsinfo.GKV_Laborfacharzt) {
            return containsAnyValue(fields.get("4239"), owner);
        }

        // Wenn Inhalt von FK7303=2 muss FK4239 = 28 vorhanden sein .
        if (feld7303 == Abrechnungsinfo.GKV_LG) {
            return containsAnyValue(fields.get("4239"), owner);
        }

        // Wenn Inhalt von FK7303=9 muss FK4239 = 27 FK 4221 = 2 vorhanden sein .
        if (feld7303 == Abrechnungsinfo.GKV_Laborfacharzt_praeventiv) {
            return containsAnyValue(fields.get("4239"), owner);
        }

        // Wenn Inhalt von FK7303=10 muss FK4239 = 28 FK 4221 = 2 vorhanden sein .
        if (feld7303 == Abrechnungsinfo.GKV_LG_praeventiv) {
            return containsAnyValue(fields.get("4239"), owner);
        }

        return true;
    }

}
