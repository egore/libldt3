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
using NodaTime;
using System.Diagnostics;
using System.Reflection;
using Microsoft.Extensions.Logging;
using libldt3.model;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Falls die FK 4109 vorhanden ist und der Feldinhalt >= "01.01.2015" sowie der
                /// Inhalt der Stellen 3 – 5 der FK 4104 < 800, dann müssen die FK 3119 und FK 4133
                /// vorhanden sein.
                /// </summary>
                /// Da seit dem  01.01.2015 im Bereich der GKV-Kostenträgern KVKs nicht
                /// mehr zulässig sind, können Behandlungen auf Basis von eingelesen
                /// KVKs bei GKV-Kostenträgern nicht durchgeführt werden.
                public class K091 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K091));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "4109", "4104", "3119", "4133" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K091.FIELDTYPES);
                        if (fields.Count != K091.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K091.FIELDTYPES);
                            return false;
                        }

                        LocalDate? feld4109 = (LocalDate?)fields["4109"].GetValue(owner);
                        string feld4104 = (string)fields["4109"].GetValue(owner);
                        if (feld4109 != null && !feld4109.IsBefore(LocalDate.Parse("01.01.2015")))
                        {
                            if (Int32.Parse(feld4104.Substring(3, 5)) <= 800)
                            {
                                return KontextregelHelper.ContainsAnyValue(fields["3119"], owner) && KontextregelHelper.ContainsAnyValue(fields["4133"], owner);
                            }

                        }

                        return true;
                    }
                }
            }
        }
    }
}
