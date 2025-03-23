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

import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Ergebnis;
import libldt3.model.enums.Ergebnis2;
import libldt3.model.enums.Grenzwertindikator;
import libldt3.model.enums.HpvHrTestergebnis;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.TestStatus;
import libldt3.model.regel.erlaubt.E028;
import libldt3.model.regel.format.F024;
import libldt3.model.regel.kontext.K053;
import libldt3.model.regel.kontext.K076;
import libldt3.model.regel.kontext.K080;
import libldt3.model.regel.kontext.K081;
import libldt3.model.regel.kontext.K082;
import libldt3.model.regel.kontext.K096;
import libldt3.model.regel.kontext.K099;
import libldt3.model.regel.kontext.K100;
import libldt3.model.regel.kontext.K119;

/**
 * In diesem Objekt können weitere Ergebnisse aus dem Bereich Zytologie
 * transportiert werden.
 */
@Objekt(value = "0063", kontextregeln = {K053.class, K076.class, K080.class, K081.class, K096.class, K100.class, K119.class})
public class UntersuchungsergebnisZytologie implements Kontext {

    @Objekt
    public static class ErgebnisId implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7320", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public RecallEmpfohlen recallEmpfohlen;
    }

    @Objekt
    public static class RecallEmpfohlen implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "8154", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 9)
        public Timestamp timestamp;
    }

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
        @Feld(value = "7366", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String langbezeichnungAngefordertenLeistung;
    }

    @Objekt
    public static class TestIdent implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8411", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String testbezeichnung;
    }

    @Objekt
    public static class Ergebnisstatus implements Kontext {
        @SuppressWarnings("unused")
        public TestStatus value;
        @Feld(value = "8422", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 2)
        public List<GrenzwertindikatorLaborwerte> grenzwertindikatorLaborwerte;
    }

    @Objekt(kontextregeln = K099.class)
    public static class GrenzwertindikatorLaborwerte implements Kontext {
        @SuppressWarnings("unused")
        public Grenzwertindikator value;
        @Feld(value = "8126", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 28)
        public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
    }

    @Objekt
    public static class HpvBefundObjUntersuchungsergebnisZytologie implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "7402", feldart = Feldart.bedingt_kann)
        @Regelsatz(value = F024.class, maxLaenge = 120)
        public List<HighRiskTyp> highRiskTyp;
        @Feld(value = "7404", feldart = Feldart.bedingt_kann)
        @Regelsatz(value = F024.class, maxLaenge = 120)
        public List<LowRiskTyp> lowRiskTyp;
    }

    @Objekt
    public static class HighRiskTyp implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7401", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public HpvHrTestergebnis highRisk;
    }

    @Objekt
    public static class LowRiskTyp implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7403", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public HpvHrTestergebnis lowRisk;
    }

    @Objekt
    public static class Gruppe implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7413", feldart = Feldart.bedingt_muss)
        @Regelsatz(value = E028.class, maxLaenge = 4)
        public String codierungGruppe;
    }

    @Feld(value = "7304", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public ErgebnisId ergebnisId;
    @Feld(value = "7364", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public List<String> probengefaessIdent;
    @Feld(value = "7260", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public List<KatalogAnforderbareLeistungenId> katalogAnforderbareLeistungenId;
    @Feld(value = "8410", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 20)
    public List<TestIdent> testIdent;
    @Feld(value = "8418", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public Ergebnisstatus ergebnisstatus;
    @Feld(value = "8237", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 12)
    public Fliesstext ergebnistext;
    @Feld(value = "7368", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean zellmaterialNichtVerwertbar;
    @Feld(value = "7400", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public HpvBefundObjUntersuchungsergebnisZytologie hpvBefundObjUntersuchungsergebnisZytologie;
    @Feld(value = "7414", feldart = Feldart.kann)
    @Regelsatz(value = E028.class, maxLaenge = 5)
    public Gruppe gruppe;
    @Feld(value = "7418", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Ergebnis p16Ki67;
    @Feld(value = "7419", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Ergebnis l1;
    @Feld(value = "7422", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Ergebnis chlamydien;
    @Feld(value = "7425", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Ergebnis2 extragynaekologischeZytologie;
    @Feld(value = "7426", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Ergebnis neisseriaGonorrhoeae;
    @Feld(value = "8126", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
    @Feld(value = "8220", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 36)
    public Timestamp timestampEingangserfassungMaterial;
    @Feld(value = "8222", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 25)
    public Timestamp timestampBeginnAnalytik;
    @Feld(value = "8223", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public Timestamp timestampErgebniserstellung;
    @Feld(value = "8224", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 22)
    public Timestamp timestampQmErfassung;
    @Feld(value = "8225", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 17)
    public Timestamp timestampMessung;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhangObjUntersuchungsergebnisZytologie;
    @Feld(value = "8141", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 13)
    public Namenskennung namenskennung;
    @Feld(value = "8158", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 23)
    public Untersuchungsabrechnung untersuchungsabrechnung;
    @Feld(value = "7429", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 990)
    public String drgHinweis;
    @Feld(value = "3473", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean untersuchungsergebnisDurchAuftragslaboratoriumErstellt;

}
