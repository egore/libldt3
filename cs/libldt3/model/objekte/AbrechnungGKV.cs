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
using NodaTime;
using libldt3.attributes;
using libldt3.model;
using libldt3.model.enums;
using libldt3.model.regel;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// <p>
            /// Hier werden alle Angaben für die Abrechnung von Untersuchungsanforderungen in
            /// der GKV gegenüber der KV hinterlegt.
            /// </summary>
            /// Der Patient ist in der gesetzlichen
            /// Kranken-versicherung pflichtversichert oder freiwillig versichert. Der
            /// Auftrag für die geplanten Untersuchungen erfolgt über Muster 10/Muster
            /// 10A/Muster 39.
            /// </p>
            /// <p>
            /// Mit diesem Objekt werden die Informationen für die Abrechnung von
            /// Untersuchungsanforderungen zusammengefasst, die im Regelleistungskatalog der
            /// Krankenkassen vorhanden sind oder anderweitig z.B. über eDMP dem Patienten
            /// zugeordnet werden können.
            /// </p>
            [Objekt(Value = "0002", Kontextregeln = new[] { typeof(K041) })]
            public class AbrechnungGKV : Kontext
            {
                [Feld(Value = "4239", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public Scheinuntergruppe? Scheinuntergruppe;
                [Feld(Value = "4134", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 28)]
                public string Kostentraegername;
                [Feld(Value = "4104", Feldart = Feldart.muss)]
                [Regelsatz(Value = new[] { typeof(F001) }, Laenge = 5)]
                public string AbrechnungsVknr;
                [Feld(Value = "4106", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public KostentraegerAbrechnungsbereich? KostentraegerAbrechnungsbereich;
                [Feld(Value = "4108", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string Zulassungsnummer;
                [Feld(Value = "3116", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 2)]
                public WOP? Wop;
                [Feld(Value = "3108", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Versichertenart? Versichertenart;
                [Feld(Value = "4109", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? LetzterEinlesetagVersichertenkarteImQuartal;
                [Feld(Value = "4133", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? VersicherungsschutzBeginn;
                [Feld(Value = "4110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? VersicherungsschutzEnde;
                [Feld(Value = "4111", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 9)]
                public string Kstentraegerkennung;
                [Feld(Value = "4229", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 5)]
                public IList<string> Ausnahmeindikation;
                [Feld(Value = "4122", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public string Abrechnungsgebiet;
                [Feld(Value = "4124", Feldart = Feldart.kann)]
                [Regelsatz(MinLaenge = 5, MaxLaenge = 60)]
                public string SktZusatzangaben;
                [Feld(Value = "4126", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> SktZusatzbemerkungen;
                [Feld(Value = "4131", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 2)]
                public BesonderePersonengruppe? BesonderePersonengruppe;
                [Feld(Value = "4132", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 2)]
                public DmpKennzeichnung? DmpKennzeichnung;
                [Feld(Value = "4202", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Unfallfolgen;
                [Feld(Value = "4204", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? EingeschraenkterLeistungsanspruch;
                [Feld(Value = "4221", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Behandlungsanlass? KurativPraeventivEss;
                [Feld(Value = "4231", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public string KontrolluntersuchungBekannterInfektion;
                [Feld(Value = "4241", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F011), typeof(F022) }, Laenge = 9)]
                public string LebenslangeArztnummer;
                [Feld(Value = "4248", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 9)]
                public string PseudoLanr;
                [Feld(Value = "4217", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F010) }, Laenge = 9)]
                public string BsnrErstveranlasser;
                [Feld(Value = "4225", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 9)]
                public string AsvTeamnummer;

            }
        }
    }
}
