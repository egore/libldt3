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
namespace libldt3
{
    namespace model
    {
        namespace enums
        {

            /// <summary>
            /// E035
            /// </summary>
            public enum AnorganischesMaterial
            {
                Wasser,
                Luft,
                /// nicht bestimmbar
                nicht_bestimmbar,
                sonstiges
            }

            public static class AnorganischesMaterialExtensions
            {
                public static string GetCode(this AnorganischesMaterial self)
                {
                    switch (self)
                    {
                        case AnorganischesMaterial.Wasser: return "1";
                        case AnorganischesMaterial.Luft: return "2";
                        case AnorganischesMaterial.nicht_bestimmbar: return "3";
                        case AnorganischesMaterial.sonstiges: return "4";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
