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
import libldt3.model.enums.DarstellungErgebniswerte;
import libldt3.model.enums.EinheitMesswert;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.TestStatus;
import libldt3.model.regel.kontext.K002;
import libldt3.model.regel.kontext.K053;
import libldt3.model.regel.kontext.K054;
import libldt3.model.regel.kontext.K076;
import libldt3.model.regel.kontext.K082;
import libldt3.model.regel.kontext.K096;
import libldt3.model.regel.kontext.K100;
import libldt3.model.regel.kontext.K106;

/**
 * In diesem Objekt werden die Ergebnisse aus dem Bereich Klinische Chemie
 * übermittelt.
 */
@Objekt(value = "0060", kontextregeln = {K053.class, K076.class, K082.class, K096.class, K106.class})
public class UntersuchungsergebnisKlinischeChemie implements Kontext {

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
        @Feld(value = "7263", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public String testId;
        @Feld(value = "7264", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public String testGeraetUid;
    }

    @Objekt
    public static class Ergebnisstatus implements Kontext {
        @SuppressWarnings("unused")
        public TestStatus value;
        @Feld(value = "7302", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public List<String> testmethode;
    }

    @Objekt(kontextregeln = K100.class)
    public static class UntersuchungsergebnisKlinischeChemie_DarstellungErgebniswerte implements Kontext {
        @SuppressWarnings("unused")
        public DarstellungErgebniswerte value;
        @Feld(value = "8420", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public List<ErgebnisWert> ergebnisWert;
        @Feld(value = "8236", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 21)
        public Fliesstext testbezogeneHinweise;
    }

    @Objekt(kontextregeln = {K002.class, K054.class, K076.class, K100.class})
    public static class ErgebnisWert implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8419", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public EinheitensystemMesswerteWertes einheitensystemMesswerteWertes;
        @Feld(value = "8142", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 10)
        public List<Normalwert> normalwert;
        @Feld(value = "8225", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 17)
        public Timestamp timestampMessungObjUntersuchungsergebnisKlinischeChemie;
        @Feld(value = "8237", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public Fliesstext ergebnistext;
    }

    @Objekt(kontextregeln = K002.class)
    public static class EinheitensystemMesswerteWertes implements Kontext {
        @SuppressWarnings("unused")
        public EinheitMesswert value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String masseinheitMesswerteWertes;
    }

    @Feld(value = "7304", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String ergebnisId;
    @Feld(value = "7364", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public List<String> probengefaessIdent;
    @Feld(value = "7260", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public KatalogAnforderbareLeistungenId katalogAnforderbareLeistungenId;
    @Feld(value = "8410", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 20)
    public TestIdent testIdent;
    @Feld(value = "8418", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public Ergebnisstatus ergebnisstatus;
    @Feld(value = "7306", feldart = Feldart.kann)
    @Regelsatz(laenge = 2)
    public List<UntersuchungsergebnisKlinischeChemie_DarstellungErgebniswerte> darstellungErgebniswerte;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
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
    @Feld(value = "8126", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
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
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
