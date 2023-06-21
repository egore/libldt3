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
import libldt3.model.enums.Abrechnungsinfo;
import libldt3.model.objekte.Arztidentifikation;
import libldt3.model.objekte.Untersuchungsanforderung;
import libldt3.model.saetze.Auftrag;

/**
 * FK 0222 muss vorhanden sein, wenn in mindestens einem Obj_0059 (Obj_Untersuchungsanforderung) die FK 7303 mit dem
 * Inhalt 8 vorhanden ist.
 */
public class K057 implements Kontextregel {

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {

        Auftrag auftrag = (Auftrag) owner;

        // Valid, as no Obj_0059 present
        if (auftrag.untersuchungsanforderung == null || auftrag.untersuchungsanforderung.isEmpty()) {
            return true;
        }

        for (Untersuchungsanforderung untersuchungsanforderung : auftrag.untersuchungsanforderung) {
            if (untersuchungsanforderung.abrechnungsinfo == Abrechnungsinfo.ASV) {

                // No identification at all, not valid
                if (auftrag.einsenderidentifikation == null) {
                    return false;
                }

                // If any FK 0222 is present, it's valid
                return isNotEmpty(auftrag.einsenderidentifikation.arztidentifikation) ||
                        isNotEmpty(auftrag.einsenderidentifikation.ueberweisungAn) ||
                        isNotEmpty(auftrag.einsenderidentifikation.ueberweisungVon);
            }
        }

        return true;
    }

    public boolean isNotEmpty(Arztidentifikation arztidentifikation) {
        return arztidentifikation.asvTeamnummer != null &&
                !arztidentifikation.asvTeamnummer.isEmpty();
    }

}
