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
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Hier werden alle notwendigen Informationen zum Einsender zusammengefasst.
            /// </summary>
            [Objekt(Value = "0022", Kontextregeln = new[] { typeof(K016), typeof(K041), typeof(K045), typeof(K046), typeof(K047), typeof(K048), typeof(K107) })]
            public class Einsenderidentifikation : Kontext
            {
                [Objekt]
                public class KundenNummer_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "7267", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string DesAuftraggebersId;

                }
                [Feld(Value = "7321", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public IList<Einsenderstatus?> StatusEinsender;
                [Feld(Value = "8312", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 20)]
                public KundenNummer_ KundenNummer;
                [Feld(Value = "8114", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 18)]
                public Arztidentifikation Arztidentifikation;
                [Feld(Value = "8240", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 32)]
                public Arztidentifikation UeberweisungVonAnderenAerzten;
                [Feld(Value = "8241", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 15)]
                public Fliesstext UeberweisungAn;
                [Feld(Value = "8147", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public Person Person;
                [Feld(Value = "7268", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string FachrichtungOderStationskennung;
                [Feld(Value = "8119", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 15)]
                public Betriebsstaette Betriebsstaette;
                [Feld(Value = "8143", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public Organisation Organisation;

            }
        }
    }
}
