package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Zulässige UKV/OKV-Kennungen in den Arztnummern und Knappschaftskennung
 */
public class E014 implements Regel {

    Set<String> VALID;

    public E014() {
        Set<String> valid = new HashSet<>();
        valid.add("01");
        valid.add("02");
        valid.add("03");
        for (int i = 6; i <= 21; i++) {
            valid.add(String.format("%02d", i));
        }
        valid.add("24");
        valid.add("25");
        valid.add("27");
        valid.add("28");
        valid.add("23");
        for (int i = 37; i <= 73; i++) {
            valid.add(String.format("%02d", i));
        }
        valid.add("78");
        valid.add("79");
        valid.add("80");
        valid.add("81");
        valid.add("83");
        valid.add("85");
        valid.add("86");
        valid.add("87");
        valid.add("88");
        valid.add("93");
        valid.add("94");
        valid.add("95");
        valid.add("96");
        valid.add("98");
        valid.add("99");
        VALID = Collections.unmodifiableSet(valid);
    }

    @Override
    public boolean isValid(String value) {
        return VALID.contains(value);
    }

}
