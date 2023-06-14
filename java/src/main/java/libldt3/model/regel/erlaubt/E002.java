package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 1 = Mitglied
 * 3 = Familienversicherter
 * 5 = Rentner
 */
public class E002 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "1".equals(value) || "3".equals(value) || "5".equals(value);
    }
}
