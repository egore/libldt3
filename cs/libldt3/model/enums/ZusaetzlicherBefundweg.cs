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
            /// E013
            /// </summary>
            public enum ZusaetzlicherBefundweg
            {
                Papier,
                Telefon,
                Fax,
                /// E-Mail
                E_Mail,
                /// DFÜ
                DFUe,
                Tourpost,
                /// KV-Connect
                KV_Connect
            }

            public static class ZusaetzlicherBefundwegExtensions
            {
                public static string GetCode(this ZusaetzlicherBefundweg self)
                {
                    switch (self)
                    {
                        case ZusaetzlicherBefundweg.Papier: return "0";
                        case ZusaetzlicherBefundweg.Telefon: return "1";
                        case ZusaetzlicherBefundweg.Fax: return "2";
                        case ZusaetzlicherBefundweg.E_Mail: return "3";
                        case ZusaetzlicherBefundweg.DFUe: return "4";
                        case ZusaetzlicherBefundweg.Tourpost: return "5";
                        case ZusaetzlicherBefundweg.KV_Connect: return "6";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
