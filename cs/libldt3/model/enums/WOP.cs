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
            /// E022
            /// </summary>
            public enum WOP
            {
                /// Dummy bei eGK
                Dummy_bei_eGK,
                /// Schleswig-Holstein
                SchleswigHolstein,
                Hamburg,
                Bremen,
                Niedersachsen,
                /// Westfalen-Lippe
                WestfalenLippe,
                Nordrhein,
                Hessen,
                Koblenz,
                Rheinhessen,
                Pfalz,
                Trier,
                /// Rheinland-Pfalz
                RheinlandPfalz,
                /// Baden-Württemberg
                BadenWuerttemberg,
                Nordbaden,
                /// Südbaden
                Suedbaden,
                /// Nordwürttemberg
                Nordwuerttemberg,
                /// Südwürttemberg
                Suedwuerttemberg,
                Bayern,
                Berlin,
                Saarland,
                KBV,
                /// Mecklenburg-Vorpommern
                MecklenburgVorpommern,
                Brandenburg,
                /// Sachsen-Anhalt
                SachsenAnhalt,
                /// Thüringen
                Thueringen,
                /// ( ) fusioniert, teilweise aber noch in Gebrauch
                Sachsen
            }

            public static class WOPExtensions
            {
                public static string GetCode(this WOP self)
                {
                    switch (self)
                    {
                        case WOP.Dummy_bei_eGK: return "00";
                        case WOP.SchleswigHolstein: return "01";
                        case WOP.Hamburg: return "02";
                        case WOP.Bremen: return "03";
                        case WOP.Niedersachsen: return "17";
                        case WOP.WestfalenLippe: return "20";
                        case WOP.Nordrhein: return "38";
                        case WOP.Hessen: return "46";
                        case WOP.Koblenz: return "47";
                        case WOP.Rheinhessen: return "48";
                        case WOP.Pfalz: return "49";
                        case WOP.Trier: return "50";
                        case WOP.RheinlandPfalz: return "51";
                        case WOP.BadenWuerttemberg: return "52";
                        case WOP.Nordbaden: return "55";
                        case WOP.Suedbaden: return "60";
                        case WOP.Nordwuerttemberg: return "61";
                        case WOP.Suedwuerttemberg: return "62";
                        case WOP.Bayern: return "71";
                        case WOP.Berlin: return "72";
                        case WOP.Saarland: return "73";
                        case WOP.KBV: return "74";
                        case WOP.MecklenburgVorpommern: return "78";
                        case WOP.Brandenburg: return "83";
                        case WOP.SachsenAnhalt: return "88";
                        case WOP.Thueringen: return "93";
                        case WOP.Sachsen: return "98";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
