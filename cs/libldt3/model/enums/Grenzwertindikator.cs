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
            /// E005
            /// </summary>
            public enum Grenzwertindikator
            {
                /// im Normalbereich
                imNormalbereich,
                /// schwach erhöht,
                schwach_erhoeht,
                /// schwach erhöht,
                schwach_erhoeht_,
                /// stark erhöht,
                stark_erhoeht,
                /// stark erhöht,
                stark_erhoeht_,
                /// schwach erniedrigt,
                schwach_erniedrigt,
                /// schwach erniedrigt,
                schwach_erniedrigt_,
                /// stark erniedrigt,
                stark_erniedrigt,
                /// stark erniedrigt,
                stark_erniedrigt_,
                /// Wert extrem erhöht,
                Wert_extrem_erhoeht,
                /// Wert extrem erhöht,
                Wert_extrem_erhoeht_,
                /// Wert extrem erniedrigt.
                Wert_extrem_erniedrigt_,
                /// Bei nicht numerischen Werten:
                Wert_extrem_erniedrigt__,
                /// normal (anzuwenden bei nicht numerischen Werten),
                normal,
                /// auffällig (anzuwenden bei nicht numerischen Werten),
                auffaellig,
                /// sehr auffällig (anzuwenden bei nicht numerischen Werten).
                sehr_auffaellig_
            }

            public static class GrenzwertindikatorExtensions
            {
                public static string GetCode(this Grenzwertindikator self)
                {
                    switch (self)
                    {
                        case Grenzwertindikator.imNormalbereich: return "N";
                        case Grenzwertindikator.schwach_erhoeht: return "H";
                        case Grenzwertindikator.schwach_erhoeht_: return "+";
                        case Grenzwertindikator.stark_erhoeht: return "HH";
                        case Grenzwertindikator.stark_erhoeht_: return "++";
                        case Grenzwertindikator.schwach_erniedrigt: return "L";
                        case Grenzwertindikator.schwach_erniedrigt_: return "-";
                        case Grenzwertindikator.stark_erniedrigt: return "LL";
                        case Grenzwertindikator.stark_erniedrigt_: return "–";
                        case Grenzwertindikator.Wert_extrem_erhoeht: return "!H";
                        case Grenzwertindikator.Wert_extrem_erhoeht_: return "!+";
                        case Grenzwertindikator.Wert_extrem_erniedrigt_: return "!L";
                        case Grenzwertindikator.Wert_extrem_erniedrigt__: return "!-";
                        case Grenzwertindikator.normal: return "N";
                        case Grenzwertindikator.auffaellig: return "A";
                        case Grenzwertindikator.sehr_auffaellig_: return "AA";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
