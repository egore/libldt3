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
using libldt3.attributes;
using libldt3.model.regel;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Hier werden Informationen zu Wirkstoffen zusammengefasst.
            /// </summary>
            [Objekt(Value = "0071")]
            public class Wirkstoff
            {
                [Feld(Value = "6212", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string Arzneimittelwirkstoff;
                [Feld(Value = "6206", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F020) }, Laenge = 8)]
                public string Pzn;
                [Feld(Value = "6224", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string WirkstoffCode;
                [Feld(Value = "6214", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string WirkstoffKlassifikation;
                [Feld(Value = "8523", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string Wirkstoffmenge;
                [Feld(Value = "8421", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 20)]
                public string Mengeneinheit;

            }
        }
    }
}
