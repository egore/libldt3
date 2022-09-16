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
            /**
             * In diesem Objekt werden die Ergebnisse aus dem Bereich Mikrobiologie
             * transportiert. Um diese Daten strukturiert zu übertragen wird eine in der
             * Mikrobiologie übliche hierarchische Vorgehensweise definiert: Ausgangspunkt
             * ist immer das Material und die dazugehörige Anforderung. Aus diesen
             * Anforderungen erfolgt über verschiedene Nachweisverfahren eine
             * Stufendiagnostik zur Keimbestimmung, optional die Bestimmung der Breakpunkte
             * bzw. MHK´s (Minimale Hemm Konzentration) für einzelne Antibiotika. Die
             * Erregermenge wird als semiquantitatives Ergebnis abhängig des
             * Untersuchungsmaterials dargestellt.
             */
            [Objekt(Value = "0061", Kontextregeln = new[] { typeof(K073), typeof(K076) })]
            public class UntersuchungsergebnisMikrobiologie
            {

                [Objekt]
                public class Keim
                {
                    public string Value;
                    [Feld(Value = "7355", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string keimName;
                    [Feld(Value = "7301", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public ErgebnisStatus? ergebnisStatus;
                    [Feld(Value = "7357", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public Wachstum? wachstum;
                    [Feld(Value = "7293", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> einheit;
                    [Feld(Value = "7356", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string keimOid;
                    [Feld(Value = "7285", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string keimNummer;
                    [Feld(Value = "7361", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string katalogId;
                    [Feld(Value = "7251", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string katalogBezeichnung;
                    [Feld(Value = "8236", Name = "Testbezogene_Hinweise", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 21)]
                    public Fliesstext testbezogeneHinweise;
                    [Feld(Value = "8225", Name = "Timestamp_Messung", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 17)]
                    public Timestamp timestamp;
                    [Feld(Value = "8237", Name = "Ergebnistext", Feldart = Feldart.kann)]
                    [Regelsatz(Laenge = 12)]
                    public Fliesstext ergebnistext;
                }

                [Objekt]
                public class KatalogReferenz
                {
                    public KatalogIdAnforderbareLeistungen? Value;
                    [Feld(Value = "7352", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string katalogUrl;
                    [Feld(Value = "7251", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string katalogBezeichnung;
                    [Feld(Value = "7365", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 20)]
                    public string analysenId;
                    [Feld(Value = "7366", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string leistungsbezeichnung;
                }

                [Objekt]
                public class NachweisverfahrenErweitert
                {
                    public Nachweisverfahren? Value;
                    [Feld(Value = "7302", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string testmethode;
                }

                [Objekt]
                public class ResistenzMethodeErweitert
                {
                    public ResistenzMethode? Value;
                    [Feld(Value = "8111", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 12)]
                    public Antibiogramm antibiogramm;
                }

                [Feld(Value = "7304", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ergebnisId;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> probengefaessIdent;
                [Feld(Value = "7260", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public KatalogReferenz anforderbareLeistungenKatalogId;
                [Feld(Value = "8410", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string testIdent;
                [Feld(Value = "8411", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string testbezeichnung;
                [Feld(Value = "8434", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string anforderung;
                [Feld(Value = "7281", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public IList<NachweisverfahrenErweitert> nachweisverfahren;
                [Feld(Value = "8418", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public TestStatus? teststatus;
                [Feld(Value = "7354", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<Keim> keime;
                [Feld(Value = "7286", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public IList<ResistenzMethodeErweitert> resistenzMethode;
                [Feld(Value = "8142", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 10)]
                public IList<Normalwert> normalValue;
                [Feld(Value = "8237", Name = "Ergebnistext", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 12)]
                public Fliesstext ergebnistext;
                [Feld(Value = "8220", Name = "Timestamp_Eingangserfassung_Material", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 36)]
                public Timestamp materialDeliveryTimestamp;
                [Feld(Value = "8222", Name = "Timestamp_Beginn_Analytik", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 25)]
                public Timestamp startAnalyticsTimestamp;
                [Feld(Value = "8223", Name = "Timestamp_Ergebniserstellung", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 28)]
                public Timestamp resultTimestamp;
                [Feld(Value = "8224", Name = "Timestamp_QM_Erfassung", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 22)]
                public Timestamp qmTimestamp;
                [Feld(Value = "8225", Name = "Timestamp_Messung", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 17)]
                public Timestamp timestampMessung;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> zusaetzlicheInformationen;
                [Feld(Value = "8141", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung namenskennung;
                [Feld(Value = "8158", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 23)]
                public Untersuchungsabrechnung untersuchungsabrechnung;
                [Feld(Value = "8110", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> anhang;

            }
        }
    }
}