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
 * E030
 */
public enum ResistenzInterpretation {
    /** nicht getestet */
    nicht_getestet("0"),
    /** sensibel/wirksam */
    sensibel_wirksam("1"),
    /** mäßig sensibel/schwach wirksam */
    maessig_sensibel_schwach_wirksam("2"),
    /** resistent/unwirksam */
    resistent_unwirksam("3"),
    /** wirksam in hohen Konzentrationen */
    wirksam_in_hohenKonzentrationen("4"),
    /** natürliche Resistenz */
    natuerlicheResistenz("5");

    public final String code;

    ResistenzInterpretation(String code) {
        this.code = code;
    }

}
