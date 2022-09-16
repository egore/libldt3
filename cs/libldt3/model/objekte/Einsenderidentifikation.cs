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
using System.Collections.Generic;
using libldt3.attributes;
using libldt3.model.enums;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /**
             * Hier werden alle notwendigen Informationen zum Einsender zusammengefasst.
             */
            [Objekt(Value = "0022")]
            public class Einsenderidentifikation
            {

                [Feld(Value = "7321", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public IList<Einsenderstatus> status;
                [Feld(Value = "8312", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 20)]
                public string kundenNummer;
                [Feld(Value = "7267", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string auftraggeberId;
                [Feld(Value = "8114", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 18)]
                public Arztidentifikation arztidentifikation;
                [Feld(Value = "8240", Name = "Ueberweisung_von_anderen_Aerzten", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 32)]
                public Arztidentifikation ueberweisungVon;
                [Feld(Value = "8241", Name = "Ueberweisung_an", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 15)]
                public Arztidentifikation ueberweisungAn;
                [Feld(Value = "8147", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public Person person;
                [Feld(Value = "7268", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string fachrichtung;
                [Feld(Value = "8119", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 15)]
                public Betriebsstaette permanentEstablishment;
                [Feld(Value = "8143", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public Organisation organisation;

            }
        }
    }
}
