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
             * E047
             */
            public enum ArztTypId
            {

                /** IK des Arztes */
                IK,
                /** Telematik-ID */
                TelematikId,
                /** ID für GEVK-Verträge */
                GevkId,
                /** ID für HÄVG-Verträge */
                HaevgId,
                /** ID für MEDI-Verträge */
                MediId,
                /** Selektivvertrag */
                Selektivvertrag,
                /** Sonstige */
                Sonstige

            }

            public static class ArztTypIdExtensions
            {
                public static string GetCode(this ArztTypId self)
                {
                    switch (self)
                    {
                        case ArztTypId.IK: return "2";
                        case ArztTypId.TelematikId: return "3";
                        case ArztTypId.GevkId: return "4";
                        case ArztTypId.HaevgId: return "5";
                        case ArztTypId.MediId: return "6";
                        case ArztTypId.Selektivvertrag: return "7";
                        case ArztTypId.Sonstige: return "9";

                        default: throw new Exception();
                    }
                }
            }
        }
    }
}