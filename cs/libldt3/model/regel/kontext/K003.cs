/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
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
                /// Wird die FK 8410 (Test-Ident) im Kontext mit der Überweisung von
                /// Laborleistungen an einen Laborfacharzt verwendet, muss die FK 8411
                /// (Testbezeichnung) im Datensatz vorkommen (mit Inhalt der FK 8411 muss das
                /// Auftragsfeld des digitalen Musters 10 befüllt werden).
                /// </summary>
                public class K003 : Kontextregel
                {
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "7303", "8410", "8411" };

                    public bool IsValid(object owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K003.FIELDTYPES);
                        if (fields.Count != K003.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K003.FIELDTYPES);
                            return false;
                        }

                        Abrechnungsinfo? abrechnungsinfo = (Abrechnungsinfo?)fields["7303"].GetValue(owner);
                        if (abrechnungsinfo == null)
                        {
                            return true;
                        }

                        // Wenn Feldinhalt von FK 7303 = 1, 8 oder 9 ist und FK 8410 vorhanden, muss auch FK 8411 vorhanden sein.
                        if (abrechnungsinfo == Abrechnungsinfo.GkvLaborfacharzt || abrechnungsinfo == Abrechnungsinfo.Asv || abrechnungsinfo == Abrechnungsinfo.GkvLaborfacharztPraeventiv)
                        {
                            if (KontextregelHelper.ContainsAnyString(fields["8410"], owner))
                            {
                                return KontextregelHelper.ContainsAnyString(fields["8411"], owner);
                            }

                        }

                        return true;
                    }
                }
            }
        }
    }
}
