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
            [Objekt(Value = "0059", Kontextregeln = new[] { typeof(K003), typeof(K010), typeof(K011), typeof(K032), typeof(K034), typeof(K037), typeof(K053), typeof(K056), typeof(K057), typeof(K097), typeof(K098), typeof(K100), typeof(K102), typeof(K103), typeof(K105), typeof(K113), typeof(K114) })]
            public class Untersuchungsanforderung : Kontext
            {
                [Objekt(Kontextregeln = new[] { typeof(K053) })]
                public class KatalogAnforderbareLeistungenId : Kontext
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
                    [Feld(Value = "7366", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string LangbezeichnungAngefordertenLeistung;

                }
                [Objekt(Kontextregeln = new[] { typeof(K003) })]
                public class TestIdent : Kontext
                {
                    public string Value;
                    [Feld(Value = "8411", Feldart = Feldart.bedingt_muss)]
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
                    [Regelsatz(Value = new[] { typeof(E012) }, MaxLaenge = 4)]
                    public string ProbenmaterialIndex;

                }
                [Objekt]
                public class EinwilligungserklaerungDesPatientenLiegtVor : Kontext
                {
                    public bool? Value;
                    [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 6)]
                    public Anhang Anhang;

                }
                [Feld(Value = "7260", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public KatalogAnforderbareLeistungenId KatalogAnforderbareLeistungenId;
                [Feld(Value = "7276", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string VerwendeterNummernpoolId;
                [Feld(Value = "8410", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 20)]
                public TestIdent TestIdent;
                [Feld(Value = "7303", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 2)]
                public Abrechnungsinfo? AbrechnungsinfoZurUntersuchung;
                [Feld(Value = "8501", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Dringlichkeit? Dringlichkeit;
                [Feld(Value = "8423", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? PathologischBekannt;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<ProbengefaessIdent_> ProbengefaessIdent;
                [Feld(Value = "8434", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string Anforderungen;
                [Feld(Value = "8134", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 35)]
                public KrebsfrueherkennungZervixKarzinom KrebsfrueherkennungZervixKarzinom;
                [Feld(Value = "8156", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 5)]
                public Tumor Tumor;
                [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8238", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 25)]
                public Fliesstext AuftragsbezogeneHinweise;
                [Feld(Value = "8491", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public EinwilligungserklaerungDesPatientenLiegtVor EinwilligungserklaerungDesPatientenLiegtVor;
                [Feld(Value = "8213", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 45)]
                public Timestamp TimestampErstellung;
                [Feld(Value = "8141", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;

            }
        }
    }
}
