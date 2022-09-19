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
            /// E050
            /// </summary>
            public enum Abrechnungsinfo
            {
                /// GKV Laborfacharzt
                GkvLaborfacharzt,
                /// GKV LG
                GkvLg,
                /// PKV Laborfacharzt
                PkvLaborfacharzt,
                /// PKG LG
                PkvLg,
                /// Selektivvertrag
                Selektivvertrag,
                /// IGeL
                IGeL,
                /// Sonstige Kostenübernahme
                Sonstige_Kostenuebernahme,
                /// ASV
                Asv,
                /// GKV Laborfacharzt präventiv
                GkvLaborfacharztPraeventiv,
                /// GKV LG präventiv
                GkgLgPraeventiv,
                /// keine Zuordnung
                keine_Zuordnung,
                /// storniert
                storniert
            }

            public static class AbrechnungsinfoExtensions
            {
                public static string GetCode(this Abrechnungsinfo self)
                {
                    switch (self)
                    {
                        case Abrechnungsinfo.GkvLaborfacharzt: return "1";
                        case Abrechnungsinfo.GkvLg: return "2";
                        case Abrechnungsinfo.PkvLaborfacharzt: return "3";
                        case Abrechnungsinfo.PkvLg: return "4";
                        case Abrechnungsinfo.Selektivvertrag: return "5";
                        case Abrechnungsinfo.IGeL: return "6";
                        case Abrechnungsinfo.Sonstige_Kostenuebernahme: return "7";
                        case Abrechnungsinfo.Asv: return "8";
                        case Abrechnungsinfo.GkvLaborfacharztPraeventiv: return "9";
                        case Abrechnungsinfo.GkgLgPraeventiv: return "10";
                        case Abrechnungsinfo.keine_Zuordnung: return "11";
                        case Abrechnungsinfo.storniert: return "99";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
