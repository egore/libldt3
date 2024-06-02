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
                /// Wenn der Inhalt von FK 8000 = 8215 und der Inhalt von FK 7303 in mindestens
                /// einem Obj_0059 mit dem Wert 99 vorkommt, muss im Obj_0013 die FK 8313 vorkommen.
                /// </summary>
                /// Änderung eines Auftrages ist nur mittels einer Nachforderung möglich.
                public class K114 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K114));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "8000", "7303", "8313" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K114.FIELDTYPES);
                        if (fields.Count != K114.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K114.FIELDTYPES);
                            return false;
                        }

                        Abrechnungsinfo? feld7303 = (Abrechnungsinfo?)KontextregelHelper.GetFieldValue(fields["7303"], owner);
                        Satzart? feld8000 = (Satzart?)KontextregelHelper.GetFieldValue(fields["8000"], owner);
                        // Wenn der Inhalt von FK 8000 = 8215 und der Inhalt von FK 7303 in mindestens einem Obj_0059 mit dem Wert 99 vorkommt, muss im Obj_0013 die FK 8313 vorkommen
                        if (feld8000 == Satzart.Auftrag && feld7303 == Abrechnungsinfo.storniert)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["8313"], owner))
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
