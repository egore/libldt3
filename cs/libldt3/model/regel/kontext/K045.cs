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
                /// Wenn Inhalt von FK 7321 = 03, 04, 05, 06, 08, 11, 12, 14 oder 16 ist, dann muss
                /// FK 8147 vorhanden sein.
                /// </summary>
                public class K045 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K045));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "7321", "8147" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K045.FIELDTYPES);
                        if (fields.Count != K045.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K045.FIELDTYPES);
                            return false;
                        }

                        IList<Einsenderstatus?> feld7321 = (IList<Einsenderstatus?>)fields["7321"].GetValue(owner);
                        if (feld7321.Contains(Einsenderstatus.Einsender_sonstige) || feld7321.Contains(Einsenderstatus.Versicherter) || feld7321.Contains(Einsenderstatus.Rechnungsempfaenger) || feld7321.Contains(Einsenderstatus.Bevollmaechtigter) || feld7321.Contains(Einsenderstatus.Leistungserbringer) || feld7321.Contains(Einsenderstatus.Halter) || feld7321.Contains(Einsenderstatus.Patient) || feld7321.Contains(Einsenderstatus.Ueberweiser) || feld7321.Contains(Einsenderstatus.sonstige_juristischePerson))
                        {
                            return KontextregelHelper.ContainsAnyValue(fields["8147"], owner);
                        }

                        return true;
                    }
                }
            }
        }
    }
}
