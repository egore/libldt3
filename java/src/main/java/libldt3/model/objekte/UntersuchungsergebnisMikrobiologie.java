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
import libldt3.model.enums.ErgebnisStatus;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.KeimArt;
import libldt3.model.enums.Nachweisverfahren;
import libldt3.model.enums.ResistenzMethode;
import libldt3.model.enums.TestStatus;
import libldt3.model.enums.Wachstum;
import libldt3.model.regel.kontext.K010;
import libldt3.model.regel.kontext.K053;
import libldt3.model.regel.kontext.K076;
import libldt3.model.regel.kontext.K082;
import libldt3.model.regel.kontext.K085;
import libldt3.model.regel.kontext.K086;
import libldt3.model.regel.kontext.K096;
import libldt3.model.regel.kontext.K100;

/**
 * In diesem Objekt werden die Ergebnisse aus dem Bereich Mikrobiologie
 * transportiert. Um diese Daten strukturiert zu übertragen wird eine in der
 * Mikrobiologie übliche hierarchische Vorgehensweise definiert: Ausgangspunkt ist
 * immer das Material und die dazugehörige Anforderung. Aus diesen Anforderungen
 * erfolgt über verschiedene Nachweisverfahren eine Stufendiagnostik zur
 * Keimbestimmung, optional die Bestimmung der Breakpunkte bzw.  MHK´s (Minimale
 * Hemm Konzentration) für einzelne Antibiotika. Die Erregermenge wird als
 * semiquantitatives Ergebnis abhängig des Untersuchungsmaterials dargestellt.
 */
@Objekt(value = "0061", kontextregeln = {K010.class, K053.class, K076.class, K082.class, K085.class, K086.class, K096.class, K100.class})
public class UntersuchungsergebnisMikrobiologie implements Kontext {

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
    public static class UntersuchungsergebnisMikrobiologie_Nachweisverfahren implements Kontext {
        @SuppressWarnings("unused")
        public Nachweisverfahren value;
        @Feld(value = "7302", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String testmethode;
    }

    @Objekt(kontextregeln = K100.class)
    public static class KeimPilzIdentifizierung implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7355", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 120)
        public String keimPilzName;
        @Feld(value = "7427", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public KeimArt artObjUntersuchungsergebnisMikrobiologie;
        @Feld(value = "7301", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public ErgebnisStatus ergebnis;
        @Feld(value = "7357", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public KeimPilzIdentifizierung_Wachstum wachstum;
        @Feld(value = "7356", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String oidKeim;
        @Feld(value = "7285", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String keimNummer;
        @Feld(value = "7361", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public KeimIdKatalog keimIdKatalog;
        @Feld(value = "8236", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 21)
        public Fliesstext testbezogeneHinweise;
        @Feld(value = "8225", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 17)
        public Timestamp timestampMessung;
        @Feld(value = "8237", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public Fliesstext ergebnistext;
    }

    @Objekt(kontextregeln = K086.class)
    public static class KeimPilzIdentifizierung_Wachstum implements Kontext {
        @SuppressWarnings("unused")
        public Wachstum value;
        @Feld(value = "7293", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public List<String> einheitMengenangabe;
    }

    @Objekt
    public static class KeimIdKatalog implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7251", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String bezeichnungDesVerwendetenKataloges;
    }

    @Objekt(kontextregeln = K085.class)
    public static class UntersuchungsergebnisMikrobiologie_ResistenzMethode implements Kontext {
        @SuppressWarnings("unused")
        public ResistenzMethode value;
        @Feld(value = "8111", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public Antibiogramm antibiogramm;
    }

    @Feld(value = "7304", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String ergebnisId;
    @Feld(value = "7364", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public List<String> probengefaessIdent;
    @Feld(value = "7260", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public List<KatalogAnforderbareLeistungenId> katalogAnforderbareLeistungenId;
    @Feld(value = "8410", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 20)
    public List<TestIdent> testIdent;
    @Feld(value = "8434", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public List<String> anforderung;
    @Feld(value = "7281", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public List<UntersuchungsergebnisMikrobiologie_Nachweisverfahren> nachweisverfahren;
    @Feld(value = "8418", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public TestStatus ergebnisstatus;
    @Feld(value = "8244", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 3)
    public List<BAK> bak;
    @Feld(value = "7354", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public List<KeimPilzIdentifizierung> keimPilzIdentifizierung;
    @Feld(value = "7286", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public List<UntersuchungsergebnisMikrobiologie_ResistenzMethode> resistenzMethode;
    @Feld(value = "8237", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 12)
    public Fliesstext ergebnistext;
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
    public Timestamp timestampQmErfassungObjUntersuchungsergebnisMikrobiologie;
    @Feld(value = "8225", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 17)
    public Timestamp timestampMessung;
    @Feld(value = "8126", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
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
