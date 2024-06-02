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
using NodaTime;
using libldt3.attributes;
using libldt3.model;
using libldt3.model.enums;
using libldt3.model.regel.format;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// In diesem Objekt werden die Informationen des Musters OEGD abgebildet.
            /// </summary>
            [Objekt(Value = "0009", Kontextregeln = new[] { typeof(K130), typeof(K131), typeof(K132), typeof(K135) })]
            public class AbrechnungOEGD : Kontext
            {
                [Feld(Value = "4110", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? VersicherungsschutzEnde;
                [Feld(Value = "8626", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public TestungRechtsgrundlage? RechtsgrundlageTestung;
                [Feld(Value = "8627", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MinLaenge = 1, MaxLaenge = 5)]
                public string KvSonderziffer;
                [Feld(Value = "8617", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 1)]
                public Beauftragungsgrund? Beauftragungsgrund;
                [Feld(Value = "4111", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 9)]
                public string Kostentraegerkennung;
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
                [Feld(Value = "8621", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? Einverstaendnis;
                [Feld(Value = "8622", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 43)]
                public string CoronaGuid;
                [Feld(Value = "8625", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 5)]
                public string PlzOeGd;
                [Feld(Value = "8623", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 22)]
                public string IdentifikationAktenzeichenOeGd;

            }
        }
    }
}
