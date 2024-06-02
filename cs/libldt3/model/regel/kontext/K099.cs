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
                /// Wenn der Inhalt der FK 8422 = !L oder !- oder !H oder !+ ist, muss FK 8126 der
                /// FK 8422 folgen.
                /// </summary>
                /// Obj_Fehlermeldung/Aufmerksamkeit muss bei Extremwerten eingesetzt
                /// werden, um den Befundempfänger auf die Werte hinzuweisen.
                public class K099 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K099));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "8422", "8126" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K099.FIELDTYPES);
                        if (fields.Count != K099.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K099.FIELDTYPES);
                            return false;
                        }

                        object o = fields["8422"].GetValue(owner);
                        if (o is IEnumerable<object>)
                        {
                            foreach (GrenzwertindikatorErweitert grenzwertindikatorErweitert in (IEnumerable<GrenzwertindikatorErweitert>)o)
                            {
                                Grenzwertindikator? grenzwertindikator = grenzwertindikatorErweitert.Value;
                                if (grenzwertindikator == Grenzwertindikator.Wert_extrem_erniedrigt_ || grenzwertindikator == Grenzwertindikator.Wert_extrem_erniedrigt__ || grenzwertindikator == Grenzwertindikator.Wert_extrem_erhoeht || grenzwertindikator == Grenzwertindikator.Wert_extrem_erhoeht_ && !KontextregelHelper.ContainsAnyValue(fields["8126"], grenzwertindikatorErweitert))
                                {
                                    return false;
                                }

                            }
                            return true;
                        }
                        else
                        {
                            GrenzwertindikatorErweitert indikatorErweitert = (GrenzwertindikatorErweitert)o;
                            Grenzwertindikator? grenzwertindikator = indikatorErweitert.Value;
                            if (grenzwertindikator == Grenzwertindikator.Wert_extrem_erniedrigt_ || grenzwertindikator == Grenzwertindikator.Wert_extrem_erniedrigt__ || grenzwertindikator == Grenzwertindikator.Wert_extrem_erhoeht || grenzwertindikator == Grenzwertindikator.Wert_extrem_erhoeht_ && !KontextregelHelper.ContainsAnyValue(fields["8126"], o))
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
