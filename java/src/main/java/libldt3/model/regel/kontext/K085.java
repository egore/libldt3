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

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyValue;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;
import static libldt3.model.regel.kontext.KontextregelHelper.getFieldValue;

import libldt3.model.Kontext;
import libldt3.model.enums.ResistenzMethode;
import libldt3.model.objekte.UntersuchungsergebnisMikrobiologie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * FK 8111 kann nur vorkommen, wenn FK 7286 mit Inhalt ≠ 0 vorkommt.
 */
public class K085 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K085.class);

    private static final Set<String> FIELDTYPES = Set.of("8111", "7286");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        List<UntersuchungsergebnisMikrobiologie.UntersuchungsergebnisMikrobiologie_ResistenzMethode> feld7286 = (List<UntersuchungsergebnisMikrobiologie.UntersuchungsergebnisMikrobiologie_ResistenzMethode>) getFieldValue(fields.get("7286"), owner);

        if (feld7286 != null) {
            boolean found = feld7286.stream()
                    .anyMatch(x -> x.value != ResistenzMethode.keinAntibiogramm_erstellt);
            if (!found) {
                if (containsAnyValue(fields.get("8111"), owner)) {
                    return false;
                }
            }
        }

        return true;
    }

}
