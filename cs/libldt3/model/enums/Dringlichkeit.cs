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
            /// E032
            /// </summary>
            public enum Dringlichkeit
            {
                /// der Patient ist vital bedroht oder der Befund ist
                /// für das weitere operative Vorgehen maßgeblich. Für die Differentialdiagnostik
                /// ist die unverzügliche Erbringung und Übermittlung der Laborbefunde unabdingbar
                /// (z.B. Troponin zum Ausschluss akuter Myokardinfarkt, PTH bei Nebenschilddrüsen-
                /// OP, histologische Schnellschnitte bei Ablation mammae).
                Notfall_intraoperativ,
                /// Quick bei Marcumar-
                /// Einstellung). Die Ergebnisse werden schnellstmöglich nach Probeneingang im Labor
                /// an den Einsender übermittelt. Einsender und Labor sollten sich über die Frist
                /// einigen.
                Eilig
            }

            public static class DringlichkeitExtensions
            {
                public static string GetCode(this Dringlichkeit self)
                {
                    switch (self)
                    {
                        case Dringlichkeit.Notfall_intraoperativ: return "1";
                        case Dringlichkeit.Eilig: return "2";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
