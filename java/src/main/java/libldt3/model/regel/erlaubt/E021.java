package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

/**
 * 00 = keine Angabe
 * 04 = BSHG (Bundessozialhilfegesetz) § 264 SGB V
 * 06 = BVG (Gesetz über die Versorgung der Opfer des Krieges)
 * 07 = SVA-Kennzeichnung für zwischenstaatliches Krankenversicherungsrecht: Personen mit Wohnsitz im Inland, Abrechnung
 *      nach Aufwand
 * 08 = SVA-Kennzeichnung, pauschal
 * 09 = Empfänger von Gesundheitsleistungen nach den §§ 4, 6 AsylbLG
 */
public class E021 implements Regel {

    Set<String> VALID = Set.of("00", "04", "06", "07", "08", "09");

    @Override
    public boolean isValid(String value) {
        return VALID.contains(value);
    }

}
