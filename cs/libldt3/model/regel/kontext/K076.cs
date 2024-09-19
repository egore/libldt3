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
                /// Wenn Inhalt von FK 8418 ≠ 01 oder 02 oder 09 oder 11 oder 12 ist, dann muss FK
                /// 8225 mindestens einmal vorkommen.
                /// </summary>
                /// Der Zeitpunkt der Messung muss immer angegeben werden, außer bei
                /// fehlendem oder unvollständigem Material, fehlendem Wert oder einer
                /// Stornierung.
                public class K076 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K076));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "3412", "3413", "3414", "3415", "3416", "3417", "3418", "3419", "8225" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K076.FIELDTYPES);
                        if (fields.Count != K076.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K076.FIELDTYPES);
                            return false;
                        }

                        if (KontextregelHelper.ContainsAnyValue(fields["3412"], owner) || KontextregelHelper.ContainsAnyValue(fields["3413"], owner) || KontextregelHelper.ContainsAnyValue(fields["3414"], owner) || KontextregelHelper.ContainsAnyValue(fields["3415"], owner) || KontextregelHelper.ContainsAnyValue(fields["3416"], owner) || KontextregelHelper.ContainsAnyValue(fields["3417"], owner) || KontextregelHelper.ContainsAnyValue(fields["3418"], owner) || KontextregelHelper.ContainsAnyValue(fields["3419"], owner))
                        {
                            return KontextregelHelper.ContainsAnyValue(fields["8225"], owner);
                        }

                        return true;
                    }
                }
            }
        }
    }
}
