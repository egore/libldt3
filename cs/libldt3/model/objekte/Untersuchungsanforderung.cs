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
            /// In diesem Objekt werden alle Informationen zur Untersuchungsanforderung
            /// zusammengefasst.
            /// </summary>
            [Objekt(Value = "0059", Kontextregeln = new[] { typeof(K003) })]
            public class Untersuchungsanforderung : Kontext
            {
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
                    [Feld(Value = "7366", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Leistungsbezeichnung;

                }
                [Objekt]
                public class Test : Kontext
                {
                    public string Value;
                    [Feld(Value = "8411", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Testbezeichnung;

                }
                [Objekt]
                public class ProbengefaessIdent_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "8428", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string ProbenmaterialIdent;
                    [Feld(Value = "8429", Feldart = Feldart.kann)]
                    [Regelsatz(MaxLaenge = 4)]
                    public string ProbenmaterialIndex;

                }
                [Objekt]
                public class Einwilligungserklaerung : Kontext
                {
                    public bool? Value;
                    [Feld(Value = "8110", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 6)]
                    public Anhang Anhang;

                }
                [Feld(Value = "7260", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public KatalogReferenz AnforderbareLeistungenKatalogId;
                [Feld(Value = "7276", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string NummernpoolId;
                [Feld(Value = "8410", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public Test TestIdent;
                [Feld(Value = "7303", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 2)]
                public Abrechnungsinfo? Abrechnungsinfo;
                [Feld(Value = "8501", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Dringlichkeit? Dringlichkeit;
                [Feld(Value = "7262", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public StatusDringlichkeit? StatusDringlichkeit;
                [Feld(Value = "8423", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? PathologischBekannt;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<ProbengefaessIdent_> ProbengefaessIdent;
                [Feld(Value = "8434", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string Anforderungen;
                [Feld(Value = "8134", Name = "Krebsfrueherkennung_Frauen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public KrebsfrueherkennungFrauen KrebsfrueherkennungFrauen;
                [Feld(Value = "8156", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 5)]
                public Tumor Tumor;
                [Feld(Value = "8110", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8238", Name = "Auftragsbezogene_Hinweise", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 25)]
                public Fliesstext AuftragsbezogeneHinweise;
                [Feld(Value = "8491", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Einwilligungserklaerung EinwilligungserklaerungLiegtVor;
                [Feld(Value = "8213", Name = "Timestamp_Erstellung_Untersuchungsanforderung", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 45)]
                public Timestamp TimestampErstellungUntersuchungsanforderung;
                [Feld(Value = "8141", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;

            }
        }
    }
}
