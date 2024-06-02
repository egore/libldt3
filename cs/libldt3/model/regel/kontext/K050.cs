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

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn Inhalt von FK 8002 = Obj_0002 (Obj_Abrechnung GKV) vorhanden, dann muss FK
                /// 0105 und FK 4239 vorhanden sein.
                /// </summary>
                public class K050 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K050));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "8002", "0105", "4239" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K050.FIELDTYPES);
                        if (fields.Count != K050.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K050.FIELDTYPES);
                            return false;
                        }

                        string feld8002 = (string)fields["8002"].GetValue(owner);
                        // Wenn Inhalt von FK8002=0002 muss FK0105 4239 vorhanden sein .
                        if (feld8002.Equals("0002"))
                        {
                            return KontextregelHelper.ContainsAnyValue(fields["0105"], owner) && KontextregelHelper.ContainsAnyValue(fields["4239"], owner);
                        }

                        return true;
                    }
                }
            }
        }
    }
}
