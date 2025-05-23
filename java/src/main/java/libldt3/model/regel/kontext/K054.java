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

/**
 * Wenn Obj_0042 (Obj_Normalwert) mehr- fach im Obj_0060 (Obj_Untersuchungsergebnis
 * Klinische Chemie) bzw. Obj_0061 (Obj_Untersuchungsergebnis Mikrobiologie)
 * vorkommt, darf der Wert 13 in der FK 8424 mehrfach vorkommen, alle anderen Werte
 * dürfen nur jeweils einmal vorkommen.
 *
 * Falls
 * für
 * ein
 * Untersuchungsergebnis
 * verschiedene
 * Normalwerte
 * angegeben werden, müssen sich die Normalwerte innerhalb eines
 * Untersuchungsergebnisses hinsichtlich der Normalwertspezifikation
 * unterscheiden. Ausgenommen davon sind die Normalwertspezifikationen,
 * die auf "Sonstige Standards" referenzieren.
 */
public class K054 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K054.class);

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {
        LOG.warn("Ignoring rule {}", this.getClass().getSimpleName());
        return true;
    }

}
