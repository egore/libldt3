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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.findField;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;
import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;

/**
 * FK 3112 und/oder FK 3121 muss vorhanden sein. Ausnahmen: Nur wenn FK 3114
 * vorhanden und der Feldinhalt ungleich "D" ist, dann gilt: Ist die FK 4109
 * vorhanden, dann muss die FK 3112 nicht vorhanden sein. Nur wenn FK 3124
 * vorhanden und der Feldinhalt ungleich "D" ist, dann gilt: Ist die FK 4109
 * vorhanden, dann muss die FK 3121 nicht vorhanden sein.
 *
 * Diese Regel beschreibt die mindestens erforderlichen Angaben im
 * Obj_0007 (Anschrift). Grundlage für diese Regel bilden die Vorgaben des
 * KVDT.
 */
public class K017 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K017.class);

    private static final Set<String> FIELDTYPES = Set.of("3112", "3121", "3114", "3124");

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        return !checkExclusion(owner, fields, "3114", "3112") &&
                !checkExclusion(owner, fields, "3124", "3121") &&
                (containsAnyString(fields.get("3112"), owner) || containsAnyString(fields.get("3121"), owner));

    }

    public boolean checkExclusion(Object owner, Map<String, Field> fields, String first, String second) throws IllegalAccessException {
        String value = (String) fields.get(first).get(owner);
        // XXX 4109 does not exist on the current object, likely we need to traverse the object tree to find it in one
        // of the holding classes
        if (value != null && !"D".equals(value) && containsAnyString(findField(owner, "4109")) &&
                containsAnyString(fields.get(second), owner)) {
            LOG.error("FK {} is present and not 'D'. Also FK 4109 is present. Then {} must not be present", first, second);
            return true;
        }
        return false;
    }
}
