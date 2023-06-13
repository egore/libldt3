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
package libldt3.model.enums;

/**
 * E146
 */
public enum Untersuchungsanlass {

    /** Vorsorge */
    Vorsorge("01"),
    /** Verlaufskontrolle */
    Verlaufskontrolle("02"),
    /** Zustand vor */
    ZustandVor("03"),
    /** Zustand nach */
    ZustandNach("04"),
    /** Ausschluss */
    Ausschluss("05"),
    /** Bestätigung */
    Bestaetigung("06"),
    /** gezielte Suche */
    GezielteSuche("07"),
    /** ungezielte Suche */
    UngezielteSuche("08"),
    /** Erfolgskontrolle */
    Erfolgskontrolle("09"),
    /** Abschlusskontrolle */
    Abschlusskontrolle("10"),
    /** Immunität/Impferfolg */
    ImmunitaetImpferfolg("11");

    public final String code;

    Untersuchungsanlass(String code) {
        this.code = code;
    }

}
