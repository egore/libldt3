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
            /// Mikrobiologie übliche hierarchische Vorgehensweise definiert: Ausgangspunkt
            /// ist immer das Material und die dazugehörige Anforderung. Aus diesen
            /// Anforderungen erfolgt über verschiedene Nachweisverfahren eine
            /// Stufendiagnostik zur Keimbestimmung, optional die Bestimmung der Breakpunkte
            /// bzw. MHK´s (Minimale Hemm Konzentration) für einzelne Antibiotika. Die
            /// Erregermenge wird als semiquantitatives Ergebnis abhängig des
            /// Untersuchungsmaterials dargestellt.
            [Objekt(Value = "0061", Kontextregeln = new[] { typeof(K076) })]
            public class UntersuchungsergebnisMikrobiologie : Kontext
            {
                [Objekt]
                public class Keim : Kontext
                {
                    public string Value;
                    [Feld(Value = "7355", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KeimName;
                    [Feld(Value = "7301", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public Ergebnis? Ergebnis;
                    [Feld(Value = "7357", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public Wachstum? Wachstum;
                    [Feld(Value = "7293", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> Einheit;
                    [Feld(Value = "7356", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KeimOid;
                    [Feld(Value = "7285", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KeimNummer;
                    [Feld(Value = "7361", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KatalogId;
                    [Feld(Value = "7251", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KatalogBezeichnung;
                    [Feld(Value = "8236", Name = "Testbezogene_Hinweise", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 21)]
                    public Fliesstext TestbezogeneHinweise;
                    [Feld(Value = "8225", Name = "Timestamp_Messung", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 17)]
                    public Timestamp Timestamp;
                    [Feld(Value = "8237", Name = "Ergebnistext", Feldart = Feldart.kann)]
                    [Regelsatz(Laenge = 12)]
                    public Fliesstext Ergebnistext;

                }
                [Objekt]
                public class KatalogReferenz : Kontext
                {
                    public KatalogIdAnforderbareLeistungen? Value;
                    [Feld(Value = "7352", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KatalogUrl;
                    [Feld(Value = "7251", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string KatalogBezeichnung;
                    [Feld(Value = "7365", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 20)]
                    public string AnalysenId;
                    [Feld(Value = "7366", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Leistungsbezeichnung;

                }
                [Objekt]
                public class NachweisverfahrenErweitert : Kontext
                {
                    public Nachweisverfahren? Value;
                    [Feld(Value = "7302", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Testmethode;

                }
                [Objekt]
                public class ResistenzMethodeErweitert : Kontext
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
                public KatalogReferenz AnforderbareLeistungenKatalogId;
                [Feld(Value = "8410", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string TestIdent;
                [Feld(Value = "8411", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string Testbezeichnung;
                [Feld(Value = "8434", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string Anforderung;
                [Feld(Value = "7281", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public IList<NachweisverfahrenErweitert> Nachweisverfahren;
                [Feld(Value = "8418", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public TestStatus? Teststatus;
                [Feld(Value = "7354", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<Keim> Keime;
                [Feld(Value = "7286", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public IList<ResistenzMethodeErweitert> ResistenzMethode;
                [Feld(Value = "8142", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 10)]
                public IList<Normalwert> NormalValue;
                [Feld(Value = "8237", Name = "Ergebnistext", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 12)]
                public Fliesstext Ergebnistext;
                [Feld(Value = "8220", Name = "Timestamp_Eingangserfassung_Material", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 36)]
                public Timestamp MaterialDeliveryTimestamp;
                [Feld(Value = "8222", Name = "Timestamp_Beginn_Analytik", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 25)]
                public Timestamp StartAnalyticsTimestamp;
                [Feld(Value = "8223", Name = "Timestamp_Ergebniserstellung", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 28)]
                public Timestamp ResultTimestamp;
                [Feld(Value = "8224", Name = "Timestamp_QM_Erfassung", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 22)]
                public Timestamp QmTimestamp;
                [Feld(Value = "8225", Name = "Timestamp_Messung", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 17)]
                public Timestamp TimestampMessung;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8141", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;
                [Feld(Value = "8158", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 23)]
                public Untersuchungsabrechnung Untersuchungsabrechnung;
                [Feld(Value = "8110", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;

            }
        }
    }
}
