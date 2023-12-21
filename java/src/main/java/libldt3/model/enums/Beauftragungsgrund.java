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
 * E172
 */
public enum Beauftragungsgrund {
    /** Test nach § 2 TestV Kontaktpersonen, nachweislich Infizierte,
     * Voraufenthalt Virusvariantengebiet */
    Test_nach2TestV_Kontaktpersonen_nachweislichInfizierte("1"),
    /** Test nach § 3 TestV Ausbruchsgeschehen */
    Test_nach3TestV_Ausbruchsgeschehen("3"),
    /** Test nach § 4 Abs. 1 Nr. 1 und 2 TestV Verhütung der Verbreitung */
    Test_nach4Abs_1Nr_1_und2TestV_Verhuetung_derVerbreitung("4");

    public final String code;

    Beauftragungsgrund(String code) {
        this.code = code;
    }

}
