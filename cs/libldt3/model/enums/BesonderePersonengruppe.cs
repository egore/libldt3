/*
 * Copyright 2016-2024  Christoph Brill <opensource@christophbrill.de>
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
namespace libldt3
{
    namespace model
    {
        namespace enums
        {

            /// <summary>
            /// E021
            /// </summary>
            public enum BesonderePersonengruppe
            {
                /// keine Angabe
                keineAngabe,
                /// BSHG (Bundessozialhilfegesetz) § 264 SGB V
                BSHG264SGB_V,
                /// BVG (Gesetz über die Versorgung der Opfer des Krieges)
                SER,
                /// Krankenversicherungsrecht: Personen mit Wohnsitz im Inland,  Abrechnung nach
                /// Aufwand
                SVA_Kennzeichnung_fuer_zwischenstaatliches,
                /// SVA-Kennzeichnung, pauschal
                SVA_Kennzeichnung_pauschal,
                /// Empfänger von Gesundheitsleistungen nach den §§ 4, 6 AsylbLG
                Empfaenger_vonGesundheitsleistungen_nach_den46AsylbLG
            }

            public static class BesonderePersonengruppeExtensions
            {
                public static string GetCode(this BesonderePersonengruppe self)
                {
                    switch (self)
                    {
                        case BesonderePersonengruppe.keineAngabe: return "00";
                        case BesonderePersonengruppe.BSHG264SGB_V: return "04";
                        case BesonderePersonengruppe.SER: return "06";
                        case BesonderePersonengruppe.SVA_Kennzeichnung_fuer_zwischenstaatliches: return "07";
                        case BesonderePersonengruppe.SVA_Kennzeichnung_pauschal: return "08";
                        case BesonderePersonengruppe.Empfaenger_vonGesundheitsleistungen_nach_den46AsylbLG: return "09";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
