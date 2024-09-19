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
                /// Wenn Inhalt von FK 4239 = 27 und FK 8240 vorhanden, dann muss eine der
                /// folgenden Kombinationen vorhanden sein:
                /// - FK 4217 und FK 4241 oder
                /// - FK 4225 und FK 4241 oder
                /// - FK 4225 und FK 4248.
                /// </summary>
                public class K041 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K041));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "4239", "8240", "4217", "4241", "4225", "4248" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K041.FIELDTYPES);
                        if (fields.Count != K041.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K041.FIELDTYPES);
                            return false;
                        }

                        if ((Scheinuntergruppe?)fields["4239"].GetValue(owner) == Scheinuntergruppe.Muster10 && KontextregelHelper.ContainsAnyValue(fields["8240"], owner))
                        {
                            if (KontextregelHelper.ContainsAnyValue(fields["4217"], owner) && KontextregelHelper.ContainsAnyValue(fields["4241"], owner))
                            {
                                return true;
                            }

                            if (KontextregelHelper.ContainsAnyValue(fields["4225"], owner) && KontextregelHelper.ContainsAnyValue(fields["4241"], owner))
                            {
                                return true;
                            }

                            if (KontextregelHelper.ContainsAnyValue(fields["4225"], owner) && KontextregelHelper.ContainsAnyValue(fields["4248"], owner))
                            {
                                return true;
                            }

                            return false;
                        }

                        return true;
                    }
                }
            }
        }
    }
}
