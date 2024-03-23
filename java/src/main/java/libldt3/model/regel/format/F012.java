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
package libldt3.model.regel.format;

import libldt3.model.regel.RegularExpressionRegel;

import java.util.regex.Pattern;

/**
 * Format KBV-Prüfnummer
 * A = [V, X, Y, Z]
 * nn = [00-99]
 * JJMM = Jahr/Monat
 * MM = Dauer in Monaten
 * aaa = Systemident (alphanumerisch)
 */
public class F012 extends RegularExpressionRegel {

    public static final Pattern PATTERN = Pattern.compile("^([A-Z])/([0-9]{2})/JJ(0[0-9]|1[012])/(0[0-9]|1[012])/([A-Z])([A-Z])([A-Z])$");

    public F012() {
        super(PATTERN);
    }

}
