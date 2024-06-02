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
            /// Im Objekt Anhang können Informationen wie Befunde, Fotos oder sonstige
            /// Dokumentationen, die in einem digitalen Standardformat vorliegen, transportiert
            /// werden.
            /// </summary>
            [Objekt(Value = "0010", Kontextregeln = new[] { typeof(K001), typeof(K075), typeof(K100) })]
            public class Anhang : Kontext
            {
                [Feld(Value = "9970", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 3)]
                public Dokumententyp? Dokumententyp;
                [Feld(Value = "6221", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? KennzeichnungFremdbefund;
                [Feld(Value = "6305", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string DateiVerweis;
                [Feld(Value = "8242", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 22)]
                public Fliesstext Base64kodierteAnlage;
                [Feld(Value = "6303", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string Dateiformat;
                [Feld(Value = "6328", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string Dateicodierung;
                [Feld(Value = "6327", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string BildinhaltDokumentinhalt;
                [Feld(Value = "9908", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string OriginaldokumentPfadSpeicherort;
                [Feld(Value = "9909", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string LangzeitArchivierungPfadSpeicherort;
                [Feld(Value = "9980", Feldart = Feldart.bedingt_kann)]
                public IList<string> ExterneDokumentenIdZurArchivierung;
                [Feld(Value = "9981", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 1)]
                public Dokumentenquelle? Dokumentenquelle;

            }
        }
    }
}
