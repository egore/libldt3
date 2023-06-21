/*
 * Copyright 2016-2023  Christoph Brill <opensource@christophbrill.de>
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
import libldt3.model.enums.StatusRechnungsempfaenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;
import static libldt3.model.regel.kontext.KontextregelHelper.findFieldsRecursive;
import static libldt3.model.regel.kontext.KontextregelHelper.getFieldValue;

/**
 * Wenn Inhalt von FK 7421 = 15, 16 oder 90 dann muss FK 8143 vorhanden sein.
 */
public class K029 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K029.class);

    private static final Set<String> FIELDTYPES = Set.of("7421", "8143");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        StatusRechnungsempfaenger status = (StatusRechnungsempfaenger) getFieldValue(fields.get("7421"), owner);
        if (status == StatusRechnungsempfaenger.staatlicheEinrichtung ||
                status == StatusRechnungsempfaenger.sonstige_juristischePerson ||
                status == StatusRechnungsempfaenger.sonstige_medizinischeEinrichtung) {
            if (!containsAnyString(fields.get("8143"), owner)) {
                LOG.error("Status {} requires field 8143 to have a value", status);
                return false;
            }
        }

        return true;
    }

}
