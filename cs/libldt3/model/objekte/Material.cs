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
            /// Im Objekt werden die Informationen zur Identifikation des zu untersuchenden
            /// Materials übermittelt sowie Angaben zum Material selbst.
            /// </summary>
            [Objekt(Value = "0037", Kontextregeln = new[] { typeof(K006), typeof(K038), typeof(K039), typeof(K063), typeof(K082) })]
            public class Material : Kontext
            {
                [Objekt(Kontextregeln = new[] { typeof(K038) })]
                public class ArtMaterial_ : Kontext
                {
                    public Materialart? Value;
                    [Feld(Value = "7311", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public OrganischesMaterial? Organisches;
                    [Feld(Value = "7312", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public Anorganisches Anorganisches;

                }
                [Objekt]
                public class Anorganisches : Kontext
                {
                    public AnorganischesMaterial? Value;
                    [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 26)]
                    public Fliesstext ZusaetzlicheInformationen;

                }
                [Objekt]
                public class MedikamenteneinnahmeZumZeitpunktMaterialentnahme_ : Kontext
                {
                    public bool? Value;
                    [Feld(Value = "8170", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 10)]
                    public Medikament Medikament;

                }
                [Objekt]
                public class MengeProbenmaterial_ : Kontext
                {
                    public float Value;
                    [Feld(Value = "8421", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string MasseinheitMesswerteWertes;
                    [Feld(Value = "8522", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Value = new[] { typeof(F006) }, Laenge = 4)]
                    public string SammelzeitProbenmaterial;

                }
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ProbengefaessIdent;
                [Feld(Value = "8429", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(E012) }, MaxLaenge = 4)]
                public string ProbenmaterialIndex;
                [Feld(Value = "8428", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string ProbenmaterialIdent;
                [Feld(Value = "8430", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string ProbenmaterialBezeichnung;
                [Feld(Value = "8431", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string ProbenmaterialSpezifikation;
                [Feld(Value = "7292", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string LokalisationProbenmaterial;
                [Feld(Value = "7310", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public ArtMaterial_ ArtMaterial;
                [Feld(Value = "8504", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public IList<MedikamenteneinnahmeZumZeitpunktMaterialentnahme_> MedikamenteneinnahmeZumZeitpunktMaterialentnahme;
                [Feld(Value = "7318", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> NahrungsaufnahmeZumZeitpunktMaterialentnahme;
                [Feld(Value = "8520", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public MengeProbenmaterial_ MengeProbenmaterial;
                [Feld(Value = "8219", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 34)]
                public Timestamp TimestampMaterialabnahmeEntnahme;
                [Feld(Value = "8220", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 36)]
                public Timestamp TimestampEingangserfassung;
                [Feld(Value = "8126", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 28)]
                public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;

            }
        }
    }
}
