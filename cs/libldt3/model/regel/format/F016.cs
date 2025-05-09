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
                /// Format Uhrzeit
                /// </summary>
                /// hh = Stunden (00 – 23)
                /// mm = Minuten (00 – 59)
                /// ss = Sekunden (00 – 59)
                /// ms = Millisekunden (000 – 999)
                public class F016 : RegularExpressionRegel
                {
                    public static readonly Regex PATTERN = new Regex("^([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])([0-9]{3})?$");

                    public F016() : base(F016.PATTERN)
                    {
                    }
                }
            }
        }
    }
}
