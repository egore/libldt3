/*
 * Copyright 2016-2023  Christoph Brill <opensource@christophbrill.de>
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
import libldt3.model.regel.erlaubt.E012;
import libldt3.model.regel.kontext.K003;
import libldt3.model.regel.kontext.K010;
import libldt3.model.regel.kontext.K011;
import libldt3.model.regel.kontext.K032;
import libldt3.model.regel.kontext.K034;
import libldt3.model.regel.kontext.K037;
import libldt3.model.regel.kontext.K053;
import libldt3.model.regel.kontext.K056;
import libldt3.model.regel.kontext.K057;
import libldt3.model.regel.kontext.K097;
import libldt3.model.regel.kontext.K098;
import libldt3.model.regel.kontext.K100;
import libldt3.model.regel.kontext.K102;
import libldt3.model.regel.kontext.K103;
import libldt3.model.regel.kontext.K105;
import libldt3.model.regel.kontext.K113;
import libldt3.model.regel.kontext.K114;

/**
 * In diesem Objekt werden alle Informationen zur Untersuchungsanforderung
 * zusammengefasst.
 */
@Objekt(value = "0059", kontextregeln = {K003.class, K010.class, K011.class, K032.class, K034.class, K037.class, K053.class, K056.class, K057.class, K097.class, K098.class, K100.class, K102.class, K103.class, K105.class, K113.class, K114.class})
public class Untersuchungsanforderung implements Kontext {

    @Objekt(kontextregeln = K053.class)
    public static class KatalogAnforderbareLeistungenId implements Kontext {
        @SuppressWarnings("unused")
        public KatalogIdAnforderbareLeistungen value;
        @Feld(value = "7352", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String urlKataloge;
        @Feld(value = "7251", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String bezeichnungDesVerwendetenKataloges;
        @Feld(value = "7365", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 20)
        public AnalysenId analysenId;
    }

    @Objekt
    public static class AnalysenId implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7366", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String langbezeichnungAngefordertenLeistung;
    }

    @Objekt(kontextregeln = K003.class)
    public static class TestIdent implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8411", feldart = Feldart.bedingt_muss)
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
        @Regelsatz(value = E012.class, maxLaenge = 4)
        public String probenmaterialIndex;
    }

    @Objekt
    public static class EinwilligungserklaerungDesPatientenLiegtVor implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "8110", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 6)
        public Anhang anhang;
    }

    @Feld(value = "7260", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public KatalogAnforderbareLeistungenId katalogAnforderbareLeistungenId;
    @Feld(value = "7276", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String verwendeterNummernpoolId;
    @Feld(value = "8410", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 20)
    public TestIdent testIdent;
    @Feld(value = "7303", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 2)
    public Abrechnungsinfo abrechnungsinfoZurUntersuchung;
    @Feld(value = "8501", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Dringlichkeit dringlichkeit;
    @Feld(value = "8423", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean pathologischBekannt;
    @Feld(value = "7364", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public List<ProbengefaessIdent> probengefaessIdent;
    @Feld(value = "8434", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public String anforderungen;
    @Feld(value = "8134", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 35)
    public KrebsfrueherkennungZervixKarzinom krebsfrueherkennungZervixKarzinom;
    @Feld(value = "8156", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 5)
    public Tumor tumor;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8238", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 25)
    public Fliesstext auftragsbezogeneHinweise;
    @Feld(value = "8491", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public EinwilligungserklaerungDesPatientenLiegtVor einwilligungserklaerungDesPatientenLiegtVor;
    @Feld(value = "8213", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 45)
    public Timestamp timestampErstellung;
    @Feld(value = "8141", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 13)
    public Namenskennung namenskennung;

}
