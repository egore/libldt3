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
using libldt3.model.regel.format;
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
            [Objekt(Value = "0014", Kontextregeln = new[] { typeof(K020), typeof(K057), typeof(K115), typeof(K116) })]
            public class Arztidentifikation : Kontext
            {
                [Objekt]
                public class ArztIdEinesArztes_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "0308", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public IList<ArztTypId?> TypArztId;

                }
                [Feld(Value = "8147", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public Person Person;
                [Feld(Value = "0212", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F011) }, Laenge = 9)]
                public IList<string> LebenslangeArztnummer;
                [Feld(Value = "0223", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F022) }, Laenge = 9)]
                public IList<string> PseudoLanrFuerKrankenhausaerzteRahmenAsvAbrechnung;
                [Feld(Value = "0306", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string VertragsIdDesBehandelndenArztes;
                [Feld(Value = "0307", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<ArztIdEinesArztes_> ArztIdEinesArztes;
                [Feld(Value = "0222", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F014) }, Laenge = 9)]
                public string AsvTeamnummer;

            }
        }
    }
}
