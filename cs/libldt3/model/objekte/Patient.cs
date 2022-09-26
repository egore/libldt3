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
            /// In diesem Objekt werden die Informationen über einen Patienten aufgeführt.
            /// </summary>
            [Objekt(Value = "0045")]
            public class Patient : Kontext
            {
                [Feld(Value = "8147", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 6)]
                public Person Person;
                [Feld(Value = "3119", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F013) }, Laenge = 10)]
                public string VersichertenId;
                [Feld(Value = "3105", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MinLaenge = 6, MaxLaenge = 12)]
                public string Versichertennummer;
                [Feld(Value = "7329", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public GeschlechtNormalbereich? Geschlecht;
                [Feld(Value = "7922", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? Sterbedatum;
                [Feld(Value = "3000", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string PatientNumber;
                [Feld(Value = "3620", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> Profession;
                [Feld(Value = "3621", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string CurrentProfession;

            }
        }
    }
}
