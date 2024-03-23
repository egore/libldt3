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
import libldt3.model.enums.Betriebsstaettenstatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

/**
 * Wenn FK 0204 im Obj_0019 (Obj_Betriebsstätte) nur einmal vorkommt, muss der
 * Inhalt der FK 0204 = 1, 2, 3 oder 4 sein. Wenn FK 0204 im Obj_0019
 * (Obj_Betriebsstätte) zweimal vorkommt, muss der Inhalt der FK 0204 einmal mit 1,
 * 2, 3 oder 4 und einmal mit 5 oder 6 gefüllt sein. Im Obj_0019
 * (Obj_Betriebsstätte) darf die FK 0204 maximal zweimal vorkommen.
 *
 * Im Obj_0019 (Obj_Betriebsstätte) ist mit der FK 0204 (Status der
 * Betriebsstätte) zwingend anzugeben, ob es sich bei der Betriebsstätte um
 * eine Arztpraxis (1), eine Laborarztpraxis (2), eine Laborgemeinschaft (3)
 * oder eine sonstige medizinische Einrichtung (4) handelt.
 * Ergänzend kann mit einem zweiten Vorkommen der FK 0204 (Status der
 * Betriebsstätte) angegeben werden, ob es sich bei der oben
 * beschriebenen Betriebsstätte um eine Hauptbetriebsstätte (5) oder eine
 * Nebenbetriebsstätte (6) handelt.
 * Die Feldkennung FK 0204 (Status der Betriebsstätte) darf maximal
 * zweimal im Obj_Betriebsstätte vorkommen.
 */
public class K043 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K043.class);

    private static final Set<String> FIELDTYPES = Set.of("0204");

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        List<Betriebsstaettenstatus> stati = (List<Betriebsstaettenstatus>) fields.get("0204").get(owner);
        if (stati == null || stati.isEmpty()) {
            LOG.error("Requires one or two states, got none");
            return false;
        }

        // Wenn FK 0204 im Obj_0019 (Obj_Betriebsstätte) nur einmal vorkommt, muss der Inhalt der FK 0204 = 1, 2, 3 oder
        // 4 sein.
        if (stati.size() == 1) {
            for (Betriebsstaettenstatus status : stati) {
                if (status == Betriebsstaettenstatus.Arztpraxis || status == Betriebsstaettenstatus.Laborarztpraxis ||
                status == Betriebsstaettenstatus.Laborgemeinschaft || status == Betriebsstaettenstatus.sonstige_medizinischeEinrichtung) {
                    return true;
                }
            }
            LOG.error("Only one state given, need to be 1, 2, 3 or 4");
            return false;
        }

        // Wenn FK 0204 im Obj_0019 (Obj_Betriebsstätte) zweimal vorkommt, muss der Inhalt der FK 0204 einmal mit 1, 2,
        // 3 oder 4 und einmal mit 5 oder 6 gefüllt sein.
        if (stati.size() == 2) {
            boolean oneToFour = false;
            boolean fiveOrSix = false;
            for (Betriebsstaettenstatus status : stati) {
                if (status == Betriebsstaettenstatus.Arztpraxis || status == Betriebsstaettenstatus.Laborarztpraxis ||
                        status == Betriebsstaettenstatus.Laborgemeinschaft || status == Betriebsstaettenstatus.sonstige_medizinischeEinrichtung) {
                    oneToFour = true;
                } else if (status == Betriebsstaettenstatus.Hauptbetriebsstaette || status == Betriebsstaettenstatus.Nebenbetriebsstaette) {
                    fiveOrSix = true;
                }
            }
            if (oneToFour && fiveOrSix) {
                return true;
            }
            LOG.error("Two states given, need to be 1, 2, 3 or 4 and 5 or 6");
            return false;
        }

        LOG.error("Requires one or two states, got {}", stati.size());
        return false;
    }

}
