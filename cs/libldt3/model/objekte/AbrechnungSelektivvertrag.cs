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
using NodaTime;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.regel;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Mit diesem Objekt werden die Informationen für die Abrechnung von
            /// Untersuchungsanforderungen zusammengefasst, welche im Rahmen von
            /// Selektivverträgen und damit außerhalb der budgetären Leistungen erbracht
            /// werden.
            /// </summary>
            /// Die Möglichkeit zum Abschluss von Selektivverträgen besteht im
            /// Wesentlichen in der hausarztzentrierten Versorgung (§ 73 b SGB V), in der
            /// besonderen ambulanten ärztlichen Versorgung (§ 73 c SGB V), bei
            /// strukturierten Behandlungsprogrammen für chronische Erkrankungen
            /// (Disease-Management-Programme) (§ 137 f SGB V) und in der Integrierten
            /// Versorgung (§§ 140ff SGB V).
            [Objekt(Value = "0006")]
            public class AbrechnungSelektivvertrag
            {
                [Feld(Value = "3130", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public EinschreibestatusSelektivvertraege? EinschreibestatusSelektivvertraege;
                [Feld(Value = "3134", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string BezeichnungSelektivvertrag;
                [Feld(Value = "4134", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 28)]
                public string Kostentraegername;
                [Feld(Value = "3131", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? TeilnahmeVon;
                [Feld(Value = "3132", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? TeilnahmeBis;
                [Feld(Value = "3133", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? DatumAntragstellung;
                [Feld(Value = "7430", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 60)]
                public string PatientenIdSelektivvertrag;
                [Feld(Value = "4121", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Gebuehrenordnung? Gebuehrenordnung;
                [Feld(Value = "8148", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 12)]
                public Rechnungsempfaenger Rechnungsempfaenger;

            }
        }
    }
}
