package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

/**
 * 21 = Auftragsleistungen
 * 23 = Konsiliaruntersuchung
 * 24 = Mit-/Weiterbehandlung
 * 27 = Überweisungsschein für Laboratoriumsuntersuchungen als Auftragsleistung (Muster 10)
 * 28 = Anforderungsschein für Laboratoriumsuntersuchungen bei Laborgemeinschaften (Muster 10A)
 */
public class E009 implements Regel {

    Set<String> VALID = Set.of("21", "23", "24", "27", "28");

    @Override
    public boolean isValid(String value) {
        return VALID.contains(value);
    }

}
