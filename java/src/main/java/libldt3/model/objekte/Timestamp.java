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

import java.time.LocalDate;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Zeitzone;
import libldt3.model.regel.format.F002;
import libldt3.model.regel.format.F016;

/**
 * Obj_Timestamp
 */
@Objekt("0054")
public class Timestamp implements Kontext {

    @Objekt
    public static class Uhrzeit implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7273", feldart = Feldart.bedingt_muss)
        @Regelsatz(minLaenge = 3, maxLaenge = 9)
        public Zeitzone zeitzone;
    }

    @Feld(value = "7278", feldart = Feldart.muss)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate datum;
    @Feld(value = "7279", feldart = Feldart.kann)
    @Regelsatz(value = F016.class, minLaenge = 6, maxLaenge = 9)
    public Uhrzeit uhrzeit;
    @Feld(value = "7272", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 990)
    public String freitext;
    @Feld(value = "8235", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 20)
    public Person person;

}
