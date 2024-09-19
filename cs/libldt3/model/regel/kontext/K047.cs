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
                /// Wenn Inhalt von FK 7321 = 03, 15 oder 16, darf FK 8119 nicht vorhanden sein.
                /// </summary>
                /// In Obj_0022 (Obj_Einsenderidentifikation) ist Obj_0019
                /// (Obj_Betriebsstaette) nur dann zu verwenden, wenn es sich um eine
                /// medizinische Einrichtung handelt.
                public class K047 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K047));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "7321", "8119" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K047.FIELDTYPES);
                        if (fields.Count != K047.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K047.FIELDTYPES);
                            return false;
                        }

                        IList<Einsenderstatus?> feld7321 = (IList<Einsenderstatus?>)fields["7321"].GetValue(owner);
                        if (feld7321.Contains(Einsenderstatus.Einsender_sonstige) || feld7321.Contains(Einsenderstatus.staatlicheEinrichtung) || feld7321.Contains(Einsenderstatus.sonstige_juristischePerson))
                        {
                            return !KontextregelHelper.ContainsAnyValue(fields["8119"], owner);
                        }

                        return true;
                    }
                }
            }
        }
    }
}
