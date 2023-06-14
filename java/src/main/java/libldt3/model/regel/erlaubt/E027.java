package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;
import libldt3.model.regel.RegularExpressionRegel;

import java.util.regex.Pattern;

/**
 * 01 = Erstveranlasser
 * 02 = Einsender Arzt
 * 03 = Einsender sonstige
 * 04 = Versicherter
 * 05 = Rechnungsempfänger
 * 06 = Bevollmächtigter
 * 07 = Laborarzt/Befundersteller
 * 08 = Leistungserbringer
 * 09 = Softwareverantwortlicher
 * 10 = Zusätzlicher Befundempfänger
 * 11 = Halter (eines Tieres)
 * 12 = Patient
 * 14 = Überweiser
 * 16 = sonstige juristische Person
 * 17 = Medizinisch-technische/r Assistent/in (MTA)
 * 18 = Medizinische/r Fachangestellte/r (MFA)
 */
public class E027 extends RegularExpressionRegel {

    public static final Pattern PATTERN = Pattern.compile("^(0[1-9]|1[0-246-8])$");

    public E027() {
        super(PATTERN);
    }

}
