/*
 * Copyright 2016-2024  Christoph Brill &lt;opensource@christophbrill.de&gt;
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
 * E023
 */
public enum Einsenderstatus {
    Erstveranlasser("01"),
    /** Einsender Arzt */
    EinsenderArzt("02"),
    /** Einsender sonstige */
    Einsender_sonstige("03"),
    Versicherter("04"),
    /** Rechnungsempfänger */
    Rechnungsempfaenger("05"),
    /** Bevollmächtigter */
    Bevollmaechtigter("06"),
    /** Laborarzt/Befundersteller */
    Laborarzt_Befundersteller("07"),
    Leistungserbringer("08"),
    /** Halter (eines Tieres) */
    Halter("11"),
    Patient("12"),
    /** Überweiser */
    Ueberweiser("14"),
    /** staatliche Einrichtung */
    staatlicheEinrichtung("15"),
    /** sonstige juristische Person */
    sonstige_juristischePerson("16"),
    /** sonstige medizinische Einrichtung */
    sonstige_medizinischeEinrichtung("17");

    public final String code;

    Einsenderstatus(String code) {
        this.code = code;
    }

}
