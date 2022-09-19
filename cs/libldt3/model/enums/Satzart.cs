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
namespace libldt3
{
    namespace model
    {
        namespace enums
        {

            /// <summary>
            /// E004
            /// </summary>
            public enum Satzart
            {
                LaborDatenpaketHeader,
                LaborDatenpaketAbschluss,
                PraxisDatenpaketHeader,
                PraxisDatenpaketAbschluss,
                Befund,
                Auftrag
            }

            public static class SatzartExtensions
            {
                public static string GetCode(this Satzart self)
                {
                    switch (self)
                    {
                        case Satzart.LaborDatenpaketHeader: return "8220";
                        case Satzart.LaborDatenpaketAbschluss: return "8221";
                        case Satzart.PraxisDatenpaketHeader: return "8230";
                        case Satzart.PraxisDatenpaketAbschluss: return "8231";
                        case Satzart.Befund: return "8205";
                        case Satzart.Auftrag: return "8215";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
