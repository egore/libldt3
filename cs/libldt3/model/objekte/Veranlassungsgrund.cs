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
            /// Mit diesem Objekt können Angaben zum Grund der Veranlassung der
            /// laboratoriumsmedizinischen Untersuchung übertragen werden.
            /// </summary>
            [Objekt(Value = "0027", Kontextregeln = new[] { typeof(K032), typeof(K034), typeof(K060) })]
            public class Veranlassungsgrund : Kontext
            {
                [Objekt(Kontextregeln = new[] { typeof(K133) })]
                public class AbrechnungsinfoZurUntersuchung : Kontext
                {
                    public Abrechnungsinfo? Value;
                    [Feld(Value = "8417", Feldart = Feldart.kann)]
                    [Regelsatz(Laenge = 2)]
                    public AnlassUntersuchung AnlassUntersuchung;
                    [Feld(Value = "8200", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 12)]
                    public IList<Diagnose> Akutdiagnose;
                    [Feld(Value = "4209", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> ZusaetzlicheAngabenZuUntersuchungen;
                    [Feld(Value = "4208", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<VorbefundMedikation> VorbefundMedikation;

                }
                [Objekt]
                public class AnlassUntersuchung : Kontext
                {
                    public Untersuchungsanlass? Value;
                    [Feld(Value = "8427", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 2)]
                    public SpezifizierungVeranlassungsgrunde SpezifizierungVeranlassungsgrunde;

                }
                [Objekt(Kontextregeln = new[] { typeof(K100) })]
                public class SpezifizierungVeranlassungsgrunde : Kontext
                {
                    public SpezifizierungVeranlassungsgrund? Value;
                    [Feld(Value = "8217", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 32)]
                    public Fliesstext Praezisierung;

                }
                [Objekt]
                public class VorbefundMedikation : Kontext
                {
                    public string Value;
                    [Feld(Value = "8170", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 10)]
                    public IList<Medikament> Medikament;

                }
                [Feld(Value = "7303", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 2)]
                public IList<AbrechnungsinfoZurUntersuchung> AbrechnungsinfoZurUntersuchung;
                [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;

            }
        }
    }
}
