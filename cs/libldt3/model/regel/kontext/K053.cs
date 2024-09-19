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
using Microsoft.Extensions.Logging;
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
                /// Wenn Inhalt von FK 7260 = 4 muss FK 7352 vorhanden sein.
                /// </summary>
                /// Angabe der URL des Leistungsverzeichnisses (LVZ) notwendig, wenn auf
                /// LVZ mit URL verwiesen wird.
                public class K053 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K053));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "7260", "7352" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K053.FIELDTYPES);
                        if (fields.Count != K053.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K053.FIELDTYPES);
                            return false;
                        }

                        KatalogIdAnforderbareLeistungen? feld7260 = (KatalogIdAnforderbareLeistungen?)KontextregelHelper.GetFieldValue(fields["7260"], owner);
                        // Wenn Inhalt von FK 7260 = 4 muss FK 7352 vorhanden sein
                        if (feld7260 == KatalogIdAnforderbareLeistungen.sonstige_mitURL)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["7352"], owner))
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
