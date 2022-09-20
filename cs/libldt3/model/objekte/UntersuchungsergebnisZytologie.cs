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
using libldt3.model.enums;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// In diesem Objekt können weitere Ergebnisse aus dem Bereich Zytologie transportiert werden.
            /// </summary>
            [Objekt(Value = "0063", Kontextregeln = new[] { typeof(K076) })]
            public class UntersuchungsergebnisZytologie
            {
                [Objekt]
                public class RecallEmpfohlen_
                {
                    public string Value;
                    [Feld(Value = "8154", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 9)]
                    public Timestamp Timestamp;

                }
                [Objekt]
                public class KatalogReferenz
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
                public class GrenzwertindikatorLaborwert
                {
                    public Grenzwertindikator? Value;
                    [Feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 28)]
                    public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;

                }
                [Objekt]
                public class Test
                {
                    public string Value;
                    [Feld(Value = "8411", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Testbezeichnung;
                    [Feld(Value = "8418", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public TestStatus? Teststatus;
                    [Feld(Value = "8422", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 2)]
                    public IList<GrenzwertindikatorLaborwert> GrenzwertindikatorLaborwert;

                }
                [Feld(Value = "7304", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ErgebnisId;
                [Feld(Value = "7320", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public IList<RecallEmpfohlen_> RecallEmpfohlen;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> ProbengefaessIdent;
                [Feld(Value = "7260", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public KatalogReferenz KatalogIdAnforderbareLeistungen;
                [Feld(Value = "8410", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<Test> TestIdent;
                [Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge =     /* XXX 12 according to spec */
            10)]
                public Fliesstext Ergebnistext;
                [Feld(Value = "7368", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? ZellmaterialNichtVerwertbar;
                [Feld(Value = "7400", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public string HpvBefund;
                [Feld(Value = "7401", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Ergebnis? HighRisk;
                [Feld(Value = "7402", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 2)]
                public IList<string> HighRiskTyp;
                [Feld(Value = "7403", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Ergebnis? LowRisk;
                [Feld(Value = "7404", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 2)]
                public IList<string> LowRiskTyp;
                [Feld(Value = "7418", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Ergebnis? P16Ki67;
                [Feld(Value = "7419", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Ergebnis? L1;
                [Feld(Value = "7422", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Ergebnis? Chlamydien;
                [Feld(Value = "7425", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Ergebnis2? ExtragynaekologischeZytologie;
                [Feld(Value = "7426", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Ergebnis? NeisseriaGonorrhoeae;
                [Feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 28)]
                public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;
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
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8110", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;
                [Feld(Value = "8141", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;
                [Feld(Value = "8158", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 23)]
                public Untersuchungsabrechnung Untersuchungsabrechnung;

            }
        }
    }
}
