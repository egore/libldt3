package libldt3.model.regel.erlaubt;

import libldt3.model.regel.RegularExpressionRegel;

import java.util.regex.Pattern;

/**
 * 00 = keine Angabe
 * 01 = Diabetes mellitus Typ 2
 * 02 = Brustkrebs
 * 03 = Koronare Herzkrankheit
 * 04 = Diabetes mellitus Typ 1
 * 05 = Asthma bronchiale
 * 06 = COPD (chronic obstructive pulmo-nary disease)
 * 07 = Chronische Herzinsuffizienz
 * 08 = Depression
 * 09 = Rückenschmerz
 * 10 = Rheuma
 * 11 = Osteoporose
 */
public class E020 extends RegularExpressionRegel {

    public static final Pattern PATTERN = Pattern.compile("^(0[0-9]|1[0-1])$");

    public E020() {
        super(PATTERN);
    }

}
