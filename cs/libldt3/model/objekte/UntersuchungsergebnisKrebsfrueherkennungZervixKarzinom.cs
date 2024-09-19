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
            /// In diesem Objekt werden die Ergebnisse der Krebsfrüherkennung Zervix-Karzinom
            /// übertragen.
            /// </summary>
            /// Die Inhalte richten sich nach dem Muster 39a/b. Zervix-Karzinom
            [Objekt(Value = "0062", Kontextregeln = new[] { typeof(K076), typeof(K082), typeof(K096), typeof(K100), typeof(K122), typeof(K123), typeof(K124), typeof(K125), typeof(K126), typeof(K134) })]
            public class UntersuchungsergebnisKrebsfrueherkennungZervixKarzinom : Kontext
            {
                [Objekt(Kontextregeln = new[] { typeof(K100) })]
                public class TestIdent : Kontext
                {
                    public string Value;
                    [Feld(Value = "8411", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string Testbezeichnung;
                    [Feld(Value = "8422", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 2)]
                    public IList<GrenzwertindikatorLaborwerte> GrenzwertindikatorLaborwerte;
                    [Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 12)]
                    public Fliesstext Ergebnistext;

                }
                [Objekt(Kontextregeln = new[] { typeof(K099) })]
                public class GrenzwertindikatorLaborwerte : Kontext
                {
                    public Grenzwertindikator? Value;
                    [Feld(Value = "8126", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 28)]
                    public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;

                }
                [Objekt]
                public class Gruppe : Kontext
                {
                    public string Value;
                    [Feld(Value = "7413", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Value = new[] { typeof(E028) }, MaxLaenge = 4)]
                    public string CodierungGruppe;

                }
                [Objekt(Kontextregeln = new[] { typeof(K122) })]
                public class HpvHrTestergebnisObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom : Kontext
                {
                    public HpvHrTestergebnis? Value;
                    [Feld(Value = "3317", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public HpvTyp1618? HpvTyp1618;

                }
                [Objekt]
                public class ZytologischeKontrolle : Kontext
                {
                    public bool? Value;
                    [Feld(Value = "7416", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public IList<NachkontrollGrund?> GrundNachkontrolle;

                }
                [Feld(Value = "7304", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string ErgebnisId;
                [Feld(Value = "7364", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> ProbengefaessIdent;
                [Feld(Value = "8410", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 20)]
                public TestIdent TestIdent;
                [Feld(Value = "8418", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public TestStatus? Ergebnisstatus;
                [Feld(Value = "7405", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public EndozervikaleZellen? EndozervikaleZellen;
                [Feld(Value = "7406", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 10)]
                public string Proliferationsgrad;
                [Feld(Value = "7407", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public bool? Doederleinflora;
                [Feld(Value = "7408", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public bool? Mischflora;
                [Feld(Value = "7409", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public bool? Kokkenflora;
                [Feld(Value = "7410", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public bool? Trichomonaden;
                [Feld(Value = "7411", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public bool? Candida;
                [Feld(Value = "7412", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public bool? Gardnerella;
                [Feld(Value = "7414", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(E028) }, MaxLaenge = 5)]
                public Gruppe Gruppe;
                [Feld(Value = "3316", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public HpvHrTestergebnisObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom HpvHrTestergebnisObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom;
                [Feld(Value = "7415", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public ZytologischeKontrolle ZytologischeKontrolle;
                [Feld(Value = "7417", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Abklaerungskolposkopie;
                [Feld(Value = "3318", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? HpvTest;
                [Feld(Value = "3319", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? KoTest;
                [Feld(Value = "3320", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? ZeitraumSofort;
                [Feld(Value = "3321", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F023) }, MinLaenge = 1, MaxLaenge = 5)]
                public string ZeitraumInMonaten;
                [Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public Fliesstext Ergebnistext;
                [Feld(Value = "8134", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 35)]
                public KrebsfrueherkennungZervixKarzinom KrebsfrueherkennungZervixKarzinom;
                [Feld(Value = "8126", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 28)]
                public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;
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
                public Timestamp TimestampMessungObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;
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

            }
        }
    }
}
