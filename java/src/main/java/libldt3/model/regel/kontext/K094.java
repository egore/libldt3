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
package libldt3.model.regel.kontext;

import libldt3.model.Kontext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wenn Inhalt von FK 7420 = 12 und FK 7303 mit den Werten 1, 2, 3, 8, 9 oder 10 in
 * jeweiliger Satzart 8205 oder 8215 vorkommen, dann müssen die FK 3103, FK 3110
 * und FK 8228 vorhanden sein.
 *
 * Handelt es sich bei der Person um einen Patienten und kommen in der
 * jeweiligen Satzart "Auftrag" oder "Befund" die Werte für Abrechnungsinfo
 * zur Untersuchung 1, 2, 3, 8, 9 oder 10 vor, müssen die Angaben zum
 * Geburtsdatum, Geschlecht und Wohnort vorhanden sein.
 * Diese Regel ermöglicht es, Aufträge bzw. Befunde zu übertragen, bei
 * denen die Angaben zum Geschlecht, Geburtsdatum bzw. Wohnort des
 * Patienten nicht oder nicht komplett vorhanden sind.
 */
public class K094 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K094.class);

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {
        throw new UnsupportedOperationException();
    }

}
