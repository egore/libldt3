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
                /// Wenn Inhalt von FK 7362 = 2, dann darf Inhalt FK 7421 im Obj_0048 nur der Wert
                /// 02 sein.
                /// </summary>
                /// Leistungen im Rahmen der Privat-LG werden dem einsendenden Arzt in
                /// Rechnung gestellt.
                public class K093 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K093));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "7362", "7421" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K093.FIELDTYPES);
                        if (fields.Count != K093.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K093.FIELDTYPES);
                            return false;
                        }

                        AbrechnungsartPkv? feld7362 = (AbrechnungsartPkv?)fields["7362"].GetValue(owner);
                        // Wenn Inhalt von FK7362=2 , darf <missing 'FK'>7421 0048 02 .
                        if (feld7362 == AbrechnungsartPkv.AbrechnungPrivatLG)
                        {
                            return !KontextregelHelper.ContainsAnyValue(fields["7421"], owner);
                        }

                        return true;
                    }
                }
            }
        }
    }
}
