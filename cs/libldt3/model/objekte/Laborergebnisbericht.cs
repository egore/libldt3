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

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Im Objekt werden die Untersuchungsergebnisse zusammengefasst.
            /// </summary>
            [Objekt(Value = "0035")]
            public class Laborergebnisbericht : Kontext
            {
                [Feld(Value = "8160", Name = "UE_Klinische_Chemie", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 19)]
                public IList<UntersuchungsergebnisKlinischeChemie> KlinischeChemie;
                [Feld(Value = "8161", Name = "UE_Mikrobiologie", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 16)]
                public IList<UntersuchungsergebnisMikrobiologie> Mikrobiologie;
                [Feld(Value = "8162", Name = "UE_Zytologie_Krebsvorsorge", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<UntersuchungsergebnisZytologieKrebsvorsorge> ZytologieKrebsvorsorge;
                [Feld(Value = "8163", Name = "UE_Zytologie", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public IList<UntersuchungsergebnisZytologie> Zytologie;
                [Feld(Value = "8155", Name = "Transfusionsmedizin_Mutterschaftsvorsorge", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 41)]
                public IList<TransfusionsmedizinMutterschaftsvorsorge> TransfusionsmedizinMutterschaftsvorsorge;
                [Feld(Value = "8156", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 5)]
                public IList<Tumor> Tumor;
                [Feld(Value = "8221", Name = "Timestamp_Erstellung_Laborergebnisbericht", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 41)]
                public Timestamp TimestampErstellungLaborergebnisbericht;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> Text;
                [Feld(Value = "8110", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;
                [Feld(Value = "8141", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 13)]
                public Namenskennung Namenskennung;

            }
        }
    }
}
