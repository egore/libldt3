package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

/**
 * 01 = keine gesicherte Information
 * Es ist keine gesicherte Information zum Ergebnis verfügbar oder abzubilden und es wird auch keine Information dazu
 * ausgegeben oder angegeben werden können.
 *
 * 02 = Ergebnis folgt
 * Es liegt eine Untersuchungsanforderung vor, für die es aktuell noch kein Ergebnis gibt.
 *
 * 03 = Ergebnis
 * Ein technisch validiertes Ergebnis ist ermittelt.
 *
 * 04 = Ergebnis korrigiert
 * Ein technisch validiertes Ergebnis wurde korrigiert. Die Korrektur ist zu dokumentieren. Es erfolgt keine weitere
 * Abrechnung.
 *
 * 05 = Ergebnis ermittelt
 * Ein ärztlich validiertes Ergebnis liegt vor, allerdings läuft die Analytik zur Absicherung noch weiter. In
 * Einzelfällen können sich noch Veränderungen ergeben. Es folgt dann ein korrigiertes Ergebnis.
 *
 * 06 = Befundergebnis
 * Die Analytik dieser Untersuchungsanforderung ist abgeschlossen und ein ärztlich validiertes Ergebnis liegt vor.
 *
 * 07 = Befundergebnis bereits berichtet
 * Das Befundergebnis ist unverändert schon mindestens einmal übermittelt worden (keine Abrechnung!).
 *
 * 08 = Befundergebnis korrigiert
 * Das schon übermittelte Befundergebnis ist korrigiert worden. Damit hat nur noch dieses korrigierte Befundergebnis
 * Gültigkeit und alle bisherigen Befundergebnisse zu dieser Untersuchungsanforderung verlieren Ihre Gültigkeit. Die
 * Korrektur ist zu dokumentieren. Es erfolgt keine weitere Abrechnung.
 *
 * 09 = Ergebnis fehlt
 * Das Ergebnis ist nicht vorhanden oder kann nicht mehr ermittelt werden. Weil das Ergebnis fehlt, kann auch kein
 * Befundergebnis erstellt werden.
 *
 * 10 = Erweiterte Analytik erforderlich
 * Eine erweiterte Untersuchungsanalytik zur besseren Beurteilung und Absicherung des bisher ermittelten ärztlich
 * validierten Befundes ist erforderlich. Die weiteren Ergebnisse werden in folgenden Befundberichten ergänzt.
 * Kommentar: Diese Ergebnisse werden zu Befunden (einer Leistung). Der Befundbericht vor Einleitung der erweiterten
 * Analytik kann nur den Status "Auftrag nicht abgeschlossen" haben. Sollte dies nicht zutreffen ist ein neuer
 * Auftrag zu erstellen!
 *
 * 11 = Material fehlt
 * Für die Untersuchungsanforderung ist kein Material für die Analytik vorhanden.
 *
 * 12 = Storniert
 * Die Untersuchungsanforderung wurde storniert.
 */
public class E007 implements Regel {

    Set<String> VALID = Set.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

    @Override
    public boolean isValid(String value) {
        return VALID.contains(value);
    }

}
