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
using libldt3.model.enums;
using libldt3.model.objekte;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace saetze
        {
            /// <summary>
            /// Befund "8205"
            /// </summary>
            [Datenpaket(Value = Satzart.Befund, Kontextregeln = new[] { typeof(K005) })]
            public class Befund : Satz
            {
                [Feld(Value = "8136", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 12)]
                public IList<Laborkennung> Laborkennung;
                [Feld(Value = "8122", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 23)]
                public Einsenderidentifikation SenderIdentification;
                [Feld(Value = "8145", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 7)]
                public Patient Patient;
                [Feld(Value = "8169", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 19)]
                public Koerperkenngroessen Koerperkenngroessen;
                [Feld(Value = "8150", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 15)]
                public Schwangerschaft Schwangerschaft;
                [Feld(Value = "8140", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 12)]
                public Mutterschaft Mutterschaft;
                [Feld(Value = "8153", Name = "Tier_Sonstiges", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 14)]
                public Tier Tier;
                [Feld(Value = "8117", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 19)]
                public Befundinformationen Befundinformationen;
                [Feld(Value = "8127", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 18)]
                public IList<Veranlassungsgrund> Veranlassungsgrund;
                [Feld(Value = "8137", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 8)]
                public IList<Material> Material;
                [Feld(Value = "8135", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 20)]
                public Laborergebnisbericht Laborergebnisbericht;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> Text;
                [Feld(Value = "8110", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;

            }
        }
    }
}
