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
using System.Diagnostics;
using System.Reflection;
using libldt3.model;
using libldt3.model.enums;
using libldt3.model.objekte;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn Inhalt von FK 7420 = 12 und FK 7303 mit den Werten 1, 2, 3, 8, 9 oder 10 in
                /// jeweiliger Satzart 8205 oder 8215 vorkommen, dann müssen die FK 3103, FK 3110
                /// und FK 8228 vorhanden sein.
                /// </summary>
                /// Handelt es sich bei der Person um einen Patienten und kommen in der
                /// jeweiligen Satzart "Auftrag" oder "Befund" die Werte für Abrechnungsinfo
                /// zur Untersuchung 1, 2, 3, 8, 9 oder 10 vor, müssen die Angaben zum
                /// Geburtsdatum, Geschlecht und Wohnort vorhanden sein.
                /// Diese Regel ermöglicht es, Aufträge bzw. Befunde zu übertragen, bei
                /// denen die Angaben zum Geschlecht, Geburtsdatum bzw. Wohnort des
                /// Patienten nicht oder nicht komplett vorhanden sind.
                public class K094 : Kontextregel
                {
                    private static readonly ISet<string> FIELDTYPES = ISet.Of("7420", "7303", "3103", "3110", "8228");

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K094.FIELDTYPES);
                        if (fields.Count != K094.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K094.FIELDTYPES);
                            return false;
                        }

                        StatusPerson? feld7420 = (StatusPerson?)fields["7420"].GetValue(owner);
                        AbrechnungsinfoZurUntersuchung feld7303 = (AbrechnungsinfoZurUntersuchung)fields["7303"].GetValue(owner);
                        // Wenn Inhalt von FK 7420 = 12 und FK 7303 mit den Werten 1, 2, 3, 8, 9 oder 10 in jeweiliger Satzart 8205
                        // oder 8215 vorkommen
                        if (feld7420 == StatusPerson.Patient && feld7303.Value == Abrechnungsinfo.GKV_Laborfacharzt || feld7303.Value == Abrechnungsinfo.GKV_LG || feld7303.Value == Abrechnungsinfo.PKV_Laborfacharzt || feld7303.Value == Abrechnungsinfo.ASV || feld7303.Value == Abrechnungsinfo.GKV_Laborfacharzt_praeventiv || feld7303.Value == Abrechnungsinfo.GKV_LG_praeventiv)
                        {
                            // dann müssen die FK 3103, FK 3110 und FK 8228 vorhanden sein
                            return KontextregelHelper.ContainsAnyValue(fields["3103"], owner) && KontextregelHelper.ContainsAnyValue(fields["3110"], owner) && KontextregelHelper.ContainsAnyValue(fields["8228"], owner);
                        }

                        return true;
                    }
                }
            }
        }
    }
}
