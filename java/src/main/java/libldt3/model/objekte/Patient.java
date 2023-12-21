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

import java.time.LocalDate;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.GeschlechtNormalbereich;
import libldt3.model.regel.format.F002;
import libldt3.model.regel.format.F013;
import libldt3.model.regel.kontext.K025;
import libldt3.model.regel.kontext.K090;
import libldt3.model.regel.kontext.K091;
import libldt3.model.regel.kontext.K104;

/**
 * In diesem Objekt werden die Informationen über einen Patienten aufgeführt.
 */
@Objekt(value = "0045", kontextregeln = {K025.class, K090.class, K091.class, K104.class})
public class Patient implements Kontext {

    @Feld(value = "8147", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public Person person;
    @Feld(value = "3119", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F013.class, laenge = 10)
    public String versichertenId;
    @Feld(value = "3105", feldart = Feldart.bedingt_muss)
    @Regelsatz(minLaenge = 6, maxLaenge = 12)
    public String versichertennummer;
    @Feld(value = "7329", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public GeschlechtNormalbereich normalbereichsrelevantesGeschlecht;
    @Feld(value = "7922", feldart = Feldart.kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate sterbedatumDesPatienten;
    @Feld(value = "3000", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String patientennummer;

}
