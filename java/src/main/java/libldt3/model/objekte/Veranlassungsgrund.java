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
import libldt3.model.enums.Abrechnungsinfo;
import libldt3.model.enums.SpezifizierungVeranlassungsgrund;
import libldt3.model.enums.Untersuchungsanlass;

/**
 * Mit diesem Objekt können Angaben zum Grund der Veranlassung der
 * laboratoriumsmedizinischen Untersuchung übertragen werden.
 */
@Objekt("0027")
public class Veranlassungsgrund implements Kontext {

    @Objekt
    public static class AbrechnungsinfoErweitert implements Kontext {
        @SuppressWarnings("unused")
        public Abrechnungsinfo value;
        @Feld(value = "8417", feldart = Feldart.kann)
        @Regelsatz(laenge = 2)
        public Untersuchungsanlass anlass;
        @Feld(value = "8427", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 2)
        public SpezifizierungVeranlassungsgrund spezifizierung;
        @Feld(value = "8217", name = "Praezisierung_Veranlassungsgrund", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 32)
        public Fliesstext praezisierung;
        @Feld(value = "8200", name = "Akutdiagnose", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 12)
        public List<Diagnose> akutDiagnose;
        @Feld(value = "4208", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public List<Medikation> vorbefundMedikation;
    }

    @Objekt
    public static class Medikation implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "6212", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public List<Arzneimittelwirkstoff> arzneimittelwirkstoff;
    }

    @Objekt
    public static class Arzneimittelwirkstoff implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "6214", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String wirkstoffKlassifikation;
    }

    @Feld(value = "7303", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 2)
    public List<AbrechnungsinfoErweitert> abrechnungsinfo;
    @Feld(value = "8110", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
