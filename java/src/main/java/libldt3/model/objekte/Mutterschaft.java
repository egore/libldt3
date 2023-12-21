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
package libldt3.model.objekte;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;

/**
 * Das Objekt Mutterschaft fasst die Angaben zur Mutterschaft zusammen.
 */
@Objekt("0040")
public class Mutterschaft implements Kontext {

    @Objekt
    public static class AnzahlSchwangerschaften implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "3664", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 2)
        public String anzahlGeburten;
        @Feld(value = "3666", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 2)
        public String anzahlKinder;
    }

    @Feld(value = "3668", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public AnzahlSchwangerschaften anzahlSchwangerschaften;

}
