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
                /// Wenn FK 8002 = Obj_0068 der FK 8242 folgt, muss FK 6329 in diesem Objekt
                /// vorkommen und FK 3564 darf nicht vorkommen.
                /// </summary>
                /// Wenn FK 8002 = Obj_0068 den FK 8167,
                /// FK 8217, FK 8236, FK 8237 oder FK 8238 folgt, muss FK 3564 in diesem Objekt
                /// vorkommen und FK 6329 darf nicht vorkommen.
                public class K100 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K100));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "8002", "8242", "6329", "3564", "8167", "8217", "8236", "8237", "8238" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K100.FIELDTYPES);
                        if (fields.Count != K100.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K100.FIELDTYPES);
                            return false;
                        }

                        throw new NotImplementedException();
                    }
                }
            }
        }
    }
}
