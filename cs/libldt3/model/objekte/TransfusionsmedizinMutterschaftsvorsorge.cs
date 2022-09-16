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
using System.Collections.Generic;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {

            [Objekt(Value = "0055", Kontextregeln = new[] { typeof(K076)})]
            public class TransfusionsmedizinMutterschaftsvorsorge
            {

                [Feld(Value = "7304", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ErgebnisId;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> ProbengefaessIdent;
                [Feld(Value = "8418", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public TestStatus? Teststatus;
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
                public IList<string> ErgebnisKreuzprobe;
                [Feld(Value = "7275", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> TerminologieId;
                [Feld(Value = "3420", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public AnforderungNothilfepass? AnforderungNothilfepass;
                [Feld(Value = "8220", Name = "Timestamp_Eingangserfassung_Material", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 36)]
                public Timestamp TimestampEingangserfassungMaterial;
                [Feld(Value = "8222", Name = "Timestamp_Beginn_Analytik", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 25)]
                public Timestamp TimestampBeginnAnalytik;
                [Feld(Value = "8223", Name = "Timestamp_Ergebniserstellung", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 28)]
                public Timestamp TimestampErgebniserstellung;
                [Feld(Value = "8224", Name = "Timestamp_QM_Erfassung", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 22)]
                public Timestamp TimestampQmErfassung;
                [Feld(Value = "8225", Name = "Timestamp_Messung", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 17)]
                public Timestamp TimestampMessung;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> zusaetzlicheInformationen;
                [Feld(Value = "8158", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 23)]
                public Untersuchungsabrechnung Untersuchungsabrechnung;

            }
        }
    }
}