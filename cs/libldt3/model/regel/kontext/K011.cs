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
                /// Wenn Inhalt von FK 8000 = 8215 und FK 8002 = Obj_0059
                /// (Obj_Untersuchungsanforderung) und FK 7303 = 2 oder 10 dann muss FK 8410
                /// vorhanden sein und FK 7260 darf nicht vorhanden sein.
                /// </summary>
                public class K011 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K011));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "8000", "8002", "7303", "8410" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K011.FIELDTYPES);
                        if (fields.Count != K011.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K011.FIELDTYPES);
                            return false;
                        }

                        Abrechnungsinfo? feld7303 = (Abrechnungsinfo?)KontextregelHelper.GetFieldValue(fields["7303"], owner);
                        Satzart? feld8000 = (Satzart?)KontextregelHelper.GetFieldValue(fields["8000"], owner);
                        object feld8002 = KontextregelHelper.GetFieldValue(fields["8002"], owner);
                        // Wenn Inhalt von FK 8000 = 8215 und FK 8002 = Obj_0059 (Obj_Untersuchungsanforderung) und FK 7303 = 2 oder 10 dann muss FK 8410 vorhande
                        if (feld8000 == Satzart.Auftrag && feld8002 is Untersuchungsanforderung && feld7303 == Abrechnungsinfo.GKV_LG || feld7303 == Abrechnungsinfo.GKV_LG_praeventiv)
                        {
                            if (KontextregelHelper.ContainsAnyValue(fields["8410"], owner))
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
