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
using libldt3.attributes;
using libldt3.model;
using libldt3.model.enums;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Das Objekt dient der Darstellung und elektronischen Übermittlung von
            /// Namenskennzeichnungen.
            /// </summary>
            [Objekt(Value = "0041")]
            public class Namenskennung : Kontext
            {
                [Objekt]
                public class Namenskennung_StatusPerson : Kontext
                {
                    public StatusPerson? Value;
                    [Feld(Value = "7358", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public NameKlartext NameKlartext;

                }
                [Objekt]
                public class NameKlartext : Kontext
                {
                    public string Value;
                    [Feld(Value = "8990", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string NamenskuerzelNamenszeichen;
                    [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 6)]
                    public Anhang Anhang;

                }
                [Feld(Value = "7420", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public Namenskennung_StatusPerson StatusPerson;

            }
        }
    }
}
