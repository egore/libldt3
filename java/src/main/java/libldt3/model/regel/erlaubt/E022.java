package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

import java.util.Set;

/**
 * 00 = Dummy bei eGK
 * 01 = Schleswig-Holstein
 * 02 = Hamburg
 * 03 = Bremen
 * 17 = Niedersachsen
 * 20 = Westfalen-Lippe
 * 38 = Nordrhein
 * 46 = Hessen
 * (47 = Koblenz)
 * (48 = Rheinhessen)
 * 49 = Pfalz)
 * (50 = Trier)
 * 51 = Rheinland-Pfalz
 * 52 = Baden-Württemberg
 * (55 = Nordbaden)
 * (60 = Südbaden)
 * (61 = Nordwürttemberg)
 * (62 = Südwürttemberg)
 * 71 = Bayern
 * 72 = Berlin
 * 73 = Saarland
 * 74 = KBV
 * 78 = Mecklenburg-Vorpommern
 * 83 = Brandenburg
 * 88 = Sachsen-Anhalt
 * 93 = Thüringen
 * 98 = Sachsen
 * ( ) fusioniert, teilweise aber noch in Gebrauch
 */
public class E022 implements Regel {

    Set<String> VALID = Set.of("00", "01", "02", "03", "17", "20", "38", "46", "47", "48", "49", "50", "51", "52",
            "55", "60", "61", "62", "71", "72", "73", "74", "78", "83", "88", "93", "98");

    @Override
    public boolean isValid(String value) {
        return VALID.contains(value);
    }

}
