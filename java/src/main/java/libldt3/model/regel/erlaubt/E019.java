package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * M = männlich
 * W = weiblich
 * D = divers
 * X = unbestimmt
 * U = unbekannt
 */
public class E019 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "M".equals(value) || "W".equals(value) || "D".equals(value) || "X".equals(value) || "U".equals(value);
    }
}
