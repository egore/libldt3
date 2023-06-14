package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

/**
 * 8220, 8221, 8230, 8231, 8205, 8215
 */
public class E004 implements Regel {

    Set<String> VALID = Set.of("8220", "8221", "8230", "8231", "8205", "8215");

    @Override
    public boolean isValid(String value) {
        return VALID.contains(value);
    }

}
