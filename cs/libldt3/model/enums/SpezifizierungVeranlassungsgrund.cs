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
            /// E147
            /// </summary>
            public enum SpezifizierungVeranlassungsgrund
            {
                Eingriff,
                Medikamentengabe,
                /// unklares Fieber
                unklaresFieber,
                Infektion,
                Rheuma,
                Allergie,
                /// Herz/Kreislauf
                Herz_Kreislauf,
                Tumor,
                Impfungen,
                Reisen,
                /// Immunität nach Infektion
                Immunitaet_nachInfektion,
                Sonstiges
            }

            public static class SpezifizierungVeranlassungsgrundExtensions
            {
                public static string GetCode(this SpezifizierungVeranlassungsgrund self)
                {
                    switch (self)
                    {
                        case SpezifizierungVeranlassungsgrund.Eingriff: return "01";
                        case SpezifizierungVeranlassungsgrund.Medikamentengabe: return "02";
                        case SpezifizierungVeranlassungsgrund.unklaresFieber: return "03";
                        case SpezifizierungVeranlassungsgrund.Infektion: return "04";
                        case SpezifizierungVeranlassungsgrund.Rheuma: return "05";
                        case SpezifizierungVeranlassungsgrund.Allergie: return "06";
                        case SpezifizierungVeranlassungsgrund.Herz_Kreislauf: return "07";
                        case SpezifizierungVeranlassungsgrund.Tumor: return "08";
                        case SpezifizierungVeranlassungsgrund.Impfungen: return "09";
                        case SpezifizierungVeranlassungsgrund.Reisen: return "10";
                        case SpezifizierungVeranlassungsgrund.Immunitaet_nachInfektion: return "11";
                        case SpezifizierungVeranlassungsgrund.Sonstiges: return "12";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
