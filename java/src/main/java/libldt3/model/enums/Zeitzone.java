/*
 * Copyright 2016-2023  Christoph Brill &lt;opensource@christophbrill.de&gt;
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
package libldt3.model.enums;

/**
 * E163
 */
public enum Zeitzone {
    UTC_MINUS_12("UTC−12"),
    UTC_MINUS_11("UTC−11"),
    UTC_MINUS_10("UTC−10"),
    UTC_MINUS_9_30("UTC−9:30"),
    UTC_MINUS_9("UTC−9"),
    UTC_MINUS_8("UTC−8"),
    UTC_MINUS_7("UTC−7"),
    UTC_MINUS_6("UTC−6"),
    UTC_MINUS_5("UTC−5"),
    UTC_MINUS_4("UTC−4"),
    UTC_MINUS_3_30("UTC−3:30"),
    UTC_MINUS_3("UTC−3"),
    UTC_MINUS_2("UTC−2"),
    UTC_MINUS_1("UTC−1"),
    UTC("UTC"),
    UTC_PLUS_1("UTC+1"),
    UTC_PLUS_2("UTC+2"),
    UTC_PLUS_3("UTC+3"),
    UTC_PLUS_3_30("UTC+3:30"),
    UTC_PLUS_4("UTC+4"),
    UTC_PLUS_4_30("UTC+4:30"),
    UTC_PLUS_5("UTC+5"),
    UTC_PLUS_5_30("UTC+5:30"),
    UTC_PLUS_5_45("UTC+5:45"),
    UTC_PLUS_6("UTC+6"),
    UTC_PLUS_6_30("UTC+6:30"),
    UTC_PLUS_7("UTC+7"),
    UTC_PLUS_8("UTC+8"),
    UTC_PLUS_8_30("UTC+8:30"),
    UTC_PLUS_9("UTC+9"),
    UTC_PLUS_9_30("UTC+9:30"),
    UTC_PLUS_10("UTC+10"),
    UTC_PLUS_10_30("UTC+10:30"),
    UTC_PLUS_11("UTC+11"),
    UTC_PLUS_12("UTC+12"),
    UTC_PLUS_12_45("UTC+12:45"),
    UTC_PLUS_13("UTC+13"),
    UTC_PLUS_13_45("UTC+13:45"),
    UTC_PLUS_14("UTC+14");

    public final String code;

    Zeitzone(String code) {
        this.code = code;
    }

}
