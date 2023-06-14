package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

/**
 * 01 = Erstveranlasser
 * 02 = Einsender Arzt
 * 03 = Einsender sonstige
 * 04 = Versicherter
 * 05 = Rechnungsempfänger
 * 06 = Bevollmächtigter
 * 07 = Laborarzt/Befundersteller
 * 08 = Leistungserbringer
 * 11 = Halter (eines Tieres)
 * 12 = Patient
 * 14 = Überweiser
 * 15 = staatliche Einrichtung
 * 16 = sonstige juristische Person
 * 17 = sonstige medizinische Einrichtung
 */
public class E023 implements Regel {

    Set<String> VALID = Set.of("01", "02", "03", "04", "05", "06", "07", "08", "11", "12", "14", "15", "16", "17");

    @Override
    public boolean isValid(String value) {
        return VALID.contains(value);
    }

}
