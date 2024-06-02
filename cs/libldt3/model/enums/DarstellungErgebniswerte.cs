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
            /// E058
            /// </summary>
            public enum DarstellungErgebniswerte
            {
                /// numerisch (exponentielle Darstellung möglich)
                numerisch,
                /// numerisch mit Messwertuntergrenze
                numerisch_mitMesswertuntergrenze,
                /// numerisch mit Messwertobergrenze
                numerisch_mitMesswertobergrenze,
                /// alpha-numerisch
                alphanumerisch,
                Titer,
                /// Titer mit Untergrenze
                Titer_mitUntergrenze,
                /// Titer mit Obergrenze
                Titer_mitObergrenze,
                /// trinäres Testergebnis: 1 | 2 | 3 **
                trinaeresTestergebnis_1_2_3,
                /// 01: 47.85, 5.00E+07, 1x10^6 02: <100, <1.00E+04 03: >2000, >5.00E+04 04:
                /// positiv, negativ, A positiv * 05: 1:2 06: <1:2 07: >1:2 08: 1, 2, 3 ** *   für
                /// die Übertragung von Blutgruppen ist vorzugsweise das Obj_0055 zu verwenden **
                /// Abbildung der Regel E169
                Sonstige
            }

            public static class DarstellungErgebniswerteExtensions
            {
                public static string GetCode(this DarstellungErgebniswerte self)
                {
                    switch (self)
                    {
                        case DarstellungErgebniswerte.numerisch: return "01";
                        case DarstellungErgebniswerte.numerisch_mitMesswertuntergrenze: return "02";
                        case DarstellungErgebniswerte.numerisch_mitMesswertobergrenze: return "03";
                        case DarstellungErgebniswerte.alphanumerisch: return "04";
                        case DarstellungErgebniswerte.Titer: return "05";
                        case DarstellungErgebniswerte.Titer_mitUntergrenze: return "06";
                        case DarstellungErgebniswerte.Titer_mitObergrenze: return "07";
                        case DarstellungErgebniswerte.trinaeresTestergebnis_1_2_3: return "08";
                        case DarstellungErgebniswerte.Sonstige: return "99";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
