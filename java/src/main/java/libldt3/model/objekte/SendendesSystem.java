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

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.regel.format.F012;
import libldt3.model.regel.kontext.K050;

/**
 * Dieses Objekt enthält die Information zum sendenden Softwaresystem, welches den
 * LDT Datensatz erstellt hat.
 */
@Objekt(value = "0051", kontextregeln = K050.class)
public class SendendesSystem implements Kontext {

    @Objekt
    public static class SoftwareNameSoftware implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "0132", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String versionReleasestandSoftware;
    }

    @Feld(value = "8315", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String desEmpfaengersId;
    @Feld(value = "8316", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String desSendersId;
    @Feld(value = "0105", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F012.class, laenge = 16)
    public String kbvPruefnummer;
    @Feld(value = "8212", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 24)
    public Organisation softwareverantwortlicher;
    @Feld(value = "0103", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public SoftwareNameSoftware softwareNameSoftware;

}
