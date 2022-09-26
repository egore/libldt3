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
using libldt3.model.enums;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Das Objekt Anschrift definiert die Adresse.
            /// </summary>
            /// Dabei kann es sich entweder um
            /// ein Postfach oder um eine physische Adresse handeln.
            [Objekt(Value = "0007", Kontextregeln = new[] { typeof(K017) })]
            public class Anschrift : Kontext
            {
                [Feld(Value = "3112", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 10)]
                public string Plz;
                [Feld(Value = "3113", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 40)]
                public string Ort;
                [Feld(Value = "3107", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 46)]
                public string Strasse;
                [Feld(Value = "3109", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 9)]
                public string Hausnummer;
                [Feld(Value = "3115", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 40)]
                public string Anschriftenzusatz;
                [Feld(Value = "3114", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 3)]
                public string Wohnsitzlaendercode;
                [Feld(Value = "3121", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 10)]
                public string PostfachPlz;
                [Feld(Value = "3122", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 40)]
                public string PostfachOrt;
                [Feld(Value = "3123", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 8)]
                public string Postfach;
                [Feld(Value = "3124", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 3)]
                public string PostfachWohnsitzlaendercode;
                [Feld(Value = "1202", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public IList<Adresstyp?> Adresstyp;

            }
        }
    }
}
