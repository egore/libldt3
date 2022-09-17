/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
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

/**
 * In diesem Objekt können Körperkenngrößen zum Patienten (Größe, Gewicht) übertragen werden.
 */
@Objekt("0069")
public class Koerperkenngroessen {

    @Objekt
    public static class Messwert {
        @SuppressWarnings("unused")
        public Float value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 20)
        public String einheit;
        @Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 17)
        public Timestamp timestamp;
    }
    
    @Feld(value = "3622", feldart = Feldart.kann)
    public Messwert groesse;
    @Feld(value = "3623", feldart = Feldart.kann)
    public Messwert gewicht;

}
