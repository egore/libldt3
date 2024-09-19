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
using java.util.function;
using java.util.stream;
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
                /// FK 8111 kann nur vorkommen, wenn FK 7286 mit Inhalt ≠ 0 vorkommt.
                /// </summary>
                public class K085 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K085));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "8111", "7286" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K085.FIELDTYPES);
                        if (fields.Count != K085.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K085.FIELDTYPES);
                            return false;
                        }

                        IList<UntersuchungsergebnisMikrobiologie_ResistenzMethode> feld7286 = (IList<UntersuchungsergebnisMikrobiologie_ResistenzMethode>)KontextregelHelper.GetFieldValue(fields["7286"], owner);
                        if (feld7286 != null)
                        {
                            bool found = feld7286.Stream().AnyMatch(// XXX renderExpression CtLambdaImpl is unknown);
    if (!found)
                            {
                                if (KontextregelHelper.ContainsAnyValue(fields["8111"], owner))
                                {
                                    return false;
                                }

                            }

                        }

                        return true;
                    }
                }
            }
        }
    }
}
