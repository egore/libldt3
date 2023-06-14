package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

public class E001 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "LDT3.2.15".equals(value);
    }
}
