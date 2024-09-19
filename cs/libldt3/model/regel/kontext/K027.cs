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

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn Inhalt von FK 8000 = 8215, dann muss im
                /// Obj_0001(Obj_Abrechnungsinformationen) mindestens einmal eine Feldkennung aus
                /// nachfolgender Liste vorhanden sein: 8102, 8103, 8104, 8105, 8106, 8109.
                /// </summary>
                public class K027 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K027));
                    private static readonly ISet<string> FIELDTYPES = new HashSet { "8000", "8102", "8103", "8104", "8105", "8106", "8109" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K027.FIELDTYPES);
                        if (fields.Count != K027.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K027.FIELDTYPES);
                            return false;
                        }

                        Satzart? feld8000 = (Satzart?)KontextregelHelper.GetFieldValue(fields["8000"], owner);
                        // Wenn Inhalt von FK 8000 = 8215, dann muss im Obj_0001(Obj_Abrechnungsinformationen) mindestens einmal eine Feldkennung aus nachfolgender Liste vorhanden sein: 8102, 8103, 8104, 8105, 8106, 8109
                        if (feld8000 == Satzart.Auftrag)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["8102"], owner) && !KontextregelHelper.ContainsAnyValue(fields["8103"], owner) && !KontextregelHelper.ContainsAnyValue(fields["8104"], owner) && !KontextregelHelper.ContainsAnyValue(fields["8105"], owner) && !KontextregelHelper.ContainsAnyValue(fields["8106"], owner) && !KontextregelHelper.ContainsAnyValue(fields["8109"], owner))
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
