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
                /// Wenn Inhalt von FK 8418 = 11 oder FK 7368 vorhanden ist, muss FK 8126 im
                /// Obj_0037 vorhanden sein.
                /// </summary>
                /// Wenn auf Grund von fehlendem bzw. nicht verwertbarem Material die
                /// Analytik nicht durchgeführt werden konnte, muss der Einsender im Befund
                /// darauf aufmerksam gemacht werden.
                public class K082 : Kontextregel
                {
                    private static readonly ISet<string> FIELDTYPES = ISet.Of("8418", "8126");

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K082.FIELDTYPES);
                        if (fields.Count != K082.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K082.FIELDTYPES);
                            return false;
                        }

                        TestStatus? feld8418 = (TestStatus?)KontextregelHelper.GetFieldValue(fields["8418"], owner);
                        // Wenn Inhalt von FK 8418 = 11 oder FK 7368 vorhanden ist, muss FK 8126 im Obj_0037 vorhanden sein
                        if (feld8418 == TestStatus.Material_fehlt)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["8126"], owner))
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
