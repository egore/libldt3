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
            /// Dieses Objekt transportiert die Informationen zur Blutgruppenzugehörigkeit.
            /// </summary>
            [Objekt(Value = "0055", Kontextregeln = new[] { typeof(K071), typeof(K076), typeof(K078), typeof(K096) })]
            public class Blutgruppenzugehoerigkeit : Kontext
            {
                [Objekt]
                public class ErgebnisKreuzprobe : Kontext
                {
                    public string Value;
                    [Feld(Value = "7275", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> TerminologieId;

                }
                [Feld(Value = "7304", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ErgebnisId;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> ProbengefaessIdent;
                [Feld(Value = "8418", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public TestStatus? Ergebnisstatus;
                [Feld(Value = "3412", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public string BlutgruppeEurocode;
                [Feld(Value = "3413", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Antikoerpersuchtest? Antikoerpersuchtest;
                [Feld(Value = "3414", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string SpezifitaetWeitereErythrozytenantigene;
                [Feld(Value = "3415", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string SpezifitaetErythrozytenantikoerper;
                [Feld(Value = "3416", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string SpezifitaetHlaHpaHnaAntigene;
                [Feld(Value = "3417", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string SpezifitaetHlaHpaHnaAntikoerper;
                [Feld(Value = "7263", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string TestId;
                [Feld(Value = "3418", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public DirekterCoombstest? DirekterCoombstest;
                [Feld(Value = "3419", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<ErgebnisKreuzprobe> ErgebnisKreuzprobe;
                [Feld(Value = "3420", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public AnforderungNothilfepass? AnforderungNhp;
                [Feld(Value = "8220", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 36)]
                public Timestamp TimestampEingangserfassungMaterial;
                [Feld(Value = "8222", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 25)]
                public Timestamp TimestampBeginnAnalytik;
                [Feld(Value = "8223", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 28)]
                public Timestamp TimestampErgebniserstellung;
                [Feld(Value = "8224", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 22)]
                public Timestamp TimestampQmErfassung;
                [Feld(Value = "8225", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 17)]
                public Timestamp TimestampMessung;
                [Feld(Value = "8126", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 28)]
                public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "7429", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 990)]
                public string DrgHinweis;
                [Feld(Value = "3473", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? UntersuchungsergebnisDurchAuftragslaboratoriumErstellt;
                [Feld(Value = "8158", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 23)]
                public Untersuchungsabrechnung Untersuchungsabrechnung;

            }
        }
    }
}
