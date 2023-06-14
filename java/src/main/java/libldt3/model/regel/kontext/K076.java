/*
 * Copyright 2017  Christoph Brill <opensource@christophbrill.de>
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

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Wenn FK 3412, FK 3413, FK 3414, FK 3415, FK 3416, FK 3417, FK 3418 oder FK 3419 vorhanden sind, dann muss FK 8225
 * mindestens einmal im Obj_0055 vorkommen.
 */
public class K076 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K076.class);

    private static final Set<String> FIELDTYPES = Set.of("3412", "3413", "3414", "3415", "3416", "3417", "3418", "3419", "8225");

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        if (containsAnyString(fields.get("3412"), owner) ||
                containsAnyString(fields.get("3413"), owner) ||
                containsAnyString(fields.get("3414"), owner) ||
                containsAnyString(fields.get("3415"), owner) ||
                containsAnyString(fields.get("3416"), owner) ||
                containsAnyString(fields.get("3417"), owner) ||
                containsAnyString(fields.get("3418"), owner) ||
                containsAnyString(fields.get("3419"), owner)) {
            return containsAnyString(fields.get("8225"), owner);
        }

        return true;
    }

}
