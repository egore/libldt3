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

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            /// <summary>
            /// Base class to implement regular expression based rules
            /// </summary>
            public abstract class RegularExpressionRegel : Regel
            {
                public readonly Regex Pattern;

                public RegularExpressionRegel(Regex pattern)
                {
                    this.Pattern = pattern;
                }
                public bool IsValid(string value)
                {
                    if (value == null)
                    {
                        return true;
                    }

                    // CharSequence is allowed, try to interpret the value as this first
                    return this.Pattern.IsMatch(value);
                }
            }
        }
    }
}
