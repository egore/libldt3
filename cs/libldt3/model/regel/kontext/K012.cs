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
                /// Wenn Inhalt von FK 4239 = 27 oder 28, dann muss das FK 4221 vorhanden sein.
                /// </summary>
                /// FK
                /// 4221 darf nicht vorhanden sein, wenn Inhalt von FK 4239 ≠ 27 oder 28 ist.
                public class K012 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K012));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "4239", "4221" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K012.FIELDTYPES);
                        if (fields.Count != K012.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K012.FIELDTYPES);
                            return false;
                        }

                        Scheinuntergruppe? feld4239 = (Scheinuntergruppe?)KontextregelHelper.GetFieldValue(fields["4239"], owner);
                        // Wenn Inhalt von FK 4239 = 27 oder 28, dann muss das FK 4221 vorhanden sein
                        if (feld4239 == Scheinuntergruppe.Muster10 || feld4239 == Scheinuntergruppe.Muster10A)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["4221"], owner))
                            {
                                return false;
                            }

                        }

                        return true;
                    }
                }
            }
        }
    }
}
