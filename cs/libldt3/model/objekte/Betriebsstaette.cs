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
using libldt3.model.regel;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Dieses Objekt fasst die notwendigen Informationen zur Betriebsstätte von
            /// medizinischen Einrichtungen zusammen.
            /// </summary>
            [Objekt(Value = "0019")]
            public class Betriebsstaette : Kontext
            {
                [Feld(Value = "0204", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public IList<Betriebsstaettenstatus?> Status;
                [Feld(Value = "0203", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string BsnrBezeichnung;
                [Feld(Value = "0200", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string BetriebsstaettenId;
                [Feld(Value = "0201", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F010), typeof(F021) }, Laenge = 9)]
                public string Bsnr;
                [Feld(Value = "0213", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 9)]
                public string Institutskennzeichen;
                [Feld(Value = "8143", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 12)]
                public Organisation Organisation;

            }
        }
    }
}
