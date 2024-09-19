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
using libldt3.model.objekte;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn FK 8147 im Obj_Tier/Sonstiges vorkommt, muss im folgenden Obj_0047 der
                /// Inhalt der FK 7420 = 11 oder 16 sein.
                /// </summary>
                /// <p>
                /// Damit kann die Person im Obj_Tier/Sonstiges übertragen werden, die in
                /// einer gewissen Beziehung zu dem zu untersuchenden Material steht (z.B.
                /// Tierhalter, Eigentümer des eingesandten Materials).
                public class K117 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K117));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "8147" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K117.FIELDTYPES);
                        if (fields.Count != K117.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K117.FIELDTYPES);
                            return false;
                        }

                        Person person = (Person)KontextregelHelper.GetFieldValue(fields["8147"], owner);
                        if (person == null)
                        {
                            return true;
                        }

                        if (person.Status != StatusPerson.Halter && person.Status != StatusPerson.sonstige_juristischePerson)
                        {

                            K117.LOG.Error("Person had invalid status {}", person.Status)
                            ;
                            return false;
                        }

                        return true;
                    }
                }
            }
        }
    }
}
