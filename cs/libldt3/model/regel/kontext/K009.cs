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

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn der Inhalt von FK 8002 = Obj_0035 (Obj_Laborergebnisbericht), dann muss
                /// mindestens eine FK 8002 mit den Werten Obj_0060(Obj_Untersuchungsergebnis
                /// Klinische Chemie), Obj_0061(Obj_Untersuchungsergebnis Mikrobiologie),
                /// Obj_0062(Obj_Untersuchungsergebnis Krebsfrueherkennung Zervix-Karzinom),
                /// Obj_0063(Obj_Untersuchungsergebnis Zytologie), Obj_0073(Sonstige
                /// Untersuchungsergebnisse) oder Obj_0055(Obj_Blutgruppenzugehoerigkeit) vorhanden
                /// sein.
                /// </summary>
                public class K009 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K009));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "8002" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K009.FIELDTYPES);
                        if (fields.Count != K009.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K009.FIELDTYPES);
                            return false;
                        }

                        string feld8002 = (string)KontextregelHelper.GetFieldValue(fields["8002"], owner);
                        // Wenn Inhalt von FK8002=0035 , muss FK8002 0060 , 0061 , 0062 , 0063 , 0073 oder 0055 vorhanden sein .
                        if (feld8002.Equals("0035"))
                        {
                            return KontextregelHelper.ContainsAnyValue(fields["8002"], owner);
                        }

                        return true;
                    }
                }
            }
        }
    }
}
