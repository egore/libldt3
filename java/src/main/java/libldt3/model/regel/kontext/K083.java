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
import libldt3.model.enums.Laborart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wenn in Satzart 8220 oder 8205 die FK 7266 mit den Inhalten 1 oder 2 vorkommt,
 * muss in Satzart 8205 die FK 8145 vorkommen, die FK 8153 darf nicht vorkommen.
 */
public class K083 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K083.class);

    private static final Set<String> FIELDTYPES = Set.of("7266", "8145", "8153");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        Laborart feld7266 = (Laborart) getFieldValue(fields.get("7266"), owner);
        if (feld7266 == Laborart.Laborgemeinschaft || feld7266 == Laborart.Facharztlabor) {
            if (!containsAnyValue(fields.get("8145"), owner)) {
                return false;
            }
            if (containsAnyValue(fields.get("8153"), owner)) {
                return false;
            }
        }

        return true;
    }

}
