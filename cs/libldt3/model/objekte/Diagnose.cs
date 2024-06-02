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
            /// Mit diesem Objekt können Angaben zu Diagnosen des Patienten übertragen werden.
            /// </summary>
            [Objekt(Value = "0100")]
            public class Diagnose : Kontext
            {
                [Objekt]
                public class IcdCode_ : Kontext
                {
                    public string Value;
                    [Feld(Value = "6003", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public Diagnosesicherheit? Diagnosesicherheit;
                    [Feld(Value = "6004", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public Lokalisation? Lokalisation;
                    [Feld(Value = "6006", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> Diagnoseerlaeuterung;
                    [Feld(Value = "6008", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(MaxLaenge = 60)]
                    public IList<string> Diagnoseausnahmetatbestand;

                }
                [Feld(Value = "4207", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> DiagnoseVerdachtsdiagnose;
                [Feld(Value = "6001", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F004) }, MinLaenge = 3, MaxLaenge = 6)]
                public IcdCode_ IcdCode;

            }
        }
    }
}
