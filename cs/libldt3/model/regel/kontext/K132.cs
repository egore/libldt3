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
                /// Wenn Inhalt von FK 8626 = 1, muss innerhalb des entsprechenden Objektes min.
                /// </summary>
                /// die
                /// FK 8617 oder die FK 8631 vorhanden sein. Es kann eine beliebige Kombination der
                /// zwei Feldkennungen vorhanden sein.
                public class K132 : Kontextregel
                {
                    private static readonly ISet<string> FIELDTYPES = ISet.Of("8626", "8617", "8631");

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K132.FIELDTYPES);
                        if (fields.Count != K132.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K132.FIELDTYPES);
                            return false;
                        }

                        TestungRechtsgrundlage? feld8626 = (TestungRechtsgrundlage?)KontextregelHelper.GetFieldValue(fields["8626"], owner);
                        // Wenn Inhalt von FK 8626 = 1, muss innerhalb des entsprechenden Objektes min. die FK 8617 oder die FK 8631 vorhanden sein
                        if (feld8626 == TestungRechtsgrundlage.TestV)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["8617"], owner) && !KontextregelHelper.ContainsAnyValue(fields["8631"], owner))
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
