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
import libldt3.model.enums.Auftragsstatus;
import libldt3.model.enums.Beauftragungsgrund;
import libldt3.model.enums.Bestaetigungsdiagnostik;
import libldt3.model.enums.BetroffeneEinrichtung;
import libldt3.model.enums.TestungRechtsgrundlage;
import libldt3.model.enums.Testungen;
import libldt3.model.enums.Virusvariantendiagnostik;
import libldt3.model.enums.ZusaetzlicherBefundweg;
import libldt3.model.regel.kontext.K005;
import libldt3.model.regel.kontext.K096;
import libldt3.model.regel.kontext.K112;
import libldt3.model.regel.kontext.K130;
import libldt3.model.regel.kontext.K131;
import libldt3.model.regel.kontext.K132;
import libldt3.model.regel.kontext.K135;

/**
 * Dieses Objekt bündelt alle Daten zum Befund inklusive aller Kennungen, welche
 * eine eineindeutige Zuordnung von Auftrag und Befund sicherstellen.
 */
@Objekt(value = "0017", kontextregeln = {K112.class, K130.class, K131.class, K132.class, K135.class})
public class Befundinformationen implements Kontext {

    @Objekt
    public static class AuftragsnummerEinsender implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8313", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public List<String> nachforderungId;
    }

    @Objekt(kontextregeln = {K131.class, K132.class})
    public static class RechtsgrundlageTestung implements Kontext {
        @SuppressWarnings("unused")
        public TestungRechtsgrundlage value;
        @Feld(value = "8627", feldart = Feldart.bedingt_muss)
        @Regelsatz(minLaenge = 1, maxLaenge = 5)
        public String kvSonderziffer;
        @Feld(value = "8617", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public Beauftragungsgrund beauftragungsgrund;
        @Feld(value = "4111", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 9)
        public String kostentraegerkennung;
    }

    @Objekt(kontextregeln = {K005.class, K096.class})
    public static class AuftragsnummerLaborId implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7305", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String befundId;
        @Feld(value = "8401", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public Auftragsstatus status;
    }

    @Objekt
    public static class DerFallakteOderStudieId implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "0081", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public List<String> bezeichnungFallakteOderStudie;
    }

    @Objekt
    public static class KatalogDurchgefuehrteLeistungenId implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7251", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public String bezeichnungDesVerwendetenKataloges;
    }

    @Objekt
    public static class Befundinformationen_ZusaetzlicherBefundweg implements Kontext {
        @SuppressWarnings("unused")
        public ZusaetzlicherBefundweg value;
        @Feld(value = "8147", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 6)
        public Person person;
    }

    @Objekt
    public static class RecallEmpfohlen implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "8154", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 9)
        public Timestamp timestamp;
    }

    @Feld(value = "8310", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public AuftragsnummerEinsender auftragsnummerEinsender;
    @Feld(value = "8214", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 27)
    public Timestamp timestampAuftragserteilung;
    @Feld(value = "8215", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 25)
    public Timestamp timestampAuftragseingang;
    @Feld(value = "8616", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Testungen testung;
    @Feld(value = "8626", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public RechtsgrundlageTestung rechtsgrundlageTestung;
    @Feld(value = "8631", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Bestaetigungsdiagnostik bestaetigungsdiagnostik;
    @Feld(value = "8632", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public Virusvariantendiagnostik virusvariantendiagnostik;
    @Feld(value = "8618", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public Boolean betreutUntergebrachtIn;
    @Feld(value = "8619", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public Boolean taetigkeitInEinrichtung;
    @Feld(value = "8620", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public BetroffeneEinrichtung betroffeneEinrichtung;
    @Feld(value = "8622", feldart = Feldart.kann)
    @Regelsatz(laenge = 43)
    public String coronaGuid;
    @Feld(value = "8625", feldart = Feldart.kann)
    @Regelsatz(laenge = 5)
    public String plzOeGd;
    @Feld(value = "8623", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 22)
    public String identifikationAktenzeichenOeGd;
    @Feld(value = "8311", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public AuftragsnummerLaborId auftragsnummerLaborId;
    @Feld(value = "0080", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public DerFallakteOderStudieId derFallakteOderStudieId;
    @Feld(value = "7258", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public KatalogDurchgefuehrteLeistungenId katalogDurchgefuehrteLeistungenId;
    @Feld(value = "4229", feldart = Feldart.kann)
    @Regelsatz(laenge = 5)
    public List<String> knappschaftskennziffer;
    @Feld(value = "8118", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 22)
    public Kommunikationsdaten abweichenderBefundweg;
    @Feld(value = "8611", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public List<Befundinformationen_ZusaetzlicherBefundweg> zusaetzlicherBefundweg;
    @Feld(value = "7320", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public RecallEmpfohlen recallEmpfohlen;
    @Feld(value = "8247", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 34)
    public List<Fliesstext> diagnostischeBewertungEmpfehlung;
    @Feld(value = "8216", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public Timestamp timestampBefunderstellung;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;
    @Feld(value = "8126", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public List<FehlermeldungAufmerksamkeit> fehlermeldungAufmerksamkeit;
    @Feld(value = "8141", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 13)
    public Namenskennung namenskennung;

}
