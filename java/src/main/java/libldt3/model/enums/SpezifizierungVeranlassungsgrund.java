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
 * E147
 */
public enum SpezifizierungVeranlassungsgrund {
    Eingriff("01"),
    Medikamentengabe("02"),
    /** unklares Fieber */
    unklaresFieber("03"),
    Infektion("04"),
    Rheuma("05"),
    Allergie("06"),
    /** Herz/Kreislauf */
    Herz_Kreislauf("07"),
    Tumor("08"),
    Impfungen("09"),
    Reisen("10"),
    /** Immunität nach Infektion */
    Immunitaet_nachInfektion("11"),
    Sonstiges("12");

    public final String code;

    SpezifizierungVeranlassungsgrund(String code) {
        this.code = code;
    }

}
