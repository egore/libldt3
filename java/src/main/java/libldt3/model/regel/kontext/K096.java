/*
 * Copyright 2021  Christoph Brill <opensource@christophbrill.de>
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

import libldt3.model.enums.Auftragsstatus;
import libldt3.model.enums.TestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Wenn Inhalt von FK 8401 = 2, darf der Inhalt von FK 8418 nicht 02, 05 oder 10 sein.
 */
public class K096 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K096.class);

    private static final Set<String> FIELDTYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("8401", "8418")));

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        Auftragsstatus status = (Auftragsstatus) fields.get("8401").get(owner);
        if (status == Auftragsstatus.Auftrag_abgeschlossen) {
            TestStatus testStatus = (TestStatus) fields.get("8418").get(owner);
            return testStatus != TestStatus.Ergebnis_folgt &&
                testStatus != TestStatus.Ergebnis_ermittelt &&
                testStatus != TestStatus.Erweiterte_Analytik_erforderlich;
        }

        return true;
    }

}
