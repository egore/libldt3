package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 0 = nicht nachweisbar / kein Wachstum
 * 1 = spärlich,
 * 2 = mäßig/vereinzelt,
 * 3 = reichlich,
 * 4 = massenhaft
 */
public class E026 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "0".equals(value) || "1".equals(value) || "2".equals(value) || "3".equals(value) || "4".equals(value);
    }
}
