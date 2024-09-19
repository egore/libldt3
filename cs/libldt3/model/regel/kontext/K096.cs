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
using Microsoft.Extensions.Logging;
using System.Reflection;
using libldt3.model;
using libldt3.model.enums;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn Inhalt von FK 8401 = 2, darf der Inhalt von FK 8418 nicht 02, 05 oder 10
                /// sein.
                /// </summary>
                /// In einem Befund mit dem Status "Auftrag abgeschlossen" werden keine
                /// fehlenden oder vorläufigen Werte übertragen.
                public class K096 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K096));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "8401", "8418" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K096.FIELDTYPES);
                        if (fields.Count != K096.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K096.FIELDTYPES);
                            return false;
                        }

                        Auftragsstatus? status = (Auftragsstatus?)fields["8401"].GetValue(owner);
                        if (status == Auftragsstatus.Auftrag_abgeschlossen)
                        {
                            TestStatus? testStatus = (TestStatus?)fields["8418"].GetValue(owner);
                            return testStatus != TestStatus.Ergebnis_folgt && testStatus != TestStatus.Ergebnis_ermittelt && testStatus != TestStatus.ErweiterteAnalytik_erforderlich;
                        }

                        return true;
                    }
                }
            }
        }
    }
}
