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
import libldt3.model.enums.Alterskategorie;
import libldt3.model.enums.Auftrag;
import libldt3.model.enums.Auftragsart;
import libldt3.model.enums.HpvHrTestergebnis;
import libldt3.model.enums.HpvImpfung;
import libldt3.model.enums.KlinischerBefund;
import libldt3.model.regel.erlaubt.E028;
import libldt3.model.regel.format.F018;
import libldt3.model.regel.kontext.K128;

/**
 * In diesem Objekt wird das Muster 39, Grundlage für die
 * Krebsfrüherkennungsuntersuchung Zervix-Karzinom, abgebildet.
 */
@Objekt(value = "0034", kontextregeln = K128.class)
public class KrebsfrueherkennungZervixKarzinom implements Kontext {

    @Objekt
    public static class Gyn_OpStrahlenOderChemotherapieGenitale implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "7337", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public List<String> gyn_OpStrahlenOderChemotherapieGenitaleWelche;
    }

    @Objekt(kontextregeln = K128.class)
    public static class HpvHrTest implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "3316", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public HpvHrTestergebnis hpvHrTestergebnis;
    }

    @Feld(value = "3322", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public Alterskategorie alterskategorie;
    @Feld(value = "8630", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public Auftragsart auftragsart;
    @Feld(value = "8629", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 6)
    public Auftrag auftrag;
    @Feld(value = "7296", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean wiederholungsuntersuchung;
    @Feld(value = "7297", feldart = Feldart.kann)
    @Regelsatz(value = F018.class, laenge = 8)
    public String datumLetztenUntersuchung;
    @Feld(value = "7414", feldart = Feldart.kann)
    @Regelsatz(value = E028.class, maxLaenge = 5)
    public String gruppe;
    @Feld(value = "7336", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Gyn_OpStrahlenOderChemotherapieGenitale gyn_OpStrahlenOderChemotherapieGenitale;
    @Feld(value = "7338", feldart = Feldart.kann)
    @Regelsatz(value = F018.class, laenge = 8)
    public String gyn_OpStrahlenOderChemotherapieGenitaleWann;
    @Feld(value = "8512", feldart = Feldart.kann)
    @Regelsatz(value = F018.class, laenge = 8)
    public String letztePeriode;
    @Feld(value = "7339", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean graviditaet;
    @Feld(value = "7380", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean ausflussPath_Blutung;
    @Feld(value = "7382", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean iup;
    @Feld(value = "7383", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean einnahmeVonOvulationshemmerSonstigeHormonAnwendung;
    @Feld(value = "7384", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public KlinischerBefund klinischerBefund;
    @Feld(value = "7423", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 990)
    public String erlaeuterungen;
    @Feld(value = "3313", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public HpvImpfung hpvImpfung;
    @Feld(value = "3314", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public HpvHrTest hpvHrTest;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationenObjKrebsfrueherkennungZervixKarzinom;

}
