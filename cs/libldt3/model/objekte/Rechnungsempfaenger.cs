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
            /// Hier sind alle Angaben zum Rechnungsempfänger enthalten.
            /// </summary>
            [Objekt(Value = "0048", Name = "RgEmpfaenger", Kontextregeln = new[] { typeof(K029), typeof(K030), typeof(K093) })]
            public class Rechnungsempfaenger : Kontext
            {
                [Objekt]
                public class NameEinrichtungAuftraggeber : Kontext
                {
                    public string Value;
                    [Feld(Value = "7328", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 10)]
                    public string ZusaetzlicheNamenszeile;

                }
                [Feld(Value = "8310", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string AuftragsnummerEinsender;
                [Feld(Value = "7421", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public StatusRechnungsempfaenger? StatusRechnungsempfaenger;
                [Feld(Value = "0600", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public NameEinrichtungAuftraggeber NameEinrichtungAuftraggeber;
                [Feld(Value = "8108", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 8)]
                public Adressat Adressat;
                [Feld(Value = "8610", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Privattarif? Privattarif;
                [Feld(Value = "8608", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string KommentarAktenzeichen;

            }
        }
    }
}
