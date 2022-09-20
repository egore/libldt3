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
using NodaTime;
using libldt3.attributes;
using libldt3.model.regel;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// In diesem Objekt wird das Muster 39, Grundlage für die
            /// Krebsfrüherkennungsuntersuchung für Frauen, abgebildet.
            /// </summary>
            [Objekt(Value = "0034")]
            public class KrebsfrueherkennungFrauen
            {
                [Feld(Value = "7295", Feldart = Feldart.muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? UntersuchungsTag;
                [Feld(Value = "7296", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Wiederholungsuntersuchung;
                [Feld(Value = "7297", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public LocalDate? LetzteUntersuchung;
                [Feld(Value = "7298", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string NrLetzterZytologischerBefund;
                [Feld(Value = "7299", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 6)]
                public string GruppeLetzterBefund;
                [Feld(Value = "7336", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public string GynaekologischeOpStrahlenOderChemotherapie;
                [Feld(Value = "7337", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> ArtGynaekologischeOpStrahlenOderChemotherapie;
                [Feld(Value = "7338", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public LocalDate? DatumGynaekologischeOp;
                [Feld(Value = "3668", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 2)]
                public int? AnzahlSchwangerschaften;
                [Feld(Value = "8512", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public LocalDate? ErsterTagLetzterZyklus;
                [Feld(Value = "7339", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Graviditaet;
                [Feld(Value = "7380", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? PathologischeGynaekologischeBlutungen;
                [Feld(Value = "7381", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? SonstigerAusfluss;
                [Feld(Value = "7382", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Iup;
                [Feld(Value = "7383", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Ovulationshemmer;
                [Feld(Value = "7384", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? SonstigeHormonanwendung;
                [Feld(Value = "7385", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? VulvaInspektionAuffaellig;
                [Feld(Value = "7386", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? PortioUndVaginaAuffaellig;
                [Feld(Value = "7387", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? InnereGenitaleAuffaellig;
                [Feld(Value = "7388", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? InguinaleLymphknotenAuffaellig;
                [Feld(Value = "7389", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? BehandlungsbeduerftigeNebenbefunde;
                [Feld(Value = "7390", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Haut;
                [Feld(Value = "7391", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? MammaAuffaellig;
                [Feld(Value = "7392", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? AxillaereLymphknotenAuffaellig;
                [Feld(Value = "7393", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? RektumKolonBlutOderSchleim;
                [Feld(Value = "7394", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? NeuAufgetreteneUnregelmaessigkeiten;
                [Feld(Value = "7395", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? RektumKolonTastbefundAuffaellig;
                [Feld(Value = "7396", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? StuhltestZurueckgegeben;
                [Feld(Value = "7397", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? StuhltestPositiv;
                [Feld(Value = "7423", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 990)]
                public bool? GynaekologischeDiagnose;
                [Feld(Value = "7398", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F015) }, Laenge = 7)]
                public string RrBlutdruck;
                [Feld(Value = "7399", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F015) }, Laenge = 7)]
                public string RrZweiteMessung;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;

            }
        }
    }
}
