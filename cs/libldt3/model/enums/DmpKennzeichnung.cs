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
            /// E020
            /// </summary>
            public enum DmpKennzeichnung
            {
                /// keine Angabe
                keineAngabe,
                /// Diabetes mellitus Typ 2
                Diabetes_mellitusTyp2,
                Brustkrebs,
                /// Koronare Herzkrankheit
                KoronareHerzkrankheit,
                /// Diabetes mellitus Typ 1
                Diabetes_mellitusTyp1,
                /// Asthma bronchiale
                Asthma_bronchiale,
                /// COPD (chronic obstructive pulmo-nary disease)
                COPD,
                /// Chronische Herzinsuffizienz
                ChronischeHerzinsuffizienz,
                Depression,
                /// Rückenschmerz
                Rueckenschmerz,
                Rheuma,
                Osteoporose
            }

            public static class DmpKennzeichnungExtensions
            {
                public static string GetCode(this DmpKennzeichnung self)
                {
                    switch (self)
                    {
                        case DmpKennzeichnung.keineAngabe: return "00";
                        case DmpKennzeichnung.Diabetes_mellitusTyp2: return "01";
                        case DmpKennzeichnung.Brustkrebs: return "02";
                        case DmpKennzeichnung.KoronareHerzkrankheit: return "03";
                        case DmpKennzeichnung.Diabetes_mellitusTyp1: return "04";
                        case DmpKennzeichnung.Asthma_bronchiale: return "05";
                        case DmpKennzeichnung.COPD: return "06";
                        case DmpKennzeichnung.ChronischeHerzinsuffizienz: return "07";
                        case DmpKennzeichnung.Depression: return "08";
                        case DmpKennzeichnung.Rueckenschmerz: return "09";
                        case DmpKennzeichnung.Rheuma: return "10";
                        case DmpKennzeichnung.Osteoporose: return "11";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
