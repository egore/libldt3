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
import libldt3.model.enums.Dringlichkeit;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.StatusDringlichkeit;
import libldt3.model.regel.kontext.K003;

/**
 * In diesem Objekt werden alle Informationen zur Untersuchungsanforderung
 * zusammengefasst.
 */
@Objekt(value = "0059", kontextregeln = { K003.class })
public class Untersuchungsanforderung implements Kontext {

    @Objekt
    public static class KatalogReferenz implements Kontext {
        @SuppressWarnings("unused")
        public KatalogIdAnforderbareLeistungen value;
        @Feld(value = "7352", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String katalogUrl;
        @Feld(value = "7251", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String katalogBezeichnung;
        @Feld(value = "7365", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 20)
        public String analysenId;
        @Feld(value = "7366", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String leistungsbezeichnung;
    }

    @Objekt
    public static class Test implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8411", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String testbezeichnung;
    }

    @Objekt
    public static class ProbengefaessIdent implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8428", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public String probenmaterialIdent;
        @Feld(value = "8429", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 4)
        public String probenmaterialIndex;
    }

    @Objekt
    public static class Einwilligungserklaerung implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "8110", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 6)
        public Anhang anhang;
    }

    @Feld(value = "7260", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public KatalogReferenz anforderbareLeistungenKatalogId;
    @Feld(value = "7276", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String nummernpoolId;
    @Feld(value = "8410", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public Test testIdent;
    @Feld(value = "7303", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 2)
    public Abrechnungsinfo abrechnungsinfo;
    @Feld(value = "8501", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Dringlichkeit dringlichkeit;
    @Feld(value = "7262", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public StatusDringlichkeit statusDringlichkeit;
    @Feld(value = "8423", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean pathologischBekannt;
    @Feld(value = "7364", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public List<ProbengefaessIdent> probengefaessIdent;
    @Feld(value = "8434", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String anforderungen;
    @Feld(value = "8134", name = "Krebsfrueherkennung_Frauen", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public KrebsfrueherkennungFrauen krebsfrueherkennungFrauen;
    @Feld(value = "8156", feldart = Feldart.kann)
    @Regelsatz(laenge = 5)
    public Tumor tumor;
    @Feld(value = "8110", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;
    @Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8238", name = "Auftragsbezogene_Hinweise", feldart = Feldart.kann)
    @Regelsatz(laenge = 25)
    public Fliesstext auftragsbezogeneHinweise;
    @Feld(value = "8491", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Einwilligungserklaerung einwilligungserklaerungLiegtVor;
    @Feld(value = "8213", name = "Timestamp_Erstellung_Untersuchungsanforderung", feldart = Feldart.muss)
    @Regelsatz(laenge = 45)
    public Timestamp timestampErstellungUntersuchungsanforderung;
    @Feld(value = "8141", feldart = Feldart.kann)
    @Regelsatz(laenge = 13)
    public Namenskennung namenskennung;

}
