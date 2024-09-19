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
using NodaTime;
using System.Reflection;
using java.time.chrono;
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
                /// Inhalt der Stellen 3 – 5 der FK 4104 >= 800, dann müssen die FK 3105 und FK 4110
                /// vorhanden sein.
                /// </summary>
                /// Da seit dem 01.01.2015 nur noch bei "originären" SKT die KVKs
                /// zulässig sind, können Behandlungen auf Basis der eingelesen KVKs nur
                /// bei "originären" SKT durchgeführt werden.
                public class K090 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K090));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "4109", "4104", "3105", "4110" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K090.FIELDTYPES);
                        if (fields.Count != K090.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K090.FIELDTYPES);
                            return false;
                        }

                        LocalDate? feld4109 = (LocalDate?)KontextregelHelper.GetFieldValue(fields["4109"], owner);
                        string feld4104 = (string)KontextregelHelper.GetFieldValue(fields["4104"], owner);
                        if (feld4109 != null && !feld4109.IsBefore(LocalDate.Parse("01.01.2015")))
                        {
                            if (int?.ParseInt(feld4104.Substring(3, 5)) >= 800)
                            {
                                return KontextregelHelper.ContainsAnyValue(fields["3105"], owner) && KontextregelHelper.ContainsAnyValue(fields["4110"], owner);
                            }

                        }

                        return true;
                    }
                }
            }
        }
    }
}
