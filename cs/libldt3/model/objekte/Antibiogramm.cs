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
            /// In diesem Objekt wird ein Antibiogramm (Matrix) aus dem Bereich Mikrobiologie
            /// transportiert.
            /// </summary>
            /// Die Darstellung des Antibiogramms erfolgt als dreidimensionale
            /// Matrix. Um den redundanten Informationsgehalt so gering wie möglich zu halten
            /// wird folgende Matrix- Struktur zugrunde gelegt: Matrix K1 K2 K3 … Kx W1
            /// Sensitivität MHK Breakpoint MHK Einheit Resistenz Interpretation Sensitivität
            /// MHK Breakpoint MHK Einheit Resistenz Interpretation Sensitivität MHK Breakpoint
            /// MHK Einheit Resistenz Interpretation Sensitivität MHK Breakpoint MHK Einheit
            /// Resistenz Interpretation W2 Sensitivität MHK Breakpoint MHK Einheit Resistenz
            /// Interpretation Sensitivität MHK Breakpoint MHK Einheit Resistenz Interpretation
            /// Sensitivität MHK Breakpoint MHK Einheit Resistenz Interpretation Sensitivität
            /// MHK Breakpoint MHK Einheit Resistenz Interpretation W3 Sensitivität MHK
            /// Breakpoint MHK Einheit Resistenz Interpretation Sensitivität MHK Breakpoint MHK
            /// Einheit Resistenz Interpretation Sensitivität MHK Breakpoint MHK Einheit
            /// Resistenz Interpretation Sensitivität MHK Breakpoint MHK Einheit Resistenz
            /// Interpretation …… Wy Sensitivität MHK Breakpoint MHK Einheit Resistenz
            /// Interpretation Sensitivität MHK Breakpoint MHK Einheit Resistenz Interpretation
            /// Sensitivität MHK Breakpoint MHK Einheit Resistenz Interpretation Sensitivität
            /// MHK Breakpoint MHK Einheit Resistenz Interpretation Kx = Keim-Identifizierung (x
            /// = max. Anzahl der Keime) Wy = Wertepaar aus Wirkstoffident und Ableitungen (y =
            /// max. Anzahl der getesteten Wirkstoffe) IT in der Arztpraxis LDT 3.0 LDT 3
            /// Satzbeschreibung, Version 3.2.16
            [Objekt(Value = "0011", Kontextregeln = new[] { typeof(K100) })]
            public class Antibiogramm : Kontext
            {
                [Objekt]
                public class WirkstoffIdent_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "7288", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> WirkstoffGenericNummer;
                    [Feld(Value = "7359", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> OidWirkstoff;
                    [Feld(Value = "7370", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> WirkstoffoderHandelsname;
                    [Feld(Value = "7354", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<KeimIdentifizierung> KeimIdentifizierung;

                }
                [Objekt]
                public class KeimIdentifizierung : Kontext
                {
                    public string Value;
                    [Feld(Value = "7367", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public Sensitivitaet? Sensitivitaet;
                    [Feld(Value = "7289", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public MhkBreakpointWert MhkBreakpointWert;
                    [Feld(Value = "7290", Feldart = Feldart.kann)]
                    [Regelsatz(Laenge = 1)]
                    public IList<KeimIdentifizierung_ResistenzInterpretation> ResistenzInterpretation;

                }
                [Objekt]
                public class MhkBreakpointWert : Kontext
                {
                    public string Value;
                    [Feld(Value = "7369", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string MhkEinheit;

                }
                [Objekt]
                public class KeimIdentifizierung_ResistenzInterpretation : Kontext
                {
                    public ResistenzInterpretation? Value;
                    [Feld(Value = "7424", Feldart = Feldart.kann)]
                    [Regelsatz(Laenge = 1)]
                    public ResistenzNach? ResistenzErstelltNach;

                }
                [Feld(Value = "7287", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<WirkstoffIdent_> WirkstoffIdent;
                [Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public Fliesstext Ergebnistext;

            }
        }
    }
}
