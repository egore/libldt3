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
import libldt3.model.enums.Gebuehrenordnung;
import libldt3.model.regel.kontext.K019;

/**
 * Mit diesem Objekt werden die Informationen für die Abrechnung von
 * Untersuchungsanforderungen zusammengefasst, welche als Ige-Leistungen gegenüber
 * gesetzlich versicherten Patienten erbracht werden können.
 */
@Objekt(value = "0004", kontextregeln = K019.class)
public class AbrechnungIgeLeistungen implements Kontext {

    @Objekt
    public static class KostenuebernahmeerklaerungAuftraggeberLiegtVor implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "8148", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public Rechnungsempfaenger rechnungsempfaenger;
    }

    @Feld(value = "4121", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public Gebuehrenordnung gebuehrenordnung;
    @Feld(value = "7253", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public KostenuebernahmeerklaerungAuftraggeberLiegtVor kostenuebernahmeerklaerungAuftraggeberLiegtVor;

}
