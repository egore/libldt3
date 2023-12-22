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

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import libldt3.model.Kontext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wenn der Inhalt von FK 8002 = Obj_0035 (Obj_Laborergebnisbericht), dann muss
 * mindestens eine FK 8002 mit den Werten Obj_0060(Obj_Untersuchungsergebnis
 * Klinische Chemie), Obj_0061(Obj_Untersuchungsergebnis Mikrobiologie),
 * Obj_0062(Obj_Untersuchungsergebnis Krebsfrueherkennung Zervix-Karzinom),
 * Obj_0063(Obj_Untersuchungsergebnis Zytologie), Obj_0073(Sonstige
 * Untersuchungsergebnisse) oder Obj_0055(Obj_Blutgruppenzugehoerigkeit) vorhanden
 * sein.
 */
public class K009 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K009.class);

    private static final Set<String> FIELDTYPES = Set.of("8002");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        String feld8002 = (String) getFieldValue(fields.get("8002"), owner);

        // Wenn Inhalt von FK8002=0035 , muss FK8002 0060 , 0061 , 0062 , 0063 , 0073 oder 0055 vorhanden sein .
        if (feld8002.equals("0035")) {
            return containsAnyValue(fields.get("8002"), owner);
        }

        return true;
    }

}
