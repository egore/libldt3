package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 1 = Privat
 * 2 = Post B
 * 3 = KVB
 */
public class E017 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "1".equals(value) || "2".equals(value) || "3".equals(value);
    }
}
