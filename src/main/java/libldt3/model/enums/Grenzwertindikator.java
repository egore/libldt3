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
 * E005
 */
public enum Grenzwertindikator {
    /** im Normalbereich */
    N("N"),
    /** schwach erhöht */
    H("H"),
    /** schwach erhöht */
    PLUS("+"),
    /** stark erhöht */
    HH("HH"),
    /** stark erhöht */
    PLUS_PLUS("++"),
    /** schwach erniedrigt */
    L("L"),
    /** schwach erniedrigt */
    MINUS("-"),
    /** stark erniedrigt */
    LL("LL"),
    /** stark erniedrigt */
    MINUS_MINUS("--"),
    /** Wert extrem erhöht */
    EXTERM_H("!H"),
    /** Wert extrem erhöht */
    EXTREM_PLUS("!+"),
    /** Wert extrem erniedrigt */
    EXTREM_L("!L"),
    /** Wert extrem erniedrigt */
    EXTREM_MINUS("!-"),
    /** auffällig */
    A("A"),
    /** sehr auffällig */
    AA("AA");

    private final String code;

    Grenzwertindikator(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
