/*
 * Copyright 2016-2017  Christoph Brill &lt;opensource@christophbrill.de&gt;
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

import libldt3.model.enums.Dokumententyp;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyValue;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Wenn Inhalt von FK 9970 = 999, dann muss FK 6327 vorkommen.
 *
 * Wird beim Dokumententyp "sonstige" angegeben, muss das Dokument
 * mittels der FK 6327 näher beschrieben werden.
 */
public class K075 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K075.class);

    private static final Set<String> FIELDTYPES = Set.of("9970", "6327");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        if (((Dokumententyp) fields.get("9970").get(owner)) == Dokumententyp.sonstige) {
            return containsAnyValue(fields.get("6327"), owner);
        }

        return true;
    }

}
