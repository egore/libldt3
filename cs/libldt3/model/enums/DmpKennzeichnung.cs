/*
 * Copyright 2016-2025  Christoph Brill <opensource@christophbrill.de>
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
                Osteoporose,
                Adipositas,
                /// Diabetes Typ 2 und KHK
                DiabetesTyp2_undKHK,
                /// Asthma und Diabetes Typ 2
                Asthma_undDiabetesTyp2,
                /// COPD und Diabetes Typ 2
                COPD_undDiabetesTyp2,
                /// COPD und KHK
                COPD_undKHK,
                /// COPD, Diabetes Typ 2 und KHK
                COPD_DiabetesTyp2_undKHK,
                /// Asthma und KHK
                Asthma_undKHK,
                /// Asthma, Diabetes Typ 2 und KHK
                AsthmaDiabetesTyp2_undKHK,
                /// Brustkrebs und Diabetes Typ 2
                Brustkrebs_undDiabetesTyp2,
                /// Diabetes Typ 1 und KHK
                DiabetesTyp1_undKHK,
                /// Asthma und Diabetes Typ 1
                Asthma_undDiabetesTyp1,
                /// Asthma und Brustkrebs
                Asthma_undBrustkrebs,
                /// Brustkrebs und KHK
                Brustkrebs_undKHK,
                /// Brustkrebs und COPD
                Brustkrebs_undCOPD,
                /// COPD und Diabetes Typ 1
                COPD_undDiabetesTyp1,
                /// Brustkrebs, Diabetes Typ 2 und KHK
                BrustkrebsDiabetesTyp2_undKHK,
                /// Asthma, Brustkrebs und Diabetes Typ 2
                AsthmaBrustkrebs_undDiabetesTyp2,
                /// Brustkrebs und Diabetes Typ 1
                Brustkrebs_undDiabetesTyp1,
                /// COPD, Diabetes Typ 1 und KHK
                COPD_DiabetesTyp1_undKHK,
                /// Brustkrebs, COPD und Diabetes Typ 2
                BrustkrebsCOPD_undDiabetesTyp2,
                /// Asthma, Diabetes Typ 1 und KHK
                AsthmaDiabetesTyp1_undKHK,
                /// Asthma, Brustkrebs und KHK
                AsthmaBrustkrebs_undKHK,
                /// Brustkrebs, COPD und KHK
                BrustkrebsCOPD_undKHK,
                /// Brustkrebs, COPD, Diabetes Typ 2 und KHK
                BrustkrebsCOPD_DiabetesTyp2_undKHK,
                /// Asthma, Brustkrebs, Diabetes Typ 2 und KHK
                AsthmaBrustkrebsDiabetesTyp2_undKHK,
                /// Brustkrebs, Diabetes Typ 1 und KHK
                BrustkrebsDiabetesTyp1_undKHK,
                /// Asthma, Brustkrebs und Diabetes Typ 1
                AsthmaBrustkrebs_undDiabetesTyp1,
                /// Asthma, Brustkrebs, Diabetes Typ 1 und KHK
                AsthmaBrustkrebsDiabetesTyp1_undKHK,
                /// Brustkrebs, COPD und Diabetes Typ 1
                BrustkrebsCOPD_undDiabetesTyp1,
                /// Brustkrebs, COPD, Diabetes Typ 1 und KHK
                BrustkrebsCOPD_DiabetesTyp1_undKHK
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
                        case DmpKennzeichnung.Adipositas: return "12";
                        case DmpKennzeichnung.DiabetesTyp2_undKHK: return "30";
                        case DmpKennzeichnung.Asthma_undDiabetesTyp2: return "31";
                        case DmpKennzeichnung.COPD_undDiabetesTyp2: return "32";
                        case DmpKennzeichnung.COPD_undKHK: return "33";
                        case DmpKennzeichnung.COPD_DiabetesTyp2_undKHK: return "34";
                        case DmpKennzeichnung.Asthma_undKHK: return "35";
                        case DmpKennzeichnung.AsthmaDiabetesTyp2_undKHK: return "36";
                        case DmpKennzeichnung.Brustkrebs_undDiabetesTyp2: return "37";
                        case DmpKennzeichnung.DiabetesTyp1_undKHK: return "38";
                        case DmpKennzeichnung.Asthma_undDiabetesTyp1: return "39";
                        case DmpKennzeichnung.Asthma_undBrustkrebs: return "40";
                        case DmpKennzeichnung.Brustkrebs_undKHK: return "41";
                        case DmpKennzeichnung.Brustkrebs_undCOPD: return "42";
                        case DmpKennzeichnung.COPD_undDiabetesTyp1: return "43";
                        case DmpKennzeichnung.BrustkrebsDiabetesTyp2_undKHK: return "44";
                        case DmpKennzeichnung.AsthmaBrustkrebs_undDiabetesTyp2: return "45";
                        case DmpKennzeichnung.Brustkrebs_undDiabetesTyp1: return "46";
                        case DmpKennzeichnung.COPD_DiabetesTyp1_undKHK: return "47";
                        case DmpKennzeichnung.BrustkrebsCOPD_undDiabetesTyp2: return "48";
                        case DmpKennzeichnung.AsthmaDiabetesTyp1_undKHK: return "49";
                        case DmpKennzeichnung.AsthmaBrustkrebs_undKHK: return "50";
                        case DmpKennzeichnung.BrustkrebsCOPD_undKHK: return "51";
                        case DmpKennzeichnung.BrustkrebsCOPD_DiabetesTyp2_undKHK: return "52";
                        case DmpKennzeichnung.AsthmaBrustkrebsDiabetesTyp2_undKHK: return "53";
                        case DmpKennzeichnung.BrustkrebsDiabetesTyp1_undKHK: return "54";
                        case DmpKennzeichnung.AsthmaBrustkrebs_undDiabetesTyp1: return "55";
                        case DmpKennzeichnung.AsthmaBrustkrebsDiabetesTyp1_undKHK: return "56";
                        case DmpKennzeichnung.BrustkrebsCOPD_undDiabetesTyp1: return "57";
                        case DmpKennzeichnung.BrustkrebsCOPD_DiabetesTyp1_undKHK: return "58";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
