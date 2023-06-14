package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 1 = Verdacht auf infektiös
 * 2 = gesichert infektiös
 */
public class E031 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "1".equals(value) || "2".equals(value);
    }
}
