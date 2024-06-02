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
using NodaTime;
using libldt3.attributes;
using libldt3.model;
using libldt3.model.regel.format;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// In diesem Objekt können Information zu einem Tumor sowohl für die Beauftragung
            /// und für den Befund transportiert werden.
            /// </summary>
            [Objekt(Value = "0056")]
            public class Tumor : Kontext
            {
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ProbengefaessIdent;
                [Feld(Value = "7372", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string Tumorklassifikation;
                [Feld(Value = "7373", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 5)]
                public string Grading;
                [Feld(Value = "7374", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 5)]
                public string Stadium;
                [Feld(Value = "7375", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F017) }, Laenge = 4)]
                public string JahrTumordiagnose;
                [Feld(Value = "7376", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string Lokalisation;
                [Feld(Value = "7377", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> Masse;
                [Feld(Value = "7378", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string Farbe;
                [Feld(Value = "7379", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string Infiltrationstiefe;
                [Feld(Value = "3424", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? Therapiebeginn;
                [Feld(Value = "3425", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? Therapieende;
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
                [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;

            }
        }
    }
}
