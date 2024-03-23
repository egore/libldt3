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
import libldt3.model.regel.format.F002;
import libldt3.model.regel.format.F005;
import libldt3.model.regel.format.F018;
import libldt3.model.regel.kontext.K118;

/**
 * Dieses Objekt enthält schwangerschaftsspezifische Informationen.
 */
@Objekt(value = "0050", kontextregeln = K118.class)
public class Schwangerschaft implements Kontext {

    @Objekt
    public static class LetztePeriode implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "3471", feldart = Feldart.bedingt_kann)
        @Regelsatz(value = F002.class, laenge = 8)
        public LocalDate errechneterEntbindungstermin;
    }

    @Feld(value = "8511", feldart = Feldart.kann)
    @Regelsatz(value = F005.class, laenge = 3)
    public String schwangerschaftsdauer;
    @Feld(value = "8512", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F018.class, laenge = 8)
    public LetztePeriode letztePeriode;

}
