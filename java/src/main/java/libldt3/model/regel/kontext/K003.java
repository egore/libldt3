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

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import libldt3.model.enums.Abrechnungsinfo;

/**
 * Wird die FK 8410 (Test-Ident) im Kontext mit der Überweisung von
 * Laborleistungen an einen Laborfacharzt verwendet, muss die FK 8411
 * (Testbezeichnung) im Datensatz vorkommen (mit Inhalt der FK 8411 muss das
 * Auftragsfeld des digitalen Musters 10 befüllt werden).
 */
public class K003 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K003.class);

    private static final Set<String> FIELDTYPES = Set.of("7303", "8410", "8411");

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        Abrechnungsinfo abrechnungsinfo = (Abrechnungsinfo) fields.get("7303").get(owner);
        if (abrechnungsinfo == null) {
            return true;
        }

        // Wenn Feldinhalt von FK 7303 = 1, 8 oder 9 ist und FK 8410 vorhanden, muss auch FK 8411 vorhanden sein.
        if (abrechnungsinfo == Abrechnungsinfo.GkvLaborfacharzt ||
                abrechnungsinfo == Abrechnungsinfo.Asv ||
                abrechnungsinfo == Abrechnungsinfo.GkvLaborfacharztPraeventiv) {
            if (containsAnyString(fields.get("8410"), owner)) {
                return containsAnyString(fields.get("8411"), owner);
            }
        }

        return true;
    }

}
