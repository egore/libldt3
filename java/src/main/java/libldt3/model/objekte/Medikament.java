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

import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.MedikationsStatus;

/**
 * Hier werden Informationen zu Medikamenten zusammengefasst.
 */
@Objekt("0070")
public class Medikament implements Kontext {

    @Feld(value = "8243", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 40)
    public Timestamp timestampZeitpunktMedikamenteneinnahme;
    @Feld(value = "6208", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String handelsname;
    @Feld(value = "6207", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 990)
    public String rezeptur;
    @Feld(value = "8171", feldart = Feldart.kann)
    @Regelsatz(laenge = 9)
    public List<Wirkstoff> wirkstoff;
    @Feld(value = "8523", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String wirkstoffmenge;
    @Feld(value = "8421", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 20)
    public String einheit;
    @Feld(value = "3689", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public List<MedikationsStatus> status;
    @Feld(value = "8226", name = "Timestamp_Gueltig_ab", feldart = Feldart.kann)
    @Regelsatz(laenge = 20)
    public Timestamp timestampGueltigAb;
    @Feld(value = "8227", name = "Timestamp_Gueltig_bis", feldart = Feldart.kann)
    @Regelsatz(laenge = 21)
    public Timestamp timestampGueltigBis;
    @Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public Fliesstext zusaetzlicheInformationen;

}
