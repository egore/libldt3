package libldt3.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegelNaming {
    public static Map<String, String> REPLACEMENTS;
    public static Set<String> SKIPPERS;

    static {
        Map<String, String> replacements = new HashMap<>();
        replacements.put("E001", "LdtVersion");
        replacements.put("E002", "Versichertenart");
        // E003 -> rule, not enum
        replacements.put("E004", "Satzart");
        replacements.put("E005", "Grenzwertindikator");
        replacements.put("E006", "Auftragsstatus");
        replacements.put("E007", "TestStatus");
        replacements.put("E008", "Gebuehrenordnung");
        replacements.put("E009", "Scheinuntergruppe");
        // E010 -> rule, not enum
        replacements.put("E011", "Boolean");
        // E012 -> rule, not enum
        replacements.put("E013", "ZusaetzlicherBefundweg");
        // E014 -> unused
        replacements.put("E015", "KostentraegerAbrechnungsbereich");
        replacements.put("E016", "KeimArt");
        replacements.put("E017", "Privattarif");
        replacements.put("E018", "GeschlechtNormalbereich");
        replacements.put("E019", "Geschlecht");
        replacements.put("E020", "DmpKennzeichnung");
        replacements.put("E021", "BesonderePersonengruppe");
        replacements.put("E022", "WOP");
        replacements.put("E023", "Einsenderstatus");
        replacements.put("E024", "AbrechnungsartPkv");
        replacements.put("E025", "Sensitivitaet");
        replacements.put("E026", "Wachstum");
        replacements.put("E027", "StatusPerson");
        // FIXME: E028 not supported yet
        replacements.put("E029", "StatusRechnungsempfaenger");
        replacements.put("E030", "ResistenzInterpretation");
        // E031 -> rule, not enum
        replacements.put("E032", "Dringlichkeit");
        replacements.put("E033", "Materialart");
        replacements.put("E034", "OrganischesMaterial");
        replacements.put("E035", "AnorganischesMaterial");
        // E036 -> "empty allowed" rule
        replacements.put("E037", "Diagnosesicherheit");
        replacements.put("E038", "Lokalisation");
        replacements.put("E039", "Behandlungsanlass");
        // E040 -> Boolean
        replacements.put("E041", "EinschreibestatusSelektivvertraege");
        replacements.put("E042", "Adresstyp");
        replacements.put("E044", "Dokumentenquelle");
        // E043 -> does not exist
        replacements.put("E045", "StatusDringlichkeit");
        replacements.put("E046", "Betriebsstaettenstatus");
        replacements.put("E047", "ArztTypId");
        replacements.put("E048", "Boolean");
        replacements.put("E049", "Benachrichtigungsgrund");
        replacements.put("E050", "Abrechnungsinfo");
        replacements.put("E051", "Laborart");
        replacements.put("E052", "Normwertspezifikation");
        replacements.put("E053", "Dokumententyp");
        replacements.put("E054", "Antikoerpersuchtest");
        replacements.put("E055", "DirekterCoombstest");
        replacements.put("E056", "AnforderungNothilfepass");
        replacements.put("E057", "KatalogIdAnforderbareLeistungen");
        replacements.put("E058", "DarstellungErgebniswerte");
        replacements.put("E059", "ResistenzMethode");
        replacements.put("E060", "EndozervikaleZellen");
        replacements.put("E061", "NachkontrollGrund");
        replacements.put("E062", "Ergebnis");
        replacements.put("E063", "Ergebnis2");
        replacements.put("E064", "ErgebnisStatus");
        replacements.put("E065", "ResistenzNach");
        // FIXME: E066 not supported yet
        replacements.put("E067", "DatensatzAbsender");
        replacements.put("E068", "Zeiteinheit");
        replacements.put("E069", "Nachweisverfahren");
        replacements.put("E070", "EinheitMesswert");
        replacements.put("E146", "Untersuchungsanlass");
        replacements.put("E147", "SpezifizierungVeranlassungsgrund");
        replacements.put("E156", "MedikationsStatus");
        // E157 -> Checksum
        replacements.put("E163", "Zeitzone");
        replacements.put("E164", "Fachgebiet");
        replacements.put("E165", "TierGeschlecht");
        replacements.put("E166", "KastriertSterilisiert");
        replacements.put("E167", "Alterskategorie");
        replacements.put("E168", "HpvImpfung");
        replacements.put("E169", "HpvHrTestergebnis");
        replacements.put("E171", "Testungen");
        replacements.put("E172", "Beauftragungsgrund");
        replacements.put("E173", "BetroffeneEinrichtung");
        replacements.put("E174", "CovidBeauftragung");
        replacements.put("E175", "TestungRechtsgrundlage");
        replacements.put("E176", "KlinischerBefund");
        replacements.put("E177", "Auftragsart");
        replacements.put("E178", "Auftrag");
        replacements.put("E179", "HpvTyp1618");
        replacements.put("E180", "Bestaetigungsdiagnostik");
        replacements.put("E181", "Virusvariantendiagnostik");
        REPLACEMENTS = Collections.unmodifiableMap(replacements);

        Set<String> skippers = new HashSet<>();
        for (int i = 71; i <= 145; i++) {
            skippers.add(String.format("E%03d", i));
        }
        for (int i = 149; i <= 155; i++) {
            skippers.add(String.format("E%03d", i));
        }
        for (int i = 158; i <= 162; i++) {
            skippers.add(String.format("E%03d", i));
        }
        skippers.add("E170");
        SKIPPERS = Collections.unmodifiableSet(skippers);
    }
}
