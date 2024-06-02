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
            /// In diesem Objekt wird das Muster 39, Grundlage für die
            /// Krebsfrüherkennungsuntersuchung Zervix-Karzinom, abgebildet.
            /// </summary>
            [Objekt(Value = "0034", Kontextregeln = new[] { typeof(K128) })]
            public class KrebsfrueherkennungZervixKarzinom : Kontext
            {
                [Objekt]
                public class Gyn_OpStrahlenOderChemotherapieGenitale_ : Kontext
                {
                    public bool? Value;
                    [Feld(Value = "7337", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> Gyn_OpStrahlenOderChemotherapieGenitaleWelche;

                }
                [Objekt(Kontextregeln = new[] { typeof(K128) })]
                public class HpvHrTest_ : Kontext
                {
                    public bool? Value;
                    [Feld(Value = "3316", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public HpvHrTestergebnis? HpvHrTestergebnis;

                }
                [Feld(Value = "3322", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public Alterskategorie? Alterskategorie;
                [Feld(Value = "8630", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public Auftragsart? Auftragsart;
                [Feld(Value = "8629", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 6)]
                public Auftrag? Auftrag;
                [Feld(Value = "7296", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Wiederholungsuntersuchung;
                [Feld(Value = "7297", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public string DatumLetztenUntersuchung;
                [Feld(Value = "7414", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(E028) }, MaxLaenge = 5)]
                public string Gruppe;
                [Feld(Value = "7336", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Gyn_OpStrahlenOderChemotherapieGenitale_ Gyn_OpStrahlenOderChemotherapieGenitale;
                [Feld(Value = "7338", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public string Gyn_OpStrahlenOderChemotherapieGenitaleWann;
                [Feld(Value = "8512", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public string LetztePeriode;
                [Feld(Value = "7339", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Graviditaet;
                [Feld(Value = "7380", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? AusflussPath_Blutung;
                [Feld(Value = "7382", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Iup;
                [Feld(Value = "7383", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? EinnahmeVonOvulationshemmerSonstigeHormonAnwendung;
                [Feld(Value = "7384", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public KlinischerBefund? KlinischerBefund;
                [Feld(Value = "7423", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 990)]
                public string Erlaeuterungen;
                [Feld(Value = "3313", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public HpvImpfung? HpvImpfung;
                [Feld(Value = "3314", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public HpvHrTest_ HpvHrTest;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationenObjKrebsfrueherkennungZervixKarzinom;

            }
        }
    }
}
