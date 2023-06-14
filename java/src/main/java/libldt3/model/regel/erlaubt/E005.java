package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

/**
 * 8220, 8221, 8230, 8231, 8205, 8215
 */
public class E005 implements Regel {

    Set<String> VALID_NUMERIC = Set.of("N", "H", "+", "HH", "++", "L", "-", "LL", "--", "!H", "!+", "!L", "!-");
    Set<String> VALID_NON_NUMERIC = Set.of("N", "A", "AA");

    @Override
    public boolean isValid(String value) {
        // TODO only once should be used
        return VALID_NUMERIC.contains(value) || VALID_NON_NUMERIC.contains(value);
    }

}
