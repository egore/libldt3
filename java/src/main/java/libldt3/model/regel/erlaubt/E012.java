package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

public class E012 implements Regel {

    @Override
    public boolean isValid(String value) {
        return Integer.parseInt(value) > 0;
    }

}
