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
 * Format ASV-Teamnummer
 * ASV-ID-Kürzel
 * nnnnnn = eindeutige Nummer
 * P = Prüfziffer *
 * * Die Prüfziffer wird mittels des Modulo 10 – Verfahrens der Stellen 3 bis 8 der
 * ASV-Teamnummer ermittelt. Bei diesem Verfahren werden die Ziffern 3 bis 8 von
 * links nach rechts abwechselnd mit 4 und 9 multipliziert. Die Summe dieser
 * Produkte wird Modulo 10 berechnet. Die Prüfziffer ergibt sich aus der Differenz
 * dieser Zahl zu 10.
 */
public class F014 extends RegularExpressionRegel {

    public static final Pattern PATTERN = Pattern.compile("^00([0-9]{6})([0-9])$");

    public F014() {
        super(PATTERN);
    }

}
