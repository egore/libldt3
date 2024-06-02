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
using libldt3.model.regel;
using libldt3.model.regel.format;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn Inhalt von FK 4121 = 0, 1 oder 2, dann gilt für den Inhalt FK 5001 die
                /// Regel F009.
                /// </summary>
                public class K019 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K019));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "4121", "5001" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<Kontext, IDictionary<string, FieldInfo>> fields = KontextregelHelper.FindFieldsRecursive(owner, K019.FIELDTYPES);
                        if (fields.Count != K019.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K019.FIELDTYPES);
                            return false;
                        }

                        Untersuchungsabrechnung_Gebuehrenordnung gebuehrenordnung = (Untersuchungsabrechnung_Gebuehrenordnung)KontextregelHelper.GetFieldValue(fields[owner]["4121"], owner);
                        if (gebuehrenordnung == null)
                        {
                            return true;
                        }

                        if (gebuehrenordnung.Value == Gebuehrenordnung.EBM || gebuehrenordnung.Value == Gebuehrenordnung.BMAe || gebuehrenordnung.Value == Gebuehrenordnung.EGO)
                        {
                            IList<Gebuehrennummer_> gebuehrennummern = (IList<Gebuehrennummer_>)KontextregelHelper.GetFieldValue(fields[gebuehrenordnung]["5001"], gebuehrenordnung);
                            foreach (Gebuehrennummer_ gebuehrennummer in gebuehrennummern)
                            {
                                if (!new F009().IsValid(gebuehrennummer.Value))
                                {
                                    Trace.TraceError("Invalid number " + gebuehrennummer.Value);
                                    return false;
                                }

                            }
                        }

                        return true;
                    }
                }
            }
        }
    }
}
