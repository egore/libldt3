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
 * E021
 */
public enum BesonderePersonengruppe {
    /** keine Angabe */
    keineAngabe("00"),
    /** BSHG (Bundessozialhilfegesetz) § 264 SGB V */
    BSHG264SGB_V("04"),
    /** SER (Soziales Entschädigungsrecht) */
    SER("06"),
    /** SVA-Kennzeichnung für zwischenstaatliches
     * Krankenversicherungsrecht: Personen mit Wohnsitz im Inland,  Abrechnung nach
     * Aufwand */
    SVA_Kennzeichnung_fuer_zwischenstaatliches("07"),
    /** SVA-Kennzeichnung, pauschal */
    SVA_Kennzeichnung_pauschal("08"),
    /** Empfänger von Gesundheitsleistungen nach den §§ 4, 6 AsylbLG */
    Empfaenger_vonGesundheitsleistungen_nach_den46AsylbLG("09");

    public final String code;

    BesonderePersonengruppe(String code) {
        this.code = code;
    }

}
