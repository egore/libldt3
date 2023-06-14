package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 1 = Keim
 * 2 = Pilz
 */
public class E016 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "1".equals(value) || "2".equals(value);
    }
}
