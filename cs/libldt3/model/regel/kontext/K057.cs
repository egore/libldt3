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
using libldt3.model;
using libldt3.model.enums;
using libldt3.model.objekte;
using libldt3.model.saetze;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// FK 0222 muss vorhanden sein, wenn in mindestens einem  Obj_0059
                /// (Obj_Untersuchungsanforderung) die FK 7303 mit dem Inhalt 8 vorhanden ist.
                /// </summary>
                /// Die ASV-Teamnummer ist anzugeben, wenn Leistungen im Rahmen der
                /// ASV (Ambulante Spezialfachärztliche Versorgung) entsprechend § 116b
                /// des SGB V beauftragt werden.
                public class K057 : Kontextregel
                {
                    public bool IsNotEmpty(Arztidentifikation arztidentifikation)
                    {
                        return arztidentifikation.AsvTeamnummer != null && !string.IsNullOrEmpty(arztidentifikation.AsvTeamnummer);
                    }
                    public bool IsNotEmpty(Fliesstext fliesstext)
                    {
                        return fliesstext.Text != null && !fliesstext.Text.Count == 0;
                    }
                    public bool IsValid(Kontext owner)
                    {
                        Auftrag auftrag = (Auftrag)owner;
                        // Valid, as no Obj_0059 present
                        if (auftrag.Untersuchungsanforderung == null || auftrag.Untersuchungsanforderung.Count == 0)
                        {
                            return true;
                        }

                        foreach (Untersuchungsanforderung untersuchungsanforderung in auftrag.Untersuchungsanforderung)
                        {
                            if (untersuchungsanforderung.AbrechnungsinfoZurUntersuchung == Abrechnungsinfo.ASV)
                            {
                                // No identification at all, not valid
                                if (auftrag.Einsenderidentifikation == null)
                                {
                                    return false;
                                }

                                // If any FK 0222 is present, it's valid
                                return this.IsNotEmpty(auftrag.Einsenderidentifikation.Arztidentifikation) || this.IsNotEmpty(auftrag.Einsenderidentifikation.UeberweisungAn) || this.IsNotEmpty(auftrag.Einsenderidentifikation.UeberweisungVonAnderenAerzten);
                            }

                        }
                        return true;
                    }
                }
            }
        }
    }
}
