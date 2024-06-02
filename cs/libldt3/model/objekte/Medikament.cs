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
using libldt3.model.regel.format;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Hier werden Informationen zu Medikamenten zusammengefasst.
            /// </summary>
            [Objekt(Value = "0070")]
            public class Medikament : Kontext
            {
                [Objekt]
                public class Rezeptur : Kontext
                {
                    public string Value;
                    [Feld(Value = "8171", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 9)]
                    public IList<Wirkstoff> Wirkstoff;
                    [Feld(Value = "6206", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Value = new[] { typeof(F020) }, Laenge = 8)]
                    public string Pharmazentralnummer;

                }
                [Objekt]
                public class WirkstoffmengeMengeBezugsmengeWirkstaerke : Kontext
                {
                    public float Value;
                    [Feld(Value = "8421", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 60)]
                    public string MasseinheitMesswerteWertes;

                }
                [Feld(Value = "8243", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 40)]
                public Timestamp TimestampZeitpunktMedikamenteneinnahme;
                [Feld(Value = "6208", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string HandelsnameArzneimittel;
                [Feld(Value = "6207", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 990)]
                public Rezeptur Rezeptur;
                [Feld(Value = "8523", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public WirkstoffmengeMengeBezugsmengeWirkstaerke WirkstoffmengeMengeBezugsmengeWirkstaerke;
                [Feld(Value = "3689", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public IList<MedikationsStatus?> StatusMedikation;
                [Feld(Value = "8226", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 20)]
                public Timestamp TimestampGueltigAb;
                [Feld(Value = "8227", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 21)]
                public Timestamp TimestampGueltigBis;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public Fliesstext ZusaetzlicheInformationen;

            }
        }
    }
}
