/*
 * Copyright 2016-2017  Christoph Brill <egore911@gmail.com>
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
 * E058
 */
public enum DarstellungErgebniswerte {

    /** numerisch (exponentielle Darstellung möglich) */
    Numerisch("01"),
    /** numerisch mit Messwertuntergrenze */
    Numerisch_Untergrenze("02"),
    /** numerisch mit Messwertobergrenze */
    Numerisch_Obergrenze("03"),
    /** alpha-numerisch */
    Alphanumerisch("04"),
    /** Titer */
    Titer("05"),
    /** Titer mit Untergrenze */
    Titer_Untergrenze("06"),
    /** Titer mit Obergrenze */
    Titer_Obergrenze("07"),
    /** Sonstige */
    Sonstige("99");

    public final String code;

    DarstellungErgebniswerte(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
