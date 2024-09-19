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
                /// Wenn im Obj_0062 die FK 7414 vorkommt und der Inhalt von FK 7414 ungleich 0 ist,
                /// dann müssen die FK 7405, FK 7406, FK 7407, FK 7408, FK 7409, FK 7410, FK 7411
                /// und FK 7412 vorkommen.
                /// </summary>
                /// Wenn im Obj_0062 die FK 7414 nicht vorkommt, dann dürfen
                /// die FK 7405, FK 7406, FK 7407, FK 7408, FK 7409, FK 7410, FK 7411 und FK 7412
                /// nicht vorkommen.
                public class K134 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K134));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "7414", "7405", "7406", "7407", "7408", "7409", "7410", "7411", "7412" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K134.FIELDTYPES);
                        if (fields.Count != K134.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K134.FIELDTYPES);
                            return false;
                        }

                        if (KontextregelHelper.ContainsAnyValue(fields["7414"], owner))
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["7405"], owner) || !KontextregelHelper.ContainsAnyValue(fields["7406"], owner) || !KontextregelHelper.ContainsAnyValue(fields["7407"], owner) || !KontextregelHelper.ContainsAnyValue(fields["7408"], owner) || !KontextregelHelper.ContainsAnyValue(fields["7409"], owner) || !KontextregelHelper.ContainsAnyValue(fields["7410"], owner) || !KontextregelHelper.ContainsAnyValue(fields["7411"], owner) || !KontextregelHelper.ContainsAnyValue(fields["7412"], owner))
                            {
                                return false;
                            }

                        }
                        else
                        {
                            if (KontextregelHelper.ContainsAnyValue(fields["7405"], owner) || KontextregelHelper.ContainsAnyValue(fields["7406"], owner) || KontextregelHelper.ContainsAnyValue(fields["7407"], owner) || KontextregelHelper.ContainsAnyValue(fields["7408"], owner) || KontextregelHelper.ContainsAnyValue(fields["7409"], owner) || KontextregelHelper.ContainsAnyValue(fields["7410"], owner) || KontextregelHelper.ContainsAnyValue(fields["7411"], owner) || KontextregelHelper.ContainsAnyValue(fields["7412"], owner))
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
