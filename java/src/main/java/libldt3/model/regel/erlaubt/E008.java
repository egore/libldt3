package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 0 = EBM
 * 1 = BMÄ
 * 2 = EGO
 * 3 = GOÄ
 * 4 = BG Tarif
 */
public class E008 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "0".equals(value) || "1".equals(value) || "2".equals(value) || "3".equals(value) || "4".equals(value);
    }
}
