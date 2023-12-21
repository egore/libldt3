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
import libldt3.model.enums.EndozervikaleZellen;
import libldt3.model.enums.Grenzwertindikator;
import libldt3.model.enums.HpvHrTestergebnis;
import libldt3.model.enums.HpvTyp1618;
import libldt3.model.enums.NachkontrollGrund;
import libldt3.model.enums.TestStatus;
import libldt3.model.regel.erlaubt.E028;
import libldt3.model.regel.format.F023;
import libldt3.model.regel.kontext.K076;
import libldt3.model.regel.kontext.K082;
import libldt3.model.regel.kontext.K096;
import libldt3.model.regel.kontext.K099;
import libldt3.model.regel.kontext.K100;
import libldt3.model.regel.kontext.K122;
import libldt3.model.regel.kontext.K123;
import libldt3.model.regel.kontext.K124;
import libldt3.model.regel.kontext.K125;
import libldt3.model.regel.kontext.K126;
import libldt3.model.regel.kontext.K134;

/**
 * In diesem Objekt werden die Ergebnisse der Krebsfrüherkennung Zervix-Karzinom
 * übertragen. Die Inhalte richten sich nach dem Muster 39a/b. Zervix-Karzinom
 */
@Objekt(value = "0062", kontextregeln = {K076.class, K082.class, K096.class, K100.class, K122.class, K123.class, K124.class, K125.class, K126.class, K134.class})
public class UntersuchungsergebnisKrebsfrueherkennungZervixKarzinom implements Kontext {

    @Objekt(kontextregeln = K100.class)
    public static class TestIdent implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8411", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String testbezeichnung;
        @Feld(value = "8422", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 2)
        public List<GrenzwertindikatorLaborwerte> grenzwertindikatorLaborwerte;
        @Feld(value = "8237", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public Fliesstext ergebnistext;
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
    public static class Gruppe implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7413", feldart = Feldart.bedingt_muss)
        @Regelsatz(value = E028.class, maxLaenge = 4)
        public String codierungGruppe;
    }

    @Objekt(kontextregeln = K122.class)
    public static class HpvHrTestergebnisObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom implements Kontext {
        @SuppressWarnings("unused")
        public HpvHrTestergebnis value;
        @Feld(value = "3317", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public HpvTyp1618 hpvTyp1618;
    }

    @Objekt
    public static class ZytologischeKontrolle implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "7416", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public List<NachkontrollGrund> grundNachkontrolle;
    }

    @Feld(value = "7304", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String ergebnisId;
    @Feld(value = "7364", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public List<String> probengefaessIdent;
    @Feld(value = "8410", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 20)
    public TestIdent testIdent;
    @Feld(value = "8418", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public TestStatus ergebnisstatus;
    @Feld(value = "7405", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public EndozervikaleZellen endozervikaleZellen;
    @Feld(value = "7406", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 10)
    public String proliferationsgrad;
    @Feld(value = "7407", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Boolean doederleinflora;
    @Feld(value = "7408", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Boolean mischflora;
    @Feld(value = "7409", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Boolean kokkenflora;
    @Feld(value = "7410", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Boolean trichomonaden;
    @Feld(value = "7411", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Boolean candida;
    @Feld(value = "7412", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Boolean gardnerella;
    @Feld(value = "7414", feldart = Feldart.kann)
    @Regelsatz(value = E028.class, maxLaenge = 5)
    public Gruppe gruppe;
    @Feld(value = "3316", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public HpvHrTestergebnisObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom hpvHrTestergebnisObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom;
    @Feld(value = "7415", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public ZytologischeKontrolle zytologischeKontrolle;
    @Feld(value = "7417", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean abklaerungskolposkopie;
    @Feld(value = "3318", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean hpvTest;
    @Feld(value = "3319", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean koTest;
    @Feld(value = "3320", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean zeitraumSofort;
    @Feld(value = "3321", feldart = Feldart.kann)
    @Regelsatz(value = F023.class, minLaenge = 1, maxLaenge = 5)
    public String zeitraumInMonaten;
    @Feld(value = "8237", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 12)
    public Fliesstext ergebnistext;
    @Feld(value = "8134", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 35)
    public KrebsfrueherkennungZervixKarzinom krebsfrueherkennungZervixKarzinom;
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
    public Timestamp timestampMessungObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;
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
