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
                /// Wenn Feldinhalt von FK 4239 ≠ 28 und wenn FK 0222 vorhanden ist, dann muss
                /// entweder FK 0212 oder FK 0223 vorhanden sein.
                /// </summary>
                /// Wenn Feldinhalt von FK 4239 = 28
                /// und wenn FK 0222 vorhanden ist, dann muss ein FK 0212 vorhanden sein. Die FK
                /// 0223 darf nicht vorhanden sein.
                /// 
                /// Es ist ausgeschlossen, dass ein Krankenhausarzt im Rahmen seiner
                /// ASV-Berechtigung Mitglied einer Laborgemeinschaft ist und in diesem
                /// Zusammenhang Laborleistungen auf Muster 10A anfordert, gemäß § 25
                /// Abs. 3 S. 7 BMV-Ä.
                public class K116 : Kontextregel
                {
                    private static readonly ISet<string> FIELDTYPES = ISet.Of("4239", "0222", "0212", "0223");

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K116.FIELDTYPES);
                        if (fields.Count != K116.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K116.FIELDTYPES);
                            return false;
                        }

                        // FIXME: This field does not exist here and likely must be inherited from the context
                        Scheinuntergruppe? scheinuntergruppe = (Scheinuntergruppe?)KontextregelHelper.GetFieldValue(fields["4239"], owner);
                        if (scheinuntergruppe != Scheinuntergruppe.Muster10A && KontextregelHelper.ContainsAnyValue(fields["0222"], owner))
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["0212"], owner) && !KontextregelHelper.ContainsAnyValue(fields["0223"], owner))
                            {
                                Trace.TraceError("For other than Muster 10A either FK 0212 or 0223 must be present");
                                return false;
                            }

                        }

                        if (scheinuntergruppe == Scheinuntergruppe.Muster10A && KontextregelHelper.ContainsAnyValue(fields["0222"], owner))
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["0212"], owner))
                            {
                                Trace.TraceError("For Muster 10A FK 0212 must be present");
                                return false;
                            }

                            if (KontextregelHelper.ContainsAnyValue(fields["0223"], owner))
                            {
                                Trace.TraceError("For Muster 10A FK 0223 must not be present");
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
