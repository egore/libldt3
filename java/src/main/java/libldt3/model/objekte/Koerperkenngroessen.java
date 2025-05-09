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
package libldt3.model.objekte;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;

/**
 * In diesem Objekt können Körperkenngrößen zum Patienten (Größe, Gewicht)
 * übertragen werden.
 */
@Objekt("0069")
public class Koerperkenngroessen implements Kontext {

    @Objekt
    public static class GroesseDesPatienten implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String masseinheitMesswerteWertes;
        @Feld(value = "8225", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 17)
        public Timestamp timestampMessung;
    }

    @Objekt
    public static class GewichtDesPatienten implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String masseinheitMesswerteWertes;
        @Feld(value = "8225", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 17)
        public Timestamp timestampMessung;
    }

    @Feld(value = "3622", feldart = Feldart.kann)
    public GroesseDesPatienten groesseDesPatienten;
    @Feld(value = "3623", feldart = Feldart.kann)
    public GewichtDesPatienten gewichtDesPatienten;

}
