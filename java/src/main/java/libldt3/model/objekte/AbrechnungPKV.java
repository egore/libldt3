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
import libldt3.model.enums.AbrechnungsartPkv;
import libldt3.model.enums.Gebuehrenordnung;
import libldt3.model.regel.kontext.K019;
import libldt3.model.regel.kontext.K033;
import libldt3.model.regel.kontext.K034;
import libldt3.model.regel.kontext.K093;

/**
 * Mit diesem Objekt werden die Informationen für die Abrechnung von
 * Untersuchungsanforderungen zusammengefasst, die über die GOÄ abgerechnet werden.
 * Das Objekt bezieht sich auf Patienten, welche bei privaten Krankenkassen
 * versichert sind. Dabei kann der Rechnungsempfänger aber auch ein anderer sein,
 * als der Versicherte.
 */
@Objekt(value = "0003", kontextregeln = {K019.class, K033.class, K034.class, K093.class})
public class AbrechnungPKV implements Kontext {

    @Objekt(kontextregeln = K093.class)
    public static class AbrechnungPKV_Gebuehrenordnung implements Kontext {
        @SuppressWarnings("unused")
        public Gebuehrenordnung value;
        @Feld(value = "4202", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public Boolean unfallUnfallfolgen;
        @Feld(value = "8148", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public Rechnungsempfaenger rechnungsempfaenger;
    }

    @Feld(value = "7362", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public AbrechnungsartPkv abrechnungsartPkv;
    @Feld(value = "4134", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 45)
    public List<String> kostentraegername;
    @Feld(value = "4121", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public AbrechnungPKV_Gebuehrenordnung gebuehrenordnung;

}
