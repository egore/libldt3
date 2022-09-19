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
namespace libldt3
{
    namespace model
    {
        namespace enums
        {

            /// <summary>
            /// E146
            /// </summary>
            public enum Untersuchungsanlass
            {
                /// Vorsorge
                Vorsorge,
                /// Verlaufskontrolle
                Verlaufskontrolle,
                /// Zustand vor
                ZustandVor,
                /// Zustand nach
                ZustandNach,
                /// Ausschluss
                Ausschluss,
                /// Bestätigung
                Bestaetigung,
                /// gezielte Suche
                GezielteSuche,
                /// ungezielte Suche
                UngezielteSuche,
                /// Erfolgskontrolle
                Erfolgskontrolle,
                /// Abschlusskontrolle
                Abschlusskontrolle,
                /// Immunität/Impferfolg
                ImmunitaetImpferfolg
            }

            public static class UntersuchungsanlassExtensions
            {
                public static string GetCode(this Untersuchungsanlass self)
                {
                    switch (self)
                    {
                        case Untersuchungsanlass.Vorsorge: return "01";
                        case Untersuchungsanlass.Verlaufskontrolle: return "02";
                        case Untersuchungsanlass.ZustandVor: return "03";
                        case Untersuchungsanlass.ZustandNach: return "04";
                        case Untersuchungsanlass.Ausschluss: return "05";
                        case Untersuchungsanlass.Bestaetigung: return "06";
                        case Untersuchungsanlass.GezielteSuche: return "07";
                        case Untersuchungsanlass.UngezielteSuche: return "08";
                        case Untersuchungsanlass.Erfolgskontrolle: return "09";
                        case Untersuchungsanlass.Abschlusskontrolle: return "10";
                        case Untersuchungsanlass.ImmunitaetImpferfolg: return "11";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
