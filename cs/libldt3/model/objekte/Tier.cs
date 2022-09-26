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
using NodaTime;
using libldt3.attributes;
using libldt3.model;
using libldt3.model.enums;
using libldt3.model.regel;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Enthält ein Auftrag Materialien die nicht Humanen Ursprungs sind, so werden
            /// die entsprechenden Informationen zur Materialquelle in diesem Objekt
            /// beschrieben.
            /// </summary>
            [Objekt(Value = "0053")]
            public class Tier : Kontext
            {
                [Feld(Value = "7319", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string IdentifikationsnummerQuelle;
                [Feld(Value = "7313", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ArtRasseMaterial;
                [Feld(Value = "7314", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string NameKennung;
                [Feld(Value = "7315", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 10)]
                public string Alter;
                [Feld(Value = "7326", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Zeiteinheit? AlterIn;
                [Feld(Value = "7351", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? Geburtsdatum;
                [Feld(Value = "8107", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 9)]
                public Anschrift Anschrift;
                [Feld(Value = "8147", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 6)]
                public Person Person;
                [Feld(Value = "8110", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;

            }
        }
    }
}
