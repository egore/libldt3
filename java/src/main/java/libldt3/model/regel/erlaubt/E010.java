package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

public class E010 implements Regel {

    @Override
    public boolean isValid(String value) {
        return "00".equals(value);
    }

}
