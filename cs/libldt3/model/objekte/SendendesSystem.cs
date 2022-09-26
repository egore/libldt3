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
            /// Dieses Objekt enthält die Information zum sendenden Softwaresystem, welches
            /// den LDT Datensatz erstellt hat.
            /// </summary>
            [Objekt(Value = "0051")]
            public class SendendesSystem : Kontext
            {
                [Feld(Value = "0001", Feldart = Feldart.muss)]
                [Regelsatz(Value = new[] { typeof(F007) }, MaxLaenge = 12)]
                public LdtVersion? Version;
                [Feld(Value = "8315", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string EmpfaengerId;
                [Feld(Value = "8316", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string SenderId;
                [Feld(Value = "0105", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F012) }, Laenge = 16)]
                public string KvbPruefnummer;
                [Feld(Value = "8212", Name = "Softwareverantwortlicher", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 24)]
                public Organisation Softwareverantwortlicher;
                [Feld(Value = "0103", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string SoftwareName;
                [Feld(Value = "0132", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string SoftwareVersion;

            }
        }
    }
}
