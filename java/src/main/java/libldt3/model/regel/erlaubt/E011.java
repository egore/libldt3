package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * 1 = ja
 */
public class E011 implements Regel {

    @Override
    public boolean isValid(String value) {
        return "1".equals(value);
    }

}
