package libldt3.model.regel.erlaubt;

import libldt3.model.regel.RegularExpressionRegel;

import java.util.regex.Pattern;

/**
 * 002-999
 */
public class E003 extends RegularExpressionRegel {

    public static final Pattern PATTERN = Pattern.compile("^0(0[2-9]|[1-9][0-9])$");

    public E003() {
        super(PATTERN);
    }

}
