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

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import libldt3.model.Kontext;
import libldt3.model.enums.TestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static libldt3.model.regel.kontext.KontextregelHelper.*;

/**
 * Wenn Inhalt von FK 8418 = 11 oder FK 7368 vorhanden ist, muss FK 8126 im
 * Obj_0037 vorhanden sein.
 *
 * Wenn auf Grund von fehlendem bzw. nicht verwertbarem Material die
 * Analytik nicht durchgeführt werden konnte, muss der Einsender im Befund
 * darauf aufmerksam gemacht werden.
 */
public class K082 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K082.class);

    private static final Set<String> FIELDTYPES = Set.of("8418", "8126");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        var feld8418 = getFieldValue(fields.get("8418"), owner);
        if (!(feld8418 instanceof TestStatus)) {
            feld8418 = findField(feld8418, "8418");
        }

        // Wenn Inhalt von FK 8418 = 11 oder FK 7368 vorhanden ist, muss FK 8126 im Obj_0037 vorhanden sein
        if (feld8418 == TestStatus.Material_fehlt) {
            if (!containsAnyValue(fields.get("8126"), owner)) {
                return false;
            }
        }

        return true;
    }

}
