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
            /// E050
            /// </summary>
            public enum Abrechnungsinfo
            {
                /// GKV Laborfacharzt
                GKV_Laborfacharzt,
                /// GKV LG
                GKV_LG,
                /// PKV Laborfacharzt
                PKV_Laborfacharzt,
                /// PKV LG
                PKV_LG,
                Selektivvertrag,
                IgeL,
                /// Sonstige Kostenübernahme
                SonstigeKostenuebernahme,
                ASV,
                /// GKV Laborfacharzt präventiv
                GKV_Laborfacharzt_praeventiv,
                /// GKV LG präventiv
                GKV_LG_praeventiv,
                /// keine Zuordnung (nur zulässig im Obj_0027)
                keineZuordnung,
                /// PräOP (Präoperative Laborleistungen**)
                PraeOP,
                /// GKV Krankenhaus
                GKV_Krankenhaus,
                /// PKV Krankenhaus
                PKV_Krankenhaus,
                /// GKV Muster 6 / 39
                GKV_Muster6_39,
                /// GKV Muster 10C
                GKV_Muster10C,
                /// ÖGD
                OeGD,
                /// Abschnitt 31.1 des EBM)
                storniert
            }

            public static class AbrechnungsinfoExtensions
            {
                public static string GetCode(this Abrechnungsinfo self)
                {
                    switch (self)
                    {
                        case Abrechnungsinfo.GKV_Laborfacharzt: return "1";
                        case Abrechnungsinfo.GKV_LG: return "2";
                        case Abrechnungsinfo.PKV_Laborfacharzt: return "3";
                        case Abrechnungsinfo.PKV_LG: return "4";
                        case Abrechnungsinfo.Selektivvertrag: return "5";
                        case Abrechnungsinfo.IgeL: return "6";
                        case Abrechnungsinfo.SonstigeKostenuebernahme: return "7";
                        case Abrechnungsinfo.ASV: return "8";
                        case Abrechnungsinfo.GKV_Laborfacharzt_praeventiv: return "9";
                        case Abrechnungsinfo.GKV_LG_praeventiv: return "10";
                        case Abrechnungsinfo.keineZuordnung: return "11";
                        case Abrechnungsinfo.PraeOP: return "12";
                        case Abrechnungsinfo.GKV_Krankenhaus: return "13";
                        case Abrechnungsinfo.PKV_Krankenhaus: return "14";
                        case Abrechnungsinfo.GKV_Muster6_39: return "15";
                        case Abrechnungsinfo.GKV_Muster10C: return "16";
                        case Abrechnungsinfo.OeGD: return "17";
                        case Abrechnungsinfo.storniert: return "99";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
