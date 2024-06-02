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
namespace libldt3
{
    namespace model
    {
        namespace enums
        {

            /// <summary>
            /// E163
            /// </summary>
            public enum Zeitzone
            {
                UTC_MINUS_12,
                UTC_MINUS_11,
                UTC_MINUS_10,
                UTC_MINUS_9_30,
                UTC_MINUS_9,
                UTC_MINUS_8,
                UTC_MINUS_7,
                UTC_MINUS_6,
                UTC_MINUS_5,
                UTC_MINUS_4,
                UTC_MINUS_3_30,
                UTC_MINUS_3,
                UTC_MINUS_2,
                UTC_MINUS_1,
                UTC,
                UTC_PLUS_1,
                UTC_PLUS_2,
                UTC_PLUS_3,
                UTC_PLUS_3_30,
                UTC_PLUS_4,
                UTC_PLUS_4_30,
                UTC_PLUS_5,
                UTC_PLUS_5_30,
                UTC_PLUS_5_45,
                UTC_PLUS_6,
                UTC_PLUS_6_30,
                UTC_PLUS_7,
                UTC_PLUS_8,
                UTC_PLUS_8_30,
                UTC_PLUS_9,
                UTC_PLUS_9_30,
                UTC_PLUS_10,
                UTC_PLUS_10_30,
                UTC_PLUS_11,
                UTC_PLUS_12,
                UTC_PLUS_12_45,
                UTC_PLUS_13,
                UTC_PLUS_13_45,
                UTC_PLUS_14
            }

            public static class ZeitzoneExtensions
            {
                public static string GetCode(this Zeitzone self)
                {
                    switch (self)
                    {
                        case Zeitzone.UTC_MINUS_12: return "UTC−12";
                        case Zeitzone.UTC_MINUS_11: return "UTC−11";
                        case Zeitzone.UTC_MINUS_10: return "UTC−10";
                        case Zeitzone.UTC_MINUS_9_30: return "UTC−9:30";
                        case Zeitzone.UTC_MINUS_9: return "UTC−9";
                        case Zeitzone.UTC_MINUS_8: return "UTC−8";
                        case Zeitzone.UTC_MINUS_7: return "UTC−7";
                        case Zeitzone.UTC_MINUS_6: return "UTC−6";
                        case Zeitzone.UTC_MINUS_5: return "UTC−5";
                        case Zeitzone.UTC_MINUS_4: return "UTC−4";
                        case Zeitzone.UTC_MINUS_3_30: return "UTC−3:30";
                        case Zeitzone.UTC_MINUS_3: return "UTC−3";
                        case Zeitzone.UTC_MINUS_2: return "UTC−2";
                        case Zeitzone.UTC_MINUS_1: return "UTC−1";
                        case Zeitzone.UTC: return "UTC";
                        case Zeitzone.UTC_PLUS_1: return "UTC+1";
                        case Zeitzone.UTC_PLUS_2: return "UTC+2";
                        case Zeitzone.UTC_PLUS_3: return "UTC+3";
                        case Zeitzone.UTC_PLUS_3_30: return "UTC+3:30";
                        case Zeitzone.UTC_PLUS_4: return "UTC+4";
                        case Zeitzone.UTC_PLUS_4_30: return "UTC+4:30";
                        case Zeitzone.UTC_PLUS_5: return "UTC+5";
                        case Zeitzone.UTC_PLUS_5_30: return "UTC+5:30";
                        case Zeitzone.UTC_PLUS_5_45: return "UTC+5:45";
                        case Zeitzone.UTC_PLUS_6: return "UTC+6";
                        case Zeitzone.UTC_PLUS_6_30: return "UTC+6:30";
                        case Zeitzone.UTC_PLUS_7: return "UTC+7";
                        case Zeitzone.UTC_PLUS_8: return "UTC+8";
                        case Zeitzone.UTC_PLUS_8_30: return "UTC+8:30";
                        case Zeitzone.UTC_PLUS_9: return "UTC+9";
                        case Zeitzone.UTC_PLUS_9_30: return "UTC+9:30";
                        case Zeitzone.UTC_PLUS_10: return "UTC+10";
                        case Zeitzone.UTC_PLUS_10_30: return "UTC+10:30";
                        case Zeitzone.UTC_PLUS_11: return "UTC+11";
                        case Zeitzone.UTC_PLUS_12: return "UTC+12";
                        case Zeitzone.UTC_PLUS_12_45: return "UTC+12:45";
                        case Zeitzone.UTC_PLUS_13: return "UTC+13";
                        case Zeitzone.UTC_PLUS_13_45: return "UTC+13:45";
                        case Zeitzone.UTC_PLUS_14: return "UTC+14";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
