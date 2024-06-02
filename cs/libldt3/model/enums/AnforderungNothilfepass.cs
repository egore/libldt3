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
            /// E056
            /// </summary>
            public enum AnforderungNothilfepass
            {
                /// Nothilfepass nur bei Nachweis Erythrozytenantikörper ausfüllen
                Nothilfepass_nur_beiNachweisErythrozytenantikoerper_ausfuellen,
                /// Nothilfepass ausstellen
                Nothilfepass_ausstellen
            }

            public static class AnforderungNothilfepassExtensions
            {
                public static string GetCode(this AnforderungNothilfepass self)
                {
                    switch (self)
                    {
                        case AnforderungNothilfepass.Nothilfepass_nur_beiNachweisErythrozytenantikoerper_ausfuellen: return "0";
                        case AnforderungNothilfepass.Nothilfepass_ausstellen: return "1";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
