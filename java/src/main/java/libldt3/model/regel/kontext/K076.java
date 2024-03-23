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

import libldt3.model.Kontext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyValue;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Wenn Inhalt von FK 8418 ≠ 01 oder 02 oder 09 oder 11 oder 12 ist, dann muss FK
 * 8225 mindestens einmal vorkommen.
 *
 * Der Zeitpunkt der Messung muss immer angegeben werden, außer bei
 * fehlendem oder unvollständigem Material, fehlendem Wert oder einer
 * Stornierung.
 */
public class K076 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K076.class);

    private static final Set<String> FIELDTYPES = Set.of("3412", "3413", "3414", "3415", "3416", "3417", "3418", "3419", "8225");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        if (containsAnyValue(fields.get("3412"), owner) ||
                containsAnyValue(fields.get("3413"), owner) ||
                containsAnyValue(fields.get("3414"), owner) ||
                containsAnyValue(fields.get("3415"), owner) ||
                containsAnyValue(fields.get("3416"), owner) ||
                containsAnyValue(fields.get("3417"), owner) ||
                containsAnyValue(fields.get("3418"), owner) ||
                containsAnyValue(fields.get("3419"), owner)) {
            return containsAnyValue(fields.get("8225"), owner);
        }

        return true;
    }

}
