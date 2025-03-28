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
            /// E069
            /// </summary>
            public enum Nachweisverfahren
            {
                /// sonstige, wenn Erreger + Resistenz angefordert
                sonstige_wennErreger_und_Resistenz_angefordert,
                /// Antigen-Nachweis
                AntigenNachweis,
                PCR,
                Mikroskopie,
                Aglutination,
                Kultur,
                /// Vitek)
                BiochemischeIdentifikation,
                /// Maldi-Tof
                MaldiTof
            }

            public static class NachweisverfahrenExtensions
            {
                public static string GetCode(this Nachweisverfahren self)
                {
                    switch (self)
                    {
                        case Nachweisverfahren.sonstige_wennErreger_und_Resistenz_angefordert: return "0";
                        case Nachweisverfahren.AntigenNachweis: return "1";
                        case Nachweisverfahren.PCR: return "2";
                        case Nachweisverfahren.Mikroskopie: return "3";
                        case Nachweisverfahren.Aglutination: return "4";
                        case Nachweisverfahren.Kultur: return "5";
                        case Nachweisverfahren.BiochemischeIdentifikation: return "6";
                        case Nachweisverfahren.MaldiTof: return "7";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
