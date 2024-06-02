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
            /// Dieses Objekt bündelt alle Daten zum Befund inklusive aller Kennungen, welche
            /// eine eineindeutige Zuordnung von Auftrag und Befund sicherstellen.
            /// </summary>
            [Objekt(Value = "0017", Kontextregeln = new[] { typeof(K112), typeof(K130), typeof(K131), typeof(K132), typeof(K135) })]
            public class Befundinformationen : Kontext
            {
                [Objekt]
                public class AuftragsnummerEinsender : Kontext
                {
                    public string Value;
                    [Feld(Value = "8313", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> NachforderungId;

                }
                [Objekt(Kontextregeln = new[] { typeof(K131), typeof(K132) })]
                public class RechtsgrundlageTestung : Kontext
                {
                    public TestungRechtsgrundlage? Value;
                    [Feld(Value = "8627", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MinLaenge = 1, MaxLaenge = 5)]
                    public string KvSonderziffer;
                    [Feld(Value = "8617", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public Beauftragungsgrund? Beauftragungsgrund;
                    [Feld(Value = "4111", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 9)]
                    public string Kostentraegerkennung;

                }
                [Objekt(Kontextregeln = new[] { typeof(K005), typeof(K096) })]
                public class AuftragsnummerLaborId : Kontext
                {
                    public string Value;
                    [Feld(Value = "7305", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string BefundId;
                    [Feld(Value = "8401", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 1)]
                    public Auftragsstatus? Status;

                }
                [Objekt]
                public class DerFallakteOderStudieId : Kontext
                {
                    public string Value;
                    [Feld(Value = "0081", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> BezeichnungFallakteOderStudie;

                }
                [Objekt]
                public class KatalogDurchgefuehrteLeistungenId : Kontext
                {
                    public string Value;
                    [Feld(Value = "7251", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string BezeichnungDesVerwendetenKataloges;

                }
                [Objekt]
                public class Befundinformationen_ZusaetzlicherBefundweg : Kontext
                {
                    public ZusaetzlicherBefundweg? Value;
                    [Feld(Value = "8147", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 6)]
                    public Person Person;

                }
                [Objekt]
                public class RecallEmpfohlen : Kontext
                {
                    public bool? Value;
                    [Feld(Value = "8154", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 9)]
                    public Timestamp Timestamp;

                }
                [Feld(Value = "8310", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public AuftragsnummerEinsender AuftragsnummerEinsender;
                [Feld(Value = "8214", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 27)]
                public Timestamp TimestampAuftragserteilung;
                [Feld(Value = "8215", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 25)]
                public Timestamp TimestampAuftragseingang;
                [Feld(Value = "8616", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Testungen? Testung;
                [Feld(Value = "8626", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public RechtsgrundlageTestung RechtsgrundlageTestung;
                [Feld(Value = "8631", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Bestaetigungsdiagnostik? Bestaetigungsdiagnostik;
                [Feld(Value = "8632", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public Virusvariantendiagnostik? Virusvariantendiagnostik;
                [Feld(Value = "8618", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public bool? BetreutUntergebrachtIn;
                [Feld(Value = "8619", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public bool? TaetigkeitInEinrichtung;
                [Feld(Value = "8620", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public BetroffeneEinrichtung? BetroffeneEinrichtung;
                [Feld(Value = "8622", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 43)]
                public string CoronaGuid;
                [Feld(Value = "8625", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 5)]
                public string PlzOeGd;
                [Feld(Value = "8623", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 22)]
                public string IdentifikationAktenzeichenOeGd;
                [Feld(Value = "8311", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public AuftragsnummerLaborId AuftragsnummerLaborId;
                [Feld(Value = "0080", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public DerFallakteOderStudieId DerFallakteOderStudieId;
                [Feld(Value = "7258", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public KatalogDurchgefuehrteLeistungenId KatalogDurchgefuehrteLeistungenId;
                [Feld(Value = "4229", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 5)]
                public IList<string> Knappschaftskennziffer;
                [Feld(Value = "8118", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 22)]
                public Kommunikationsdaten AbweichenderBefundweg;
                [Feld(Value = "8611", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public IList<Befundinformationen_ZusaetzlicherBefundweg> ZusaetzlicherBefundweg;
                [Feld(Value = "7320", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public RecallEmpfohlen RecallEmpfohlen;
                [Feld(Value = "8247", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 34)]
                public IList<Fliesstext> DiagnostischeBewertungEmpfehlung;
                [Feld(Value = "8216", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public Timestamp TimestampBefunderstellung;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;
                [Feld(Value = "8126", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 28)]
                public IList<FehlermeldungAufmerksamkeit> FehlermeldungAufmerksamkeit;
                [Feld(Value = "8141", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;

            }
        }
    }
}
