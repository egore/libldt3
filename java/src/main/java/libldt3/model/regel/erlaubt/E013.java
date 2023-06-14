package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

/**
 * 0 = Papier
 * 1 = Telefon
 * 2 = Fax
 * 3 = E-Mail
 * 4 = DFÜ
 * 5 = Tourpost
 * 6 = KV-Connect
 */
public class E013 implements Regel {

    Set<String> VALID = Set.of("0", "1", "2", "3", "4", "5", "6");

    @Override
    public boolean isValid(String value) {
        return VALID.contains(value);
    }

}
