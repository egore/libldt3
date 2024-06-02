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
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Im Objekt werden die Untersuchungsergebnisse zusammengefasst.
            /// </summary>
            /// Hinweis: Die
            /// Feldkennungen 8160, 8161, 8162, 8163, 8155, 8248 und 8156 können im Obj_0035 in
            /// beliebiger Reihenfolge angeordnet und übertragen werden. Damit wird es möglich,
            /// im Obj_0035 die Struktur eines schriftlichen Befundes nachzubilden.
            [Objekt(Value = "0035", Kontextregeln = new[] { typeof(K009) })]
            public class Laborergebnisbericht : Kontext
            {
                [Feld(Value = "8160", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 19)]
                public IList<UntersuchungsergebnisKlinischeChemie> UeKlinischeChemie;
                [Feld(Value = "8161", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 16)]
                public IList<UntersuchungsergebnisMikrobiologie> UeMikrobiologie;
                [Feld(Value = "8162", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 38)]
                public IList<UntersuchungsergebnisKrebsfrueherkennungZervixKarzinom> UeKrebsfrueherkennungZervixKarzinom;
                [Feld(Value = "8163", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public IList<UntersuchungsergebnisZytologie> UeZytologie;
                [Feld(Value = "8155", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 25)]
                public IList<Blutgruppenzugehoerigkeit> Blutgruppenzugehoerigkeit;
                [Feld(Value = "8248", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 35)]
                public IList<SonstigeUntersuchungsergebnisse> UeSonstigeUntersuchungsergebnisse;
                [Feld(Value = "8156", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 5)]
                public IList<Tumor> Tumor;
                [Feld(Value = "8221", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 41)]
                public Timestamp TimestampErstellung;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;
                [Feld(Value = "8141", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;

            }
        }
    }
}
