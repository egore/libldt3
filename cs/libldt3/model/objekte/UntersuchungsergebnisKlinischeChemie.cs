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
            /// In diesem Objekt werden die Ergebnisse aus dem Bereich Klinische Chemie
            /// übermittelt.
            /// </summary>
            [Objekt(Value = "0060", Kontextregeln = new[] { typeof(K053), typeof(K076), typeof(K082), typeof(K096), typeof(K106) })]
            public class UntersuchungsergebnisKlinischeChemie : Kontext
            {
                [Objekt(Kontextregeln = new[] { typeof(K053) })]
                public class KatalogAnforderbareLeistungenId_ : Kontext
                {
                    public KatalogIdAnforderbareLeistungen? Value;
                    [Feld(Value = "7352", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string UrlKataloge;
                    [Feld(Value = "7251", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string BezeichnungDesVerwendetenKataloges;
                    [Feld(Value = "7365", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 20)]
                    public AnalysenId AnalysenId;

                }
                [Objekt]
                public class AnalysenId : Kontext
                {
                    public string Value;
                    [Feld(Value = "7366", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string LangbezeichnungAngefordertenLeistung;

                }
                [Objekt]
                public class TestIdent_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "8411", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Testbezeichnung;
                    [Feld(Value = "7263", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string TestId;
                    [Feld(Value = "7264", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string TestGeraetUid;

                }
                [Objekt]
                public class Ergebnisstatus_ : Kontext
                {
                    public TestStatus? Value;
                    [Feld(Value = "7302", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> Testmethode;

                }
                [Objekt(Kontextregeln = new[] { typeof(K100) })]
                public class UntersuchungsergebnisKlinischeChemie_DarstellungErgebniswerte : Kontext
                {
                    public DarstellungErgebniswerte? Value;
                    [Feld(Value = "8420", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<ErgebnisWert> ErgebnisWert;
                    [Feld(Value = "8236", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 21)]
                    public Fliesstext TestbezogeneHinweise;

                }
                [Objekt(Kontextregeln = new[] { typeof(K002), typeof(K054), typeof(K076), typeof(K100) })]
                public class ErgebnisWert : Kontext
                {
                    public string Value;
                    [Feld(Value = "8419", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public EinheitensystemMesswerteWertes EinheitensystemMesswerteWertes;
                    [Feld(Value = "8142", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 10)]
                    public IList<Normalwert> Normalwert;
                    [Feld(Value = "8225", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 17)]
                    public Timestamp TimestampMessungObjUntersuchungsergebnisKlinischeChemie;
                    [Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 12)]
                    public Fliesstext Ergebnistext;

                }
                [Objekt(Kontextregeln = new[] { typeof(K002) })]
                public class EinheitensystemMesswerteWertes : Kontext
                {
                    public EinheitMesswert? Value;
                    [Feld(Value = "8421", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string MasseinheitMesswerteWertes;

                }
                [Feld(Value = "7304", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ErgebnisId;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> ProbengefaessIdent;
                [Feld(Value = "7260", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public KatalogAnforderbareLeistungenId_ KatalogAnforderbareLeistungenId;
                [Feld(Value = "8410", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 20)]
                public TestIdent_ TestIdent;
                [Feld(Value = "8418", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public Ergebnisstatus_ Ergebnisstatus;
                [Feld(Value = "7306", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 2)]
                public IList<UntersuchungsergebnisKlinischeChemie_DarstellungErgebniswerte> DarstellungErgebniswerte;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
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
                [Feld(Value = "8126", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 28)]
                public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;
                [Feld(Value = "8141", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;
                [Feld(Value = "8158", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 23)]
                public Untersuchungsabrechnung Untersuchungsabrechnung;
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
