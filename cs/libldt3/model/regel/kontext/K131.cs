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
                /// Wenn Inhalt von FK 8626 = 2, muss entweder FK 8627 oder FK 4111 vorhanden sein.
                /// </summary>
                /// Beide Feldkennungen dürfen gleichzeitig vorhanden sein. Wenn Inhalt von FK 8626
                /// = 1 oder 3, darf FK 8627 und FK 4111 nicht vorhanden sein. Wenn Inhalt von FK
                /// 8626 = 3, darf FK 8617, 8618, 8619  und 8620 nicht vorhanden sein.
                public class K131 : Kontextregel
                {
                    private static readonly ISet<string> FIELDTYPES = ISet.Of("8626", "8627", "8617");

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K131.FIELDTYPES);
                        if (fields.Count != K131.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K131.FIELDTYPES);
                            return false;
                        }

                        TestungRechtsgrundlage? feld8626 = (TestungRechtsgrundlage?)fields["8626"].GetValue(owner);
                        string feld8627 = (string)fields["8627"].GetValue(owner);
                        Beauftragungsgrund? feld8617 = (Beauftragungsgrund?)fields["8617"].GetValue(owner);
                        // Wenn Inhalt von FK 8626 = 2, muss entweder FK 8627 oder FK 4111 vorhanden sein
                        if (feld8626 == TestungRechtsgrundlage.RegionaleSondervereinbarung)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["8627"], owner) && !KontextregelHelper.ContainsAnyValue(fields["4111"], owner))
                            {
                                return false;
                            }

                        }

                        // Wenn Inhalt von FK 8626 = 1 oder 3, darf FK 8627 und FK 4111 nicht vorhanden sein
                        if (feld8626 == TestungRechtsgrundlage.TestV || feld8626 == TestungRechtsgrundlage.Selbstzahler)
                        {
                            if (KontextregelHelper.ContainsAnyValue(fields["8627"], owner) || KontextregelHelper.ContainsAnyValue(fields["4111"], owner))
                            {
                                return false;
                            }

                        }

                        // Wenn Inhalt von FK 8626 = 3, darf FK 8617, 8618, 8619  und 8620 nicht vorhanden sein
                        if (feld8626 == TestungRechtsgrundlage.Selbstzahler)
                        {
                            if (KontextregelHelper.ContainsAnyValue(fields["8617"], owner) || KontextregelHelper.ContainsAnyValue(fields["8618"], owner) || KontextregelHelper.ContainsAnyValue(fields["8619"], owner) || KontextregelHelper.ContainsAnyValue(fields["8620"], owner))
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
