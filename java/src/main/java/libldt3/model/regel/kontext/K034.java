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

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import libldt3.model.Kontext;
import libldt3.model.enums.Abrechnungsinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wenn Inhalt von FK 7303 = 3 muss FK 7362 = 1 vorhanden sein. Wenn Inhalt von FK
 * 7303 = 4 muss FK 7362 = 2 vorhanden sein.
 */
public class K034 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K034.class);

    private static final Set<String> FIELDTYPES = Set.of("7303", "7362");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        Abrechnungsinfo feld7303 = (Abrechnungsinfo) fields.get("7303").get(owner);

        // Wenn Inhalt von FK7303=3 muss FK7362 = 1 vorhanden sein .
        if (feld7303 == Abrechnungsinfo.PKV_Laborfacharzt) {
            return containsAnyString(fields.get("7362"), owner);
        }

        // Wenn Inhalt von FK7303=4 muss FK7362 = 2 vorhanden sein .
        if (feld7303 == Abrechnungsinfo.PKV_LG) {
            return containsAnyString(fields.get("7362"), owner);
        }

        return true;
    }

}
