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
            /// Dieses Objekt wird als Zusammenfassung aller im Auftrag vorhandenen
            /// Abrechnungsarten genutzt.
            /// </summary>
            /// An Hand der hier gemachten Angaben kann bei der
            /// Implementierung eine Prüfroutine hinsichtlich der Vollständigkeit der
            /// darunterliegenden Objekte eingeführt werden. Pro Satzart "8215" darf dieses
            /// Objekt nur einmal vorhanden sein.
            [Objekt(Value = "0001")]
            public class Abrechnungsinformation : Kontext
            {
                [Feld(Value = "8102", Name = "Abrechnung_GKV", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 14)]
                public IList<AbrechnungGkv> AbrechnungGkv;
                [Feld(Value = "8103", Name = "Abrechnung_PKV", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 14)]
                public IList<AbrechnungPkv> AbrechnungPkv;
                [Feld(Value = "8104", Name = "Abrechnung_IGe-Leistungen", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge =     /* XXX 15 according to spec 3.0.3 */
            25)]
                public AbrechnungIgel AbrechnungIgel;
                [Feld(Value = "8105", Name = "Abrechnung_Sonstige_Kostenuebernahme", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 36)]
                public AbrechnungSonstigeKostenuebernahme AbrechnungSonstigeKostenuebernahme;
                [Feld(Value = "8106", Name = "Abrechnung_Selektivvertrag", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public AbrechnungSelektivvertrag AbrechnungSelektivvertrag;

            }
        }
    }
}
