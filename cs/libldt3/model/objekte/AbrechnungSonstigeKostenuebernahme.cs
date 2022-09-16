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
using libldt3.attributes;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {

            /**
             * Mit diesem Objekt werden die Informationen für die Abrechnung von
             * Untersuchungsanforderungen zusammengefasst, welche ein Labor außerhalb der
             * Regelleistungen EBM und GOÄ erbringen kann. Es werden hierüber auch
             * Leistungen abgerechnet, welche nicht durch medizinische Einsender abgefordert
             * werden oder die Materialien betreffen, die nicht humanen Ursprungs sind. Der
             * Rechnungsempfänger ist frei wählbar.
             */
            [Objekt(Value = "0005")]
            public class AbrechnungSonstigeKostenuebernahme
            {

                [Feld(Value = "7261", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string sonstigeVersichertennummer;
                [Feld(Value = "7253", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public bool? kostenuebernahmeerklaerungAuftraggeber;
                [Feld(Value = "8148", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 12)]
                public Rechnungsempfaenger rechnungsempfaenger;

            }
        }
    }
}
