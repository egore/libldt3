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
using libldt3.model;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Hier werden alle Informationen zusammengefasst, die im Kontext mit der
            /// Erstellung des Datensatzes stehen.
            /// </summary>
            [Objekt(Value = "0032")]
            public class Kopfdaten : Kontext
            {
                [Feld(Value = "8151", Name = "Sendendes_System", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 16)]
                public SendendesSystem SendendesSystem;
                [Feld(Value = "8218", Name = "Timestamp_Erstellung_Datensatz", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 30)]
                public Timestamp TimestampErstellungDatensatz;
                [Feld(Value = "8212", Name = "Softwareverantwortlicher", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 24)]
                public Organisation Softwareverantwortlicher;

            }
        }
    }
}
