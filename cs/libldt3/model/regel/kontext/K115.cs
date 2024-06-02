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

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Entweder die FK 0212 oder die FK 0223 muss jeweils mindestens einmal vorkommen.
                /// </summary>
                public class K115 : Kontextregel
                {
                    private static readonly ISet<string> FIELDTYPES = ISet.Of("0212", "0223");

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K115.FIELDTYPES);
                        if (fields.Count != K115.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K115.FIELDTYPES);
                            return false;
                        }

                        IList<string> lebenslangeArztnummer = (IList<string>)KontextregelHelper.GetFieldValue(fields["0212"], owner);
                        IList<string> pseudoLanrFuerKrankenhausaerzteRahmenAsvAbrechnung = (IList<string>)KontextregelHelper.GetFieldValue(fields["0223"], owner);
                        if (lebenslangeArztnummer != null && !lebenslangeArztnummer.Count == 0 || pseudoLanrFuerKrankenhausaerzteRahmenAsvAbrechnung != null && !pseudoLanrFuerKrankenhausaerzteRahmenAsvAbrechnung.Count == 0)
                        {
                            return true;
                        }

                        Trace.TraceError("Either lebenslangeArztnummer or pseudoLanrFuerKrankenhausaerzteRahmenAsvAbrechnung is required");
                        return false;
                    }
                }
            }
        }
    }
}
