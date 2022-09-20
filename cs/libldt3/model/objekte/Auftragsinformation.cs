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

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// In diesem Objekt werden die Informationen zur Zuordnung im sendenden System
            /// zum Auftrag zusammengefasst sowie zusätzliche Befundwege und die
            /// Dringlichkeit des Auftrags definiert.
            /// </summary>
            [Objekt(Value = "0013")]
            public class Auftragsinformation
            {
                [Objekt]
                public class Befundweg
                {
                    public ZusaetzlicherBefundweg? Value;
                    [Feld(Value = "8147", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 6)]
                    public Person Person;

                }
                [Feld(Value = "8310", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string AuftragsnummerEinsender;
                [Feld(Value = "8313", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> NachforderungId;
                [Feld(Value = "8311", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string AuftragsnummerLabor;
                [Feld(Value = "7268", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string FachrichtungStationskennung;
                [Feld(Value = "0080", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string FallakteId;
                [Feld(Value = "0081", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> FallakteBezeichnung;
                [Feld(Value = "8501", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public Dringlichkeit? Dringlichkeit;
                [Feld(Value = "7262", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public StatusDringlichkeit? StatusDringlichkeit;
                [Feld(Value = "8118", Name = "Abweichender_Befundweg", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 22)]
                public Kommunikationsdaten AbweichenderBefundweg;
                [Feld(Value = "8611", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public IList<Befundweg> ZusaetzlicherBefundweg;
                [Feld(Value = "8213", Name = "Timestamp_Erstellung_Untersuchungsanforderung", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 45)]
                public Timestamp TimestampErstellungUntersuchungsanforderung;
                [Feld(Value = "8238", Name = "Auftragsbezogene_Hinweise", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 25)]
                public Fliesstext AuftragsbezogeneHinweise;
                [Feld(Value = "8141", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;

            }
        }
    }
}
