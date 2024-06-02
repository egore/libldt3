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
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Mit diesem Objekt werden Organisationsstrukturen abgebildet.
            /// </summary>
            [Objekt(Value = "0043")]
            public class Organisation : Kontext
            {
                [Objekt]
                public class OrganisationFirma_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "1251", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Rechtsform;
                    [Feld(Value = "1252", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<FunktionsbezeichnungPersonInnerhalbOrganisation> FunktionsbezeichnungPersonInnerhalbOrganisation;
                    [Feld(Value = "8229", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 23)]
                    public IList<Anschrift> AnschriftArbeitsstelle;
                    [Feld(Value = "8230", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 18)]
                    public Anschrift Rechnungsanschrift;
                    [Feld(Value = "8131", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 19)]
                    public Kommunikationsdaten Kommunikationsdaten;

                }
                [Objekt(Kontextregeln = new[] { typeof(K092) })]
                public class FunktionsbezeichnungPersonInnerhalbOrganisation : Kontext
                {
                    public string Value;
                    [Feld(Value = "8147", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 6)]
                    public IList<Person> Person;

                }
                [Feld(Value = "1250", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public OrganisationFirma_ OrganisationFirma;

            }
        }
    }
}
