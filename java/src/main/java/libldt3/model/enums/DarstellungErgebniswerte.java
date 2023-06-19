/*
 * Copyright 2016-2023  Christoph Brill <opensource@christophbrill.de>
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
    numerisch("01"),
    /** numerisch mit Messwertuntergrenze */
    numerisch_mitMesswertuntergrenze("02"),
    /** numerisch mit Messwertobergrenze */
    numerisch_mitMesswertobergrenze("03"),
    /** alpha-numerisch */
    alphanumerisch("04"),
    Titer("05"),
    /** Titer mit Untergrenze */
    Titer_mitUntergrenze("06"),
    /** Titer mit Obergrenze */
    Titer_mitObergrenze("07"),
    /** trinäres Testergebnis: 1 | 2 | 3 ** */
    trinaeresTestergebnis("08"),
    /** Beispiele
     * 01: 47.85, 5.00E+07, 1x10^6 02: <100, <1.00E+04 03: >2000, >5.00E+04 04:
     * positiv, negativ, A positiv * 05: 1:2 06: <1:2 07: >1:2 08: 1, 2, 3 ** *   für
     * die Übertragung von Blutgruppen ist vorzugsweise das Obj_0055 zu verwenden **
     * Abbildung der Regel E169 */
    Sonstige("99");

    public final String code;

    DarstellungErgebniswerte(String code) {
        this.code = code;
    }

}
