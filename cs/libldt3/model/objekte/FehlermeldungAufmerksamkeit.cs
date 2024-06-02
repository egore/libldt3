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

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /// <summary>
            /// Dieses Objekt soll genutzt werden, wenn es aus Sicht des Auftragsnehmers
            /// Vorkommnisse im Prozess gegeben hat, die eine zusätzliche Benachrichtigung des
            /// Einsenders erfordern.
            /// </summary>
            [Objekt(Value = "0026")]
            public class FehlermeldungAufmerksamkeit : Kontext
            {
                [Objekt]
                public class GrundBenachrichtigung_ : Kontext
                {
                    public Benachrichtigungsgrund? Value;
                    [Feld(Value = "7320", Feldart = Feldart.bedingt_kann)]
                    [Regelsatz(Laenge = 1)]
                    public RecallEmpfohlen_ RecallEmpfohlen;

                }
                [Objekt]
                public class RecallEmpfohlen_ : Kontext
                {
                    public bool? Value;
                    [Feld(Value = "8154", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(Laenge = 9)]
                    public Timestamp Timestamp;

                }
                [Feld(Value = "7280", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public IList<GrundBenachrichtigung_> GrundBenachrichtigung;
                [Feld(Value = "8147", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public Person Person;
                [Feld(Value = "8167", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> ZusaetzlicheInformationen;
                [Feld(Value = "8110", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Laenge = 6)]
                public IList<Anhang> Anhang;

            }
        }
    }
}
