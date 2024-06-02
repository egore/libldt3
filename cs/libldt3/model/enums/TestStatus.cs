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
            /// E007
            /// </summary>
            public enum TestStatus
            {
                /// keine gesicherte Information
                /// Es ist keine gesicherte Information zum Ergebnis verfügbar oder abzubilden und
                /// es wird auch keine Information dazu ausgegeben oder angegeben werden können.
                keine_gesicherteInformation,
                /// Ergebnis folgt
                /// Es liegt eine Untersuchungsanforderung vor, für die es aktuell noch kein
                /// Ergebnis gibt.
                Ergebnis_folgt,
                /// Ein technisch validiertes Ergebnis ist ermittelt.
                Ergebnis,
                /// Die Korrektur ist zu
                /// dokumentieren. Es erfolgt keine weitere Abrechnung.
                Ergebnis_korrigiert,
                /// In Einzelfällen können sich noch Veränderungen ergeben.
                /// Es folgt dann ein korrigiertes Ergebnis.
                Ergebnis_ermittelt,
                /// Die Analytik dieser Untersuchungsanforderung ist abgeschlossen und ein
                /// ärztlich validiertes Ergebnis liegt vor.
                Befundergebnis,
                /// Befundergebnis bereits berichtet
                /// Das Befundergebnis ist unverändert schon mindestens einmal übermittelt worden
                /// (keine Abrechnung!).
                Befundergebnis_bereits_berichtet,
                /// Damit hat nur noch
                /// dieses korrigierte Befundergebnis Gültigkeit und alle bisherigen
                /// Befundergebnisse zu dieser Untersuchungsanforderung verlieren Ihre Gültigkeit.
                /// Die Korrektur ist zu dokumentieren. Es erfolgt keine weitere Abrechnung.
                Befundergebnis_korrigiert,
                /// Weil das
                /// Ergebnis fehlt, kann auch kein Befundergebnis erstellt werden.
                Ergebnis_fehlt,
                /// Die
                /// weiteren Ergebnisse werden in folgenden Befundberichten ergänzt. Kommentar:
                /// Diese Ergebnisse werden zu Befunden (einer Leistung). Der Befundbericht vor
                /// Einleitung der erweiterten Analytik kann nur den Status "Auftrag nicht
                /// abgeschlossen" haben. Sollte dies nicht zutreffen ist ein neuer Auftrag zu
                /// erstellen"!
                ErweiterteAnalytik_erforderlich,
                /// Material fehlt
                /// Für die Untersuchungsanforderung ist kein Material für die Analytik vorhanden.
                Material_fehlt,
                /// Die Untersuchungsanforderung wurde storniert.
                Storniert
            }

            public static class TestStatusExtensions
            {
                public static string GetCode(this TestStatus self)
                {
                    switch (self)
                    {
                        case TestStatus.keine_gesicherteInformation: return "01";
                        case TestStatus.Ergebnis_folgt: return "02";
                        case TestStatus.Ergebnis: return "03";
                        case TestStatus.Ergebnis_korrigiert: return "04";
                        case TestStatus.Ergebnis_ermittelt: return "05";
                        case TestStatus.Befundergebnis: return "06";
                        case TestStatus.Befundergebnis_bereits_berichtet: return "07";
                        case TestStatus.Befundergebnis_korrigiert: return "08";
                        case TestStatus.Ergebnis_fehlt: return "09";
                        case TestStatus.ErweiterteAnalytik_erforderlich: return "10";
                        case TestStatus.Material_fehlt: return "11";
                        case TestStatus.Storniert: return "12";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
