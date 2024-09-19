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
                /// FK 3112 und/oder FK 3121 muss vorhanden sein.
                /// </summary>
                /// Ausnahmen: Nur wenn FK 3114
                /// vorhanden und der Feldinhalt ungleich "D" ist, dann gilt: Ist die FK 4109
                /// vorhanden, dann muss die FK 3112 nicht vorhanden sein. Nur wenn FK 3124
                /// vorhanden und der Feldinhalt ungleich "D" ist, dann gilt: Ist die FK 4109
                /// vorhanden, dann muss die FK 3121 nicht vorhanden sein.
                /// 
                /// Diese Regel beschreibt die mindestens erforderlichen Angaben im
                /// Obj_0007 (Anschrift). Grundlage für diese Regel bilden die Vorgaben des
                /// KVDT.
                public class K017 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K017));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "3112", "3121", "3114", "3124" };

                    public bool CheckExclusion(object owner, IDictionary<string, FieldInfo> fields, string first, string second)
                    {
                        string value = (string)fields[first].GetValue(owner);
                        // XXX 4109 does not exist on the current object, likely we need to traverse the object tree to find it in one
                        // of the holding classes
                        if (value != null && !"D".Equals(value) && KontextregelHelper.ContainsAnyValue(fields["4109"], owner) && KontextregelHelper.ContainsAnyValue(fields[second], owner))
                        {
                            Trace.TraceError("FK {0} is present and not 'D'. Also FK 4109 is present. Then {1} must not be present", first, second);
                            return true;
                        }

                        return false;
                    }
                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K017.FIELDTYPES);
                        if (fields.Count != K017.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K017.FIELDTYPES);
                            return false;
                        }

                        return !this.CheckExclusion(owner, fields, "3114", "3112") && !this.CheckExclusion(owner, fields, "3124", "3121") && KontextregelHelper.ContainsAnyValue(fields["3112"], owner) || KontextregelHelper.ContainsAnyValue(fields["3121"], owner);
                    }
                }
            }
        }
    }
}
