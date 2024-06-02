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
                /// Für Satzart 8215 gilt: Wenn Inhalt von FK 7303 = 1 muss FK 4239 = 27 vorhanden
                /// sein.
                /// </summary>
                /// Wenn Inhalt von FK 7303 = 2 muss FK 4239 = 28 vorhanden sein. Wenn Inhalt
                /// von FK 7303 = 9 muss FK 4239 = 27 in Kombination mit FK 4221 = 2 vorhanden sein.
                /// Wenn Inhalt von FK 7303 = 10 muss FK 4239 = 28 in Kombination mit FK 4221 = 2
                /// vorhanden sein.
                /// 
                /// Abhängigkeit der Abrechnungsinformation von den Abrechnungsobjekten
                /// und deren Inhalten
                public class K032 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K032));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "7303", "4239" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K032.FIELDTYPES);
                        if (fields.Count != K032.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K032.FIELDTYPES);
                            return false;
                        }

                        Abrechnungsinfo? feld7303 = (Abrechnungsinfo?)fields["7303"].GetValue(owner);
                        // Wenn Inhalt von FK7303=1 muss FK4239 = 27 vorhanden sein .
                        if (feld7303 == Abrechnungsinfo.GKV_Laborfacharzt)
                        {
                            return KontextregelHelper.ContainsAnyValue(fields["4239"], owner);
                        }

                        // Wenn Inhalt von FK7303=2 muss FK4239 = 28 vorhanden sein .
                        if (feld7303 == Abrechnungsinfo.GKV_LG)
                        {
                            return KontextregelHelper.ContainsAnyValue(fields["4239"], owner);
                        }

                        // Wenn Inhalt von FK7303=9 muss FK4239 = 27 FK 4221 = 2 vorhanden sein .
                        if (feld7303 == Abrechnungsinfo.GKV_Laborfacharzt_praeventiv)
                        {
                            return KontextregelHelper.ContainsAnyValue(fields["4239"], owner);
                        }

                        // Wenn Inhalt von FK7303=10 muss FK4239 = 28 FK 4221 = 2 vorhanden sein .
                        if (feld7303 == Abrechnungsinfo.GKV_LG_praeventiv)
                        {
                            return KontextregelHelper.ContainsAnyValue(fields["4239"], owner);
                        }

                        return true;
                    }
                }
            }
        }
    }
}
