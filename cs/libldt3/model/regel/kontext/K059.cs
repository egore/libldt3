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
                /// Mindestens eine der FK 7330, FK 7331, FK 7332, FK 7333, FK 7334 oder FK 7335
                /// muss vorhanden sein.
                /// </summary>
                public class K059 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K059));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "7330", "7331", "7332", "7333", "7334", "7335" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K059.FIELDTYPES);
                        if (fields.Count != K059.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K059.FIELDTYPES);
                            return false;
                        }

                        if (!KontextregelHelper.ContainsAnyValue(fields["7330"], owner) && !KontextregelHelper.ContainsAnyValue(fields["7331"], owner) && !KontextregelHelper.ContainsAnyValue(fields["7332"], owner) && !KontextregelHelper.ContainsAnyValue(fields["7333"], owner) && !KontextregelHelper.ContainsAnyValue(fields["7334"], owner) && !KontextregelHelper.ContainsAnyValue(fields["7335"], owner))
                        {
                            Trace.TraceError("One of FK 7330, 7331, 7332, 7333, 7334, 7335 must be present");
                            return false;
                        }

                        return true;
                    }
                }
            }
        }
    }
}
