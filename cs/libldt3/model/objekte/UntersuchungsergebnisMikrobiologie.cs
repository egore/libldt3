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
            /// In diesem Objekt werden die Ergebnisse aus dem Bereich Mikrobiologie
            /// transportiert.
            /// </summary>
            /// Um diese Daten strukturiert zu übertragen wird eine in der
            /// Mikrobiologie übliche hierarchische Vorgehensweise definiert: Ausgangspunkt ist
            /// immer das Material und die dazugehörige Anforderung. Aus diesen Anforderungen
            /// erfolgt über verschiedene Nachweisverfahren eine Stufendiagnostik zur
            /// Keimbestimmung, optional die Bestimmung der Breakpunkte bzw.  MHK´s (Minimale
            /// Hemm Konzentration) für einzelne Antibiotika. Die Erregermenge wird als
            /// semiquantitatives Ergebnis abhängig des Untersuchungsmaterials dargestellt.
            [Objekt(Value = "0061", Kontextregeln = new[] { typeof(K010), typeof(K053), typeof(K076), typeof(K082), typeof(K085), typeof(K086), typeof(K096), typeof(K100) })]
            public class UntersuchungsergebnisMikrobiologie : Kontext
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

                }
                [Objekt]
                public class UntersuchungsergebnisMikrobiologie_Nachweisverfahren : Kontext
                {
                    public Nachweisverfahren? Value;
                    [Feld(Value = "7302", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Testmethode;

                }
                [Objekt(Kontextregeln = new[] { typeof(K100) })]
                public class KeimPilzIdentifizierung_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "7355", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 120)]
                    public string KeimPilzName;
                    [Feld(Value = "7427", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public KeimArt? ArtObjUntersuchungsergebnisMikrobiologie;
                    [Feld(Value = "7301", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public ErgebnisStatus? Ergebnis;
                    [Feld(Value = "7357", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public KeimPilzIdentifizierung_Wachstum Wachstum;
                    [Feld(Value = "7356", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string OidKeim;
                    [Feld(Value = "7285", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KeimNummer;
                    [Feld(Value = "7361", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public KeimIdKatalog KeimIdKatalog;
                    [Feld(Value = "8236", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 21)]
                    public Fliesstext TestbezogeneHinweise;
                    [Feld(Value = "8225", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 17)]
                    public Timestamp TimestampMessung;
                    [Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 12)]
                    public Fliesstext Ergebnistext;

                }
                [Objekt(Kontextregeln = new[] { typeof(K086) })]
                public class KeimPilzIdentifizierung_Wachstum : Kontext
                {
                    public Wachstum? Value;
                    [Feld(Value = "7293", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> EinheitMengenangabe;

                }
                [Objekt]
                public class KeimIdKatalog : Kontext
                {
                    public string Value;
                    [Feld(Value = "7251", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string BezeichnungDesVerwendetenKataloges;

                }
                [Objekt(Kontextregeln = new[] { typeof(K085) })]
                public class UntersuchungsergebnisMikrobiologie_ResistenzMethode : Kontext
                {
                    public ResistenzMethode? Value;
                    [Feld(Value = "8111", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 12)]
                    public Antibiogramm Antibiogramm;

                }
                [Feld(Value = "7304", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ErgebnisId;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> ProbengefaessIdent;
                [Feld(Value = "7260", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public IList<KatalogAnforderbareLeistungenId_> KatalogAnforderbareLeistungenId;
                [Feld(Value = "8410", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 20)]
                public IList<TestIdent_> TestIdent;
                [Feld(Value = "8434", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> Anforderung;
                [Feld(Value = "7281", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public IList<UntersuchungsergebnisMikrobiologie_Nachweisverfahren> Nachweisverfahren;
                [Feld(Value = "8418", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public TestStatus? Ergebnisstatus;
                [Feld(Value = "8244", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 3)]
                public IList<BAK> Bak;
                [Feld(Value = "7354", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<KeimPilzIdentifizierung_> KeimPilzIdentifizierung;
                [Feld(Value = "7286", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public IList<UntersuchungsergebnisMikrobiologie_ResistenzMethode> ResistenzMethode;
                [Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public Fliesstext Ergebnistext;
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
                public Timestamp TimestampQmErfassungObjUntersuchungsergebnisMikrobiologie;
                [Feld(Value = "8225", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 17)]
                public Timestamp TimestampMessung;
                [Feld(Value = "8126", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 28)]
                public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
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
