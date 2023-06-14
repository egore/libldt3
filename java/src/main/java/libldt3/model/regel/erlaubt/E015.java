package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;
import libldt3.model.regel.RegularExpressionRegel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 00 = Primärabrechnung
 * 01 = Sozialversicherungsabkommen (SVA)
 * 02 = Bundesversorgungsgesetz (BVG)
 * 03 = Bundesentschädigungsgesetz (BEG)
 * 04 = Grenzgänger (GG)
 * 05 = Rheinschiffer (RHS)
 * 06 = Sozialhilfeträger, ohne Asylstellen (SHT)
 * 07 = Bundesvertriebenengesetz (BVFG)
 * 08 = Asylstellen (AS)
 * 09 = Schwangerschaftsabbrüche
 */
public class E015 extends RegularExpressionRegel {

    public static final Pattern PATTERN = Pattern.compile("^0[0-9]$");

    public E015() {
        super(PATTERN);
    }

}
