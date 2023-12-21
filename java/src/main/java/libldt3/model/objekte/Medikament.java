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

import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.MedikationsStatus;
import libldt3.model.regel.format.F020;

/**
 * Hier werden Informationen zu Medikamenten zusammengefasst.
 */
@Objekt("0070")
public class Medikament implements Kontext {

    @Objekt
    public static class Rezeptur implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8171", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 9)
        public List<Wirkstoff> wirkstoff;
        @Feld(value = "6206", feldart = Feldart.bedingt_kann)
        @Regelsatz(value = F020.class, laenge = 8)
        public String pharmazentralnummer;
    }

    @Objekt
    public static class WirkstoffmengeMengeBezugsmengeWirkstaerke implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String masseinheitMesswerteWertes;
    }

    @Feld(value = "8243", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 40)
    public Timestamp timestampZeitpunktMedikamenteneinnahme;
    @Feld(value = "6208", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String handelsnameArzneimittel;
    @Feld(value = "6207", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 990)
    public Rezeptur rezeptur;
    @Feld(value = "8523", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public WirkstoffmengeMengeBezugsmengeWirkstaerke wirkstoffmengeMengeBezugsmengeWirkstaerke;
    @Feld(value = "3689", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public List<MedikationsStatus> statusMedikation;
    @Feld(value = "8226", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 20)
    public Timestamp timestampGueltigAb;
    @Feld(value = "8227", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 21)
    public Timestamp timestampGueltigBis;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public Fliesstext zusaetzlicheInformationen;

}
