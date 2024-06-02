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
            /// E052
            /// </summary>
            public enum Normwertspezifikation
            {
                /// Methodenspezifische Standards nach WHO
                MethodenspezifischeStandards_nachWHO,
                /// serologische Verfahren)
                MethodenspezifischeStandards_nachIFCC,
                /// Methodenspezifische Standards nach DGKL
                MethodenspezifischeStandards_nachDGKL,
                /// Sonstige Standards 1)
                SonstigeStandards,
                /// Patientenspezifische Einflussgröße "Alter" betreffend
                PatientenspezifischeEinflussgroesseAlter_betreffend,
                /// Patientenspezifische Einflussgröße "Geschlecht" betreffend
                PatientenspezifischeEinflussgroesseGeschlecht_betreffend,
                /// Patientenspezifische Einflussgröße "Alter + Geschlecht" betreffend
                PatientenspezifischeEinflussgroesseAlter_und_Geschlecht_betreffend,
                /// Patientenspezifische Einflussgröße "SSW" betreffend
                PatientenspezifischeEinflussgroesseSSW_betreffend,
                /// Patientenspezifische Einflussgröße "Alter + SSW" betreffend
                PatientenspezifischeEinflussgroesseAlter_und_SSW_betreffend,
                /// Medikation) 1)
                weitere_patientenspezifischeEinflussgroessen,
                /// Information zu Patientenspezifischer Einflussgröße "Alter" fehlte
                Information_zuPatientenspezifischerEinflussgroesseAlter_fehlte,
                /// Information zu Patientenspezifischer Einflussgröße "Geschlecht" fehlte
                Information_zuPatientenspezifischerEinflussgroesseGeschlecht_fehlte,
                /// Information zu Patientenspezifischer Einflussgröße Alter und Geschlecht fehlte
                InformationPatientenspezifischerEinflussgroesseAlterGeschlechtFehlte,
                /// Funktionsprofile 1)
                /// 1) Zur weiteren Spezifikation FK 8167 verwenden.
                Funktionsprofile
            }

            public static class NormwertspezifikationExtensions
            {
                public static string GetCode(this Normwertspezifikation self)
                {
                    switch (self)
                    {
                        case Normwertspezifikation.MethodenspezifischeStandards_nachWHO: return "10";
                        case Normwertspezifikation.MethodenspezifischeStandards_nachIFCC: return "11";
                        case Normwertspezifikation.MethodenspezifischeStandards_nachDGKL: return "12";
                        case Normwertspezifikation.SonstigeStandards: return "13";
                        case Normwertspezifikation.PatientenspezifischeEinflussgroesseAlter_betreffend: return "20";
                        case Normwertspezifikation.PatientenspezifischeEinflussgroesseGeschlecht_betreffend: return "21";
                        case Normwertspezifikation.PatientenspezifischeEinflussgroesseAlter_und_Geschlecht_betreffend: return "22";
                        case Normwertspezifikation.PatientenspezifischeEinflussgroesseSSW_betreffend: return "23";
                        case Normwertspezifikation.PatientenspezifischeEinflussgroesseAlter_und_SSW_betreffend: return "24";
                        case Normwertspezifikation.weitere_patientenspezifischeEinflussgroessen: return "25";
                        case Normwertspezifikation.Information_zuPatientenspezifischerEinflussgroesseAlter_fehlte: return "26";
                        case Normwertspezifikation.Information_zuPatientenspezifischerEinflussgroesseGeschlecht_fehlte: return "27";
                        case Normwertspezifikation.InformationPatientenspezifischerEinflussgroesseAlterGeschlechtFehlte: return "28";
                        case Normwertspezifikation.Funktionsprofile: return "30";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
