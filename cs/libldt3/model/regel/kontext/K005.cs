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
using Microsoft.Extensions.Logging;
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
                /// Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 1, darf FK 4121
                /// nicht vorhanden sein.
                /// </summary>
                /// Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401
                /// = 2, kann FK 4121 vorhanden sein
                /// 
                /// In Befunden mit dem Status "Auftrag nicht abgeschlossen" dürfen keine
                /// Abrechnungsinformationen übertragen werden.
                /// Nur in Befunden mit dem Status "Auftrag abgeschlossen" können
                /// Abrechnungsinformationen übertragen werden.
                public class K005 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K005));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "8000", "8401", "4121" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K005.FIELDTYPES);
                        if (fields.Count != K005.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K005.FIELDTYPES);
                            return false;
                        }

                        Satzart? feld8000 = (Satzart?)KontextregelHelper.GetFieldValue(fields["8000"], owner);
                        if (feld8000 == Satzart.Befund)
                        {
                            // Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 1, darf FK 4121 nicht vorhanden sein.
                            Auftragsstatus? auftragsstatus = (Auftragsstatus?)fields["8401"].GetValue(owner);
                            if (auftragsstatus == Auftragsstatus.Auftrag_nicht_abgeschlossen)
                            {
                                if (KontextregelHelper.ContainsAnyValue(fields["8410"], owner))
                                {
                                    return false;
                                }

                            }

                            // Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 2, kann FK 4121 vorhanden sein
                        }

                        return true;
                    }
                }
            }
        }
    }
}
