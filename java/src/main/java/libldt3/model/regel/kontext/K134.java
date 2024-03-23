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

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyValue;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;
import static libldt3.model.regel.kontext.KontextregelHelper.getFieldValue;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import libldt3.model.Kontext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wenn im Obj_0062 die FK 7414 vorkommt und der Inhalt von FK 7414 ungleich 0 ist,
 * dann müssen die FK 7405, FK 7406, FK 7407, FK 7408, FK 7409, FK 7410, FK 7411
 * und FK 7412 vorkommen.
 *
 * Wenn im Obj_0062 die FK 7414 nicht vorkommt, dann dürfen
 * die FK 7405, FK 7406, FK 7407, FK 7408, FK 7409, FK 7410, FK 7411 und FK 7412
 * nicht vorkommen.
 */
public class K134 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K134.class);

    private static final Set<String> FIELDTYPES = Set.of("7414", "7405", "7406", "7407", "7408", "7409", "7410", "7411", "7412");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        if (containsAnyValue(fields.get("7414"), owner)) {
            if (!containsAnyValue(fields.get("7405"), owner) ||
                !containsAnyValue(fields.get("7406"), owner) ||
                !containsAnyValue(fields.get("7407"), owner) ||
                !containsAnyValue(fields.get("7408"), owner) ||
                !containsAnyValue(fields.get("7409"), owner) ||
                !containsAnyValue(fields.get("7410"), owner) ||
                !containsAnyValue(fields.get("7411"), owner) ||
                !containsAnyValue(fields.get("7412"), owner)) {
                return false;
            }
        } else {
            if (containsAnyValue(fields.get("7405"), owner) ||
                containsAnyValue(fields.get("7406"), owner) ||
                containsAnyValue(fields.get("7407"), owner) ||
                containsAnyValue(fields.get("7408"), owner) ||
                containsAnyValue(fields.get("7409"), owner) ||
                containsAnyValue(fields.get("7410"), owner) ||
                containsAnyValue(fields.get("7411"), owner) ||
                containsAnyValue(fields.get("7412"), owner)) {
                return false;
            }
        }

        return true;
    }

}
