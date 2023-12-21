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

import libldt3.model.Kontext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wenn der Inhalt von  FK 8002 = Obj_0058 (Obj_Untersuchungsabrechnung) und der
 * Inhalt FK 7303 = 1, 2, 8, 9 oder 10 dann sind als Inhalte FK 4121 nur 0, 1, 2
 * oder 3 erlaubt.
 *
 * Für die Abrechnung von Leistungen, die im Bereich der kassenärztlichen
 * Versorgung (Laborfacharzt, Laborgemeinschaft und ASV) erbracht
 * wurden,
 * können
 * nur
 * der
 * EBM,
 * BMÄ,
 * EGO
 * oder
 * GOÄ
 * als
 * Gebührenordnung angegeben werden.
 */
public class K008 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K008.class);

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {
        LOG.warn("Ignoring rule {}", this.getClass().getSimpleName());
        return true;
    }

}
