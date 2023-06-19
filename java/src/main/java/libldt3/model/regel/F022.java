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
package libldt3.model.regel;

import java.util.regex.Pattern;

/**
 * Pseudo-LANR für Krankenhausärzte im Rahmen der ASV-Abrechnung
 * (ASV-AV Anlage 3 Fachgruppencodierungen)
 * 555555 = Pseudo-Arztnummer für Krankenhausärzte im Rahmen der
 * ASV-Abrechnung
 * n = Ordnungsnummer (zulässige Werte 0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
 * ff = Fachgruppencode gemäß der jeweils gültigen Anlage 2 der Richtlinie
 */
public class F022 extends RegularExpressionRegel {

    public static final Pattern PATTERN = Pattern.compile("^555555([0-9])[0-9]{2}$");

    public F022() {
        super(PATTERN);
    }

}
