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
using System;

namespace libldt3
{
    namespace model
    {
        namespace enums
        {

            /**
             * E027
             */
            public enum StatusPerson
            {
                /** Erstveranlasser */
                Erstveranlasser,
                /** Einsender Arzt */
                EinsenderArzt,
                /** Einsender Sonstige */
                EinsenderSonstige,
                /** Versicherter */
                Versicherter,
                /** Rechnungsempfänger */
                Rechnungsempfaenger,
                /** Bevollmächtigter */
                Bevollmaechtigter,
                /** Laborarzt/Befundersteller */
                Laborarzt_Befundersteller,
                /** Leistungserbringer */
                Leistungserbringer,
                /** Softwareverantwortlicher */
                Softwareverantwortlicher,
                /** Zusätzlicher Befundempfänger */
                ZusaetzlicherBefundempfaenger,
                /** Halter (eines Tieres) */
                Tierhalter,
                /** Patient */
                Patient,
                /** Überweiser */
                Ueberweiser,
                /** sonstige juristische Person */
                sonstigeJuristischePerson,
                /** Medizinisch-technische/r Assistent/in (MTA) */
                MTA,
                /** Medizinische/r Fachangestellte/r (MFA) */
                MFA
            }

            public static class StatusPersonExtensions
            {
                public static string GetCode(this StatusPerson self)
                {
                    switch (self)
                    {
                        case StatusPerson.Erstveranlasser: return "01";
                        case StatusPerson.EinsenderArzt: return "02";
                        case StatusPerson.EinsenderSonstige: return "03";
                        case StatusPerson.Versicherter: return "04";
                        case StatusPerson.Rechnungsempfaenger: return "05";
                        case StatusPerson.Bevollmaechtigter: return "06";
                        case StatusPerson.Laborarzt_Befundersteller: return "07";
                        case StatusPerson.Leistungserbringer: return "08";
                        case StatusPerson.Softwareverantwortlicher: return "09";
                        case StatusPerson.ZusaetzlicherBefundempfaenger: return "10";
                        case StatusPerson.Tierhalter: return "11";
                        case StatusPerson.Patient: return "12";
                        case StatusPerson.Ueberweiser: return "14";
                        case StatusPerson.sonstigeJuristischePerson: return "16";
                        case StatusPerson.MTA: return "17";
                        case StatusPerson.MFA: return "18";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
