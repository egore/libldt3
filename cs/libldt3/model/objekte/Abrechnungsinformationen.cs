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
            /// Dieses Objekt wird als Zusammenfassung aller im Auftrag vorhandenen
            /// Abrechnungsarten genutzt.
            /// </summary>
            /// An Hand der hier gemachten Angaben kann bei der
            /// Implementierung eine Prüfroutine hinsichtlich der Vollständigkeit der
            /// darunterliegenden Objekte eingeführt werden. Pro Satzart "8215" darf dieses
            /// Objekt nur einmal vorhanden sein.
            [Objekt(Value = "0001", Kontextregeln = new[] { typeof(K027), typeof(K070) })]
            public class Abrechnungsinformationen : Kontext
            {
                [Feld(Value = "8102", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 14)]
                public IList<AbrechnungGKV> AbrechnungGkv;
                [Feld(Value = "8103", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 14)]
                public IList<AbrechnungPKV> AbrechnungPkv;
                [Feld(Value = "8104", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 15)]
                public AbrechnungIgeLeistungen AbrechnungIgeLeistungen;
                [Feld(Value = "8105", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 36)]
                public AbrechnungSonstigeKostenuebernahme AbrechnungSonstigeKostenuebernahme;
                [Feld(Value = "8106", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public AbrechnungSelektivvertrag AbrechnungSelektivvertrag;
                [Feld(Value = "8109", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 16)]
                public AbrechnungOEGD AbrechnungOegd;

            }
        }
    }
}
