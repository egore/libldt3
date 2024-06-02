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
            /// In diesem Objekt werden die Ergebnisse bakteriologischer Untersuchungen
            /// strukturiert abgebildet.
            /// </summary>
            [Objekt(Value = "0072", Kontextregeln = new[] { typeof(K100) })]
            public class BAK : Kontext
            {
                [Objekt]
                public class BAK_DarstellungErgebniswerte : Kontext
                {
                    public DarstellungErgebniswerte? Value;
                    [Feld(Value = "8420", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<ErgebnisWert> ErgebnisWert;

                }
                [Objekt(Kontextregeln = new[] { typeof(K002), typeof(K054), typeof(K100) })]
                public class ErgebnisWert : Kontext
                {
                    public string Value;
                    [Feld(Value = "8419", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public EinheitensystemMesswerteWertes EinheitensystemMesswerteWertes;
                    [Feld(Value = "8142", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 10)]
                    public IList<Normalwert> Normalwert;
                    [Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 12)]
                    public Fliesstext Ergebnistext;

                }
                [Objekt(Kontextregeln = new[] { typeof(K002) })]
                public class EinheitensystemMesswerteWertes : Kontext
                {
                    public EinheitMesswert? Value;
                    [Feld(Value = "8421", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string MasseinheitMesswerteWertes;

                }
                [Feld(Value = "8245", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public Fliesstext BakErgebnis;
                [Feld(Value = "7306", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 2)]
                public IList<BAK_DarstellungErgebniswerte> DarstellungErgebniswerte;
                [Feld(Value = "8246", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 29)]
                public Fliesstext BakErgebniswertbezogeneHinweise;

            }
        }
    }
}
