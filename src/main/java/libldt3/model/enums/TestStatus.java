/*
 * Copyright 2016  Christoph Brill <egore911@gmail.com>
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
 * E007
 */
public enum TestStatus {
    /**
     * Es ist keine gesicherte Information zum Ergebnis verfügbar oder
     * abzubilden und es wird auch keine Information dazu ausgegeben oder
     * ausgegeben werden können.
     */
    keine_gesicherte_Information("01"),
    /**
     * Es liegt eine Untersuchungsanforderung vor, für die es aktuell noch
     * kein Ergebnis gibt.
     */
    Ergebnis_folgt("02"),
    /**
     * Ein technisch validiertes Ergebnis ist ermittelt. Im Kontext der
     * Laborgemeinschaft kann die Abrechnung der Leistung erfolgen.
     */
    Ergebnis("03"),
    /**
     * Ein technisch validiertes Ergebnis wurde korrigiert. Die Korrektur
     * ist zu dokumentieren. Es erfolgt keine weitere Abrechnung.
     */
    Ergebnis_korrigiert("04"),
    /**
     * Ein ärztlich validiertes Ergebnis liegt vor, allerdings läuft die
     * Analytik zur Absicherung noch weiter. In Einzelfällen können sich
     * noch Veränderungen ergeben. Es folgt dann ein korrigiertes Ergebnis.
     */
    Ergebnis_ermittelt("05"),
    /**
     * Die Analytik dieser Untersuchungsanforderung ist abgeschlossen und
     * ein ärztlich validiertes Ergebnis liegt vor. Die Abrechnung der
     * Leistung kann erfolgen.
     */
    Befundergebnis("06"),
    /**
     * Das Befundergebnis ist unverändert schon mindestens einmal übermittelt
     * worden (keine Abrechnung!).
     */
    Befundergebnis_bereits_berichtet("07"),
    /**
     * Das schon übermittelte Befundergebnis ist korrigiert worden. Damit hat
     * nur noch dieses korrigierte Befundergebnis Gültigkeit und alle
     * bisherigen Befundergebnisse zu dieser Untersuchungsanforderung verlieren
     * Ihre Gültigkeit. Die Korrektur ist zu dokumentieren. Es erfolgt keine
     * weitere Abrechnung.
     */
    Befundergebnis_korrigiert("08"),
    /**
     * Das Ergebnis ist nicht vorhanden oder kann nicht mehr ermittelt werden.
     * Weil das Ergebnis fehlt, kann auch kein Befundergebnis erstellt werden.
     */
    Ergebnis_fehlt("09"),
    /**
     * Eine erweiterte Untersuchungsanalytik zur besseren Beurteilung und
     * Absicherung des bisher ermittelten ärztlich validierten Befundes ist
     * erforderlich. Die weiteren Ergebnisse werden in folgenden
     * Befundberichten ergänzt. Kommentar: Diese Ergebnisse werden zu Befunden
     * (einer Leistung). Der Befundbericht vor Einleitung der erweiterten
     * Analytik kann nur den Status "Auftrag nicht abgeschlossen" haben. Sollte
     * dies nicht zutreffen ist ein neuer Auftrag zu erstellen!
     */
    Erweiterte_Analytik_erforderlich("10"),
    /**
     * Für die Untersuchungsanforderung ist kein Material für die Analytik
     * vorhanden.
     */
    Material_fehlt("11"),
    /**
     * Die Untersuchungsanforderung wurde storniert.
     */
    Storniert("12");

    public final String code;

    TestStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}