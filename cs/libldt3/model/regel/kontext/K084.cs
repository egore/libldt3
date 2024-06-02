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

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn in Satzart 8220 oder 8205 die FK 7266 mit den Inhalten 1 oder 2 vorkommt,
                /// muss in Satzart 8205 die FK 8145 oder FK 8153 vorkommen.
                /// </summary>
                public class K084 : Kontextregel
                {
                    private static readonly ISet<string> FIELDTYPES = ISet.Of("7266", "8145", "8153");

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K084.FIELDTYPES);
                        if (fields.Count != K084.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K084.FIELDTYPES);
                            return false;
                        }

                        Laborart? feld7266 = (Laborart?)KontextregelHelper.GetFieldValue(fields["7266"], owner);
                        if (feld7266 == Laborart.Laborgemeinschaft || feld7266 == Laborart.Facharztlabor)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["8145"], owner) && !KontextregelHelper.ContainsAnyValue(fields["8153"], owner))
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
