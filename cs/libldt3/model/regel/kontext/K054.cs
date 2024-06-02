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
using System.Diagnostics;
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
                /// Wenn Obj_0042 (Obj_Normalwert) mehr- fach im Obj_0060 (Obj_Untersuchungsergebnis
                /// Klinische Chemie) bzw.
                /// </summary>
                /// Obj_0061 (Obj_Untersuchungsergebnis Mikrobiologie)
                /// vorkommt, darf der Wert 13 in der FK 8424 mehrfach vorkommen, alle anderen Werte
                /// dürfen nur jeweils einmal vorkommen.
                /// 
                /// Falls
                /// für
                /// ein
                /// Untersuchungsergebnis
                /// verschiedene
                /// Normalwerte
                /// angegeben werden, müssen sich die Normalwerte innerhalb eines
                /// Untersuchungsergebnisses hinsichtlich der Normalwertspezifikation
                /// unterscheiden. Ausgenommen davon sind die Normalwertspezifikationen,
                /// die auf "Sonstige Standards" referenzieren.
                public class K054 : Kontextregel
                {

                    public bool IsValid(Kontext owner)
                    {

                        K054.LOG.Warn("Ignoring rule {}", this.GetType().GetSimpleName())
                        ;
                        return true;
                    }
                }
            }
        }
    }
}
