package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 1 = Auftrag nicht abgeschlossen
 * 2 = Auftrag abgeschlossen
 */
public class E006 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "1".equals(value) || "2".equals(value);
    }
}
