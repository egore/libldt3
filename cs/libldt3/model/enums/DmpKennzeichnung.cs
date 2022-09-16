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
             * E020
             * 
             * Disease Management Program (DMP) bezeichnet die Behandlung von Patienten
             * anhand standardisierter Vorgaben. Diese richten sich an einzelnen
             * Krankheitsbildern aus. Wird beispielsweise festgestellt, dass ein Patient an
             * Diabetes leidet, ergeben sich aus der Diagnose vorgegebene
             * Behandlungsschritte.
             */
            public enum DmpKennzeichnung
            {

                /** keine Angabe */
                keine_Angabe,
                /** Diabetes mellitus Typ 2 */
                DiabetesMellitusTyp2,
                /** Brustkrebs */
                Brustkrebs,
                /** Koronare Herzkrankheit */
                KoronareHerzkrankheit,
                /** Diabetes mellitus Typ 1 */
                DiabetesMellitusTyp1,
                /** Asthma bronchiale */
                AsthmaBronchiale,
                /** COPD (chronic obstructive pulmo-nary disease) */
                ChronicObstructivePulmonaryDisease
            }

            public static class DmpKennzeichnungExtensions
            {
                public static string GetCode(this DmpKennzeichnung self)
                {
                    switch (self)
                    {
                        case DmpKennzeichnung.keine_Angabe: return "00";
                        case DmpKennzeichnung.DiabetesMellitusTyp2: return "01";
                        case DmpKennzeichnung.Brustkrebs: return "02";
                        case DmpKennzeichnung.KoronareHerzkrankheit: return "03";
                        case DmpKennzeichnung.DiabetesMellitusTyp1: return "04";
                        case DmpKennzeichnung.AsthmaBronchiale: return "05";
                        case DmpKennzeichnung.ChronicObstructivePulmonaryDisease: return "06";

                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
