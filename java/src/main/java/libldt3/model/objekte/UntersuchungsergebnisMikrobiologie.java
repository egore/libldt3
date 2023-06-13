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
import libldt3.model.enums.Ergebnis;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.Nachweisverfahren;
import libldt3.model.enums.ResistenzMethode;
import libldt3.model.enums.TestStatus;
import libldt3.model.enums.Wachstum;
import libldt3.model.regel.kontext.K076;

/**
 * In diesem Objekt werden die Ergebnisse aus dem Bereich Mikrobiologie
 * transportiert. Um diese Daten strukturiert zu übertragen wird eine in der
 * Mikrobiologie übliche hierarchische Vorgehensweise definiert: Ausgangspunkt
 * ist immer das Material und die dazugehörige Anforderung. Aus diesen
 * Anforderungen erfolgt über verschiedene Nachweisverfahren eine
 * Stufendiagnostik zur Keimbestimmung, optional die Bestimmung der Breakpunkte
 * bzw. MHK´s (Minimale Hemm Konzentration) für einzelne Antibiotika. Die
 * Erregermenge wird als semiquantitatives Ergebnis abhängig des
 * Untersuchungsmaterials dargestellt.
 */
@Objekt(value = "0061", kontextregeln = { K076.class })
public class UntersuchungsergebnisMikrobiologie implements Kontext {

    @Objekt
    public static class Keim implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7355", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String keimName;
        @Feld(value = "7301", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public Ergebnis ergebnis;
        @Feld(value = "7357", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public Wachstum wachstum;
        @Feld(value = "7293", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public List<String> einheit;
        @Feld(value = "7356", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String keimOid;
        @Feld(value = "7285", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String keimNummer;
        @Feld(value = "7361", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String katalogId;
        @Feld(value = "7251", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String katalogBezeichnung;
        @Feld(value = "8236", name = "Testbezogene_Hinweise", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 21)
        public Fliesstext testbezogeneHinweise;
        @Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 17)
        public Timestamp timestamp;
        @Feld(value = "8237", name = "Ergebnistext", feldart = Feldart.kann)
        @Regelsatz(laenge = 12)
        public Fliesstext ergebnistext;
    }

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
        @Feld(value = "7366", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String leistungsbezeichnung;
    }

    @Objekt
    public static class NachweisverfahrenErweitert implements Kontext {
        @SuppressWarnings("unused")
        public Nachweisverfahren value;
        @Feld(value = "7302", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String testmethode;
    }

    @Objekt
    public static class ResistenzMethodeErweitert implements Kontext {
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
    public KatalogReferenz anforderbareLeistungenKatalogId;
    @Feld(value = "8410", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String testIdent;
    @Feld(value = "8411", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String testbezeichnung;
    @Feld(value = "8434", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String anforderung;
    @Feld(value = "7281", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public List<NachweisverfahrenErweitert> nachweisverfahren;
    @Feld(value = "8418", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public TestStatus teststatus;
    @Feld(value = "7354", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public List<Keim> keime;
    @Feld(value = "7286", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public List<ResistenzMethodeErweitert> resistenzMethode;
    @Feld(value = "8142", feldart = Feldart.kann)
    @Regelsatz(laenge = 10)
    public List<Normalwert> normalValue;
    @Feld(value = "8237", name = "Ergebnistext", feldart = Feldart.kann)
    @Regelsatz(laenge = 12)
    public Fliesstext ergebnistext;
    @Feld(value = "8220", name = "Timestamp_Eingangserfassung_Material", feldart = Feldart.kann)
    @Regelsatz(laenge = 36)
    public Timestamp materialDeliveryTimestamp;
    @Feld(value = "8222", name = "Timestamp_Beginn_Analytik", feldart = Feldart.kann)
    @Regelsatz(laenge = 25)
    public Timestamp startAnalyticsTimestamp;
    @Feld(value = "8223", name = "Timestamp_Ergebniserstellung", feldart = Feldart.kann)
    @Regelsatz(laenge = 28)
    public Timestamp resultTimestamp;
    @Feld(value = "8224", name = "Timestamp_QM_Erfassung", feldart = Feldart.kann)
    @Regelsatz(laenge = 22)
    public Timestamp qmTimestamp;
    @Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 17)
    public Timestamp timestampMessung;
    @Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8141", feldart = Feldart.muss)
    @Regelsatz(laenge = 13)
    public Namenskennung namenskennung;
    @Feld(value = "8158", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 23)
    public Untersuchungsabrechnung untersuchungsabrechnung;
    @Feld(value = "8110", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
