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

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn Inhalt FK 8002 = Obj_0002 (Obj_Abrechnung GKV) und FK 0201 in Satzart 8230
                /// oder 8215 vorhanden, dann muss auch FK 0212 oder FK 0223 in Satzart 8230 oder
                /// 8215 vorhanden sein.
                /// </summary>
                /// Die Angabe der BSNR und der LANR ist bei Anforderungen, die im
                /// Kontext der kassenärztlichen Versorgung beauftragt werden, obligat.
                public class K020 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K020));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "8002", "0201", "0212", "0223" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K020.FIELDTYPES);
                        if (fields.Count != K020.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K020.FIELDTYPES);
                            return false;
                        }

                        throw new NotImplementedException();
                    }
                }
            }
        }
    }
}
