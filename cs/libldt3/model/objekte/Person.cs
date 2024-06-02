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
using NodaTime;
using libldt3.attributes;
using libldt3.model;
using libldt3.model.enums;
using libldt3.model.regel.format;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Mit dem Objekt Person werden alle die natürlichen Personen dargestellt, deren
            /// Daten für die Abwicklung, Abrechnung oder Dokumentation von Aufträgen und
            /// Befunden notwendig sind.
            /// </summary>
            [Objekt(Value = "0047", Kontextregeln = new[] { typeof(K094), typeof(K104) })]
            public class Person : Kontext
            {
                [Feld(Value = "7420", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 2)]
                public StatusPerson? Status;
                [Feld(Value = "3100", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 20)]
                public string Namenszusatz;
                [Feld(Value = "3120", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 20)]
                public string Vorsatzwort;
                [Feld(Value = "3101", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 45)]
                public string Nachname;
                [Feld(Value = "3102", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 45)]
                public IList<string> Vorname;
                [Feld(Value = "3103", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F003) }, Laenge = 8)]
                public LocalDate? Geburtsdatum;
                [Feld(Value = "3104", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 20)]
                public string Titel;
                [Feld(Value = "3110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Geschlecht? Geschlecht;
                [Feld(Value = "3628", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string Muttersprache;
                [Feld(Value = "8990", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string NamenskuerzelNamenszeichen;
                [Feld(Value = "8228", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 13)]
                public Anschrift Wohnanschrift;
                [Feld(Value = "8229", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 23)]
                public Anschrift AnschriftArbeitsstelle;
                [Feld(Value = "8230", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 18)]
                public Anschrift Rechnungsanschrift;
                [Feld(Value = "8232", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 27)]
                public Kommunikationsdaten PrivateKommunikationsdaten;
                [Feld(Value = "8233", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 34)]
                public Kommunikationsdaten GeschaeftlicheKommunikationsdaten;

            }
        }
    }
}
