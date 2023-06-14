package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 1 = Abrechnung Laborfacharzt
 * 2 = Abrechnung Privat-LG
 */
public class E024 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "1".equals(value) || "2".equals(value);
    }
}
