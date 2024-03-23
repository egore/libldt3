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
package libldt3.model.saetze;

import java.util.List;

import libldt3.annotations.Datenpaket;
import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Satzart;
import libldt3.model.enums.DatensatzAbsender;
import libldt3.model.objekte.Einsenderidentifikation;
import libldt3.model.objekte.Kopfdaten;

/**
 * Satzart: P (Praxis)-Datenpaket-Header "8230"
 */
@Datenpaket(Satzart.PraxisDatenpaketHeader)
public class PraxisDatenpaketHeader implements Satz, Kontext {

    @Feld(value = "8132", feldart = Feldart.muss)
    @Regelsatz(laenge = 9)
    public Kopfdaten kopfdaten;
    @Feld(value = "7265", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public DatensatzAbsender absenderDatensatze;
    @Feld(value = "8122", feldart = Feldart.muss)
    @Regelsatz(laenge = 23)
    public List<Einsenderidentifikation> einsenderidentifikation;

}
