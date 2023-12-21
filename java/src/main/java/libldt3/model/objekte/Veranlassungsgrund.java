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
import libldt3.model.enums.Abrechnungsinfo;
import libldt3.model.enums.SpezifizierungVeranlassungsgrund;
import libldt3.model.enums.Untersuchungsanlass;
import libldt3.model.regel.kontext.K032;
import libldt3.model.regel.kontext.K034;
import libldt3.model.regel.kontext.K060;
import libldt3.model.regel.kontext.K100;
import libldt3.model.regel.kontext.K133;

/**
 * Mit diesem Objekt können Angaben zum Grund der Veranlassung der
 * laboratoriumsmedizinischen Untersuchung übertragen werden.
 */
@Objekt(value = "0027", kontextregeln = {K032.class, K034.class, K060.class})
public class Veranlassungsgrund implements Kontext {

    @Objekt(kontextregeln = K133.class)
    public static class AbrechnungsinfoZurUntersuchung implements Kontext {
        @SuppressWarnings("unused")
        public Abrechnungsinfo value;
        @Feld(value = "8417", feldart = Feldart.kann)
        @Regelsatz(laenge = 2)
        public AnlassUntersuchung anlassUntersuchung;
        @Feld(value = "8200", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public List<Diagnose> akutdiagnose;
        @Feld(value = "4209", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public List<String> zusaetzlicheAngabenZuUntersuchungen;
        @Feld(value = "4208", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public List<VorbefundMedikation> vorbefundMedikation;
    }

    @Objekt
    public static class AnlassUntersuchung implements Kontext {
        @SuppressWarnings("unused")
        public Untersuchungsanlass value;
        @Feld(value = "8427", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 2)
        public SpezifizierungVeranlassungsgrunde spezifizierungVeranlassungsgrunde;
    }

    @Objekt(kontextregeln = K100.class)
    public static class SpezifizierungVeranlassungsgrunde implements Kontext {
        @SuppressWarnings("unused")
        public SpezifizierungVeranlassungsgrund value;
        @Feld(value = "8217", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 32)
        public Fliesstext praezisierung;
    }

    @Objekt
    public static class VorbefundMedikation implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8170", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 10)
        public List<Medikament> medikament;
    }

    @Feld(value = "7303", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 2)
    public List<AbrechnungsinfoZurUntersuchung> abrechnungsinfoZurUntersuchung;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
