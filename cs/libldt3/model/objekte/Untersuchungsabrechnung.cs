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
using libldt3.model.regel.erlaubt;
using libldt3.model.regel.format;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Jeder Untersuchung wird direkt eine Abrechnung zugeordnet.
            /// </summary>
            /// Hier werden alle
            /// Werte transportiert, die für die ordnungsgemäße Abrechnung des Auftrages
            /// notwendig sind.
            [Objekt(Value = "0058", Kontextregeln = new[] { typeof(K005), typeof(K008), typeof(K019) })]
            public class Untersuchungsabrechnung : Kontext
            {
                [Objekt(Kontextregeln = new[] { typeof(K019) })]
                public class Untersuchungsabrechnung_Gebuehrenordnung : Kontext
                {
                    public Gebuehrenordnung? Value;
                    [Feld(Value = "5001", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Value = new[] { typeof(F009) }, MinLaenge = 5, MaxLaenge = 6)]
                    public IList<Gebuehrennummer_> Gebuehrennummer;

                }
                [Objekt]
                public class Gebuehrennummer_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "8406", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KostenInEURCent;
                    [Feld(Value = "5005", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Value = new[] { typeof(E003) }, Laenge = 3)]
                    public string Multiplikator;
                    [Feld(Value = "5009", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> FreierBegruendungstext;
                    [Feld(Value = "8614", Feldart = Feldart.muss)]
                    [Regelsatz(Laenge = 1)]
                    public bool? BereitsAbgerechnet;

                }
                [Objekt]
                public class KatalogAbrechenbareLeistungenId_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "7251", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string BezeichnungDesVerwendetenKataloges;

                }
                [Feld(Value = "7303", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 2)]
                public Abrechnungsinfo? AbrechnungsinfoZurUntersuchung;
                [Feld(Value = "4121", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Untersuchungsabrechnung_Gebuehrenordnung Gebuehrenordnung;
                [Feld(Value = "7259", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public KatalogAbrechenbareLeistungenId_ KatalogAbrechenbareLeistungenId;

            }
        }
    }
}
