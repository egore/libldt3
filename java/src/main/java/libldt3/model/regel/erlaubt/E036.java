package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;
import libldt3.model.regel.RegularExpressionRegel;

import java.util.regex.Pattern;

/**
 * Feld kann ohne Inhalt übertragen werden.
 *
 * Damit wird die Formatierung von zu übertragenden Texten mit Leerzeilen ermöglicht.
 */
public class E036 implements Regel {

    @Override
    public boolean isValid(String value) {
        return true;
    }
}
