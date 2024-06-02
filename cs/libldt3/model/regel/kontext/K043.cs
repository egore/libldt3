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
                /// Wenn FK 0204 im Obj_0019 (Obj_Betriebsstätte) nur einmal vorkommt, muss der
                /// Inhalt der FK 0204 = 1, 2, 3 oder 4 sein.
                /// </summary>
                /// Wenn FK 0204 im Obj_0019
                /// (Obj_Betriebsstätte) zweimal vorkommt, muss der Inhalt der FK 0204 einmal mit 1,
                /// 2, 3 oder 4 und einmal mit 5 oder 6 gefüllt sein. Im Obj_0019
                /// (Obj_Betriebsstätte) darf die FK 0204 maximal zweimal vorkommen.
                /// 
                /// Im Obj_0019 (Obj_Betriebsstätte) ist mit der FK 0204 (Status der
                /// Betriebsstätte) zwingend anzugeben, ob es sich bei der Betriebsstätte um
                /// eine Arztpraxis (1), eine Laborarztpraxis (2), eine Laborgemeinschaft (3)
                /// oder eine sonstige medizinische Einrichtung (4) handelt.
                /// Ergänzend kann mit einem zweiten Vorkommen der FK 0204 (Status der
                /// Betriebsstätte) angegeben werden, ob es sich bei der oben
                /// beschriebenen Betriebsstätte um eine Hauptbetriebsstätte (5) oder eine
                /// Nebenbetriebsstätte (6) handelt.
                /// Die Feldkennung FK 0204 (Status der Betriebsstätte) darf maximal
                /// zweimal im Obj_Betriebsstätte vorkommen.
                public class K043 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K043));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "0204" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K043.FIELDTYPES);
                        if (fields.Count != K043.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K043.FIELDTYPES);
                            return false;
                        }

                        IList<Betriebsstaettenstatus?> stati = (IList<Betriebsstaettenstatus?>)fields["0204"].GetValue(owner);
                        if (stati == null || stati.Count == 0)
                        {
                            Trace.TraceError("Requires one or two states, got none");
                            return false;
                        }

                        // Wenn FK 0204 im Obj_0019 (Obj_Betriebsstätte) nur einmal vorkommt, muss der Inhalt der FK 0204 = 1, 2, 3 oder
                        // 4 sein.
                        if (stati.Size() == 1)
                        {
                            foreach (Betriebsstaettenstatus? status in stati)
                            {
                                if (status == Betriebsstaettenstatus.Arztpraxis || status == Betriebsstaettenstatus.Laborarztpraxis || status == Betriebsstaettenstatus.Laborgemeinschaft || status == Betriebsstaettenstatus.sonstige_medizinischeEinrichtung)
                                {
                                    return true;
                                }

                            }
                            Trace.TraceError("Only one state given, need to be 1, 2, 3 or 4");
                            return false;
                        }

                        // Wenn FK 0204 im Obj_0019 (Obj_Betriebsstätte) zweimal vorkommt, muss der Inhalt der FK 0204 einmal mit 1, 2,
                        // 3 oder 4 und einmal mit 5 oder 6 gefüllt sein.
                        if (stati.Size() == 2)
                        {
                            bool oneToFour = false;
                            bool fiveOrSix = false;
                            foreach (Betriebsstaettenstatus? status in stati)
                            {
                                if (status == Betriebsstaettenstatus.Arztpraxis || status == Betriebsstaettenstatus.Laborarztpraxis || status == Betriebsstaettenstatus.Laborgemeinschaft || status == Betriebsstaettenstatus.sonstige_medizinischeEinrichtung)
                                {
                                    // XXX renderExpression CtVariableWriteImpl is unknown = true;
                                }
                                else
                                {
                                    if (status == Betriebsstaettenstatus.Hauptbetriebsstaette || status == Betriebsstaettenstatus.Nebenbetriebsstaette)
                                    {
                                        // XXX renderExpression CtVariableWriteImpl is unknown = true;
                                    }

                                }

                            }
                            if (oneToFour && fiveOrSix)
                            {
                                return true;
                            }

                            Trace.TraceError("Two states given, need to be 1, 2, 3 or 4 and 5 or 6");
                            return false;
                        }


                        K043.LOG.Error("Requires one or two states, got {}", stati.Size())
                        ;
                        return false;
                    }
                }
            }
        }
    }
}
