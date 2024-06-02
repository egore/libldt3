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
using System.Text.RegularExpressions;
using libldt3.model.regel;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace format
            {
                /// <summary>
                /// EBNF*-Format für entweder eine einzelne HPV-Typ-Nummer oder eine
                /// Gruppe von mehreren HPV-Typ-Nummern mit optional vorangestelltem
                /// gerätespezifischen
                /// Gruppennamen.
                /// </summary>
                /// HPV-Typ-Nummer
                /// und
                /// Gruppenname besitzen ein alphanumerisches Format. (Bsp.: 18,
                /// G1:31/33/52/58)
                /// * Erweiterte Backus-Naur-Form
                public class F024 : RegularExpressionRegel
                {
                    public static readonly Regex PATTERN = new Regex("^H([0-9])V-Typ-Nu([0-5][0-9])er | ( [ Gruppe([0-9]{2})([A-Z])me ':' ] H([0-9])V-Typ-Nu([0-5][0-9])er ( ('/' | '_' ) H([0-9])V-Typ- Nu([0-5][0-9])er )+ )$");

                    public F024() : base(F024.PATTERN)
                    {
                    }
                }
            }
        }
    }
}
