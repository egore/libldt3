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
            /// E015
            /// </summary>
            public enum KostentraegerAbrechnungsbereich
            {
                /// Primärabrechnung
                Primaerabrechnung,
                /// Sozialversicherungsabkommen (SVA)
                Sozialversicherungsabkommen,
                /// Bundesversorgungsgesetz (BVG)
                Bundesversorgungsgesetz,
                /// Bundesentschädigungsgesetz (BEG)
                Bundesentschaedigungsgesetz,
                /// Grenzgänger (GG)
                Grenzgaenger,
                /// Rheinschiffer (RHS)
                Rheinschiffer,
                /// Sozialhilfeträger, ohne Asylstellen (SHT)
                Sozialhilfetraeger,
                /// Bundesvertriebenengesetz (BVFG)
                Bundesvertriebenengesetz,
                /// Asylstellen (AS)
                Asylstellen,
                /// Schwangerschaftsabbrüche
                Schwangerschaftsabbrueche
            }

            public static class KostentraegerAbrechnungsbereichExtensions
            {
                public static string GetCode(this KostentraegerAbrechnungsbereich self)
                {
                    switch (self)
                    {
                        case KostentraegerAbrechnungsbereich.Primaerabrechnung: return "00";
                        case KostentraegerAbrechnungsbereich.Sozialversicherungsabkommen: return "01";
                        case KostentraegerAbrechnungsbereich.Bundesversorgungsgesetz: return "02";
                        case KostentraegerAbrechnungsbereich.Bundesentschaedigungsgesetz: return "03";
                        case KostentraegerAbrechnungsbereich.Grenzgaenger: return "04";
                        case KostentraegerAbrechnungsbereich.Rheinschiffer: return "05";
                        case KostentraegerAbrechnungsbereich.Sozialhilfetraeger: return "06";
                        case KostentraegerAbrechnungsbereich.Bundesvertriebenengesetz: return "07";
                        case KostentraegerAbrechnungsbereich.Asylstellen: return "08";
                        case KostentraegerAbrechnungsbereich.Schwangerschaftsabbrueche: return "09";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
