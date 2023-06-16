package libldt3.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RegelNaming {
    public static Map<String, String> REPLACEMENTS;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("E001", "LdtVersion");
        map.put("E002", "Versichertenart");
        // E003 -> rule, not enum
        map.put("E004", "Satzart");
        map.put("E005", "Grenzwertindikator");
        map.put("E006", "Auftragsstatus");
        map.put("E007", "TestStatus");
        map.put("E008", "Gebuehrenordnung");
        map.put("E009", "Scheinuntergruppe");
        // E010 -> rule, not enum
        // E011 -> Boolean
        // E012 -> rule, not enum
        map.put("E013", "ZusaetzlicherBefundweg");
        // E014 -> unused
        map.put("E015", "KostentraegerAbrechnungsbereich");
        map.put("E016", "KeimArt");
        map.put("E017", "Privattarif");
        map.put("E018", "GeschlechtNormalbereich");
        map.put("E019", "Geschlecht");
        map.put("E020", "DmpKennzeichnung");
        map.put("E021", "BesonderePersonengruppe");
        map.put("E022", "WOP");
        map.put("E023", "Einsenderstatus");
        map.put("E024", "AbrechnungsartPkv");
        map.put("E025", "Sensitivitaet");
        map.put("E026", "Wachstum");
        map.put("E027", "StatusPerson");
        // FIXME: E028 not supported yet
        map.put("E029", "StatusRechnungsempfaenger");
        map.put("E030", "ResistenzInterpretation");
        // E031 -> rule, not enum
        map.put("E032", "Dringlichkeit");
        map.put("E033", "Materialart");
        map.put("E034", "OrganischesMaterial");
        map.put("E035", "AnorganischesMaterial");
        // E036 -> "empty allowed" rule
        map.put("E037", "Diagnosesicherheit");
        map.put("E038", "Lokalisation");
        map.put("E039", "Behandlungsanlass");
        // E040 -> Boolean
        map.put("E041", "EinschreibestatusSelektivvertraege");
        map.put("E042", "Adresstyp");
        map.put("E044", "Dokumentenquelle");
        // E043 -> does not exist
        map.put("E045", "StatusDringlichkeit");
        map.put("E046", "Betriebsstaettenstatus");
        map.put("E047", "ArztTypId");
        // E048 -> Boolean?
        map.put("E049", "Benachrichtigungsgrund");
        map.put("E050", "Abrechnungsinfo");
        map.put("E051", "Laborart");
        map.put("E052", "Normwertspezifikation");
        map.put("E053", "Dokumententyp");
        map.put("E054", "Antikoerpersuchtest");
        map.put("E055", "DirekterCoombstest");
        map.put("E056", "AnforderungNothilfepass");
        map.put("E057", "KatalogIdAnforderbareLeistungen");
        map.put("E058", "DarstellungErgebniswerte");
        map.put("E059", "ResistenzMethode");
        map.put("E060", "EndozervikaleZellen");
        map.put("E061", "NachkontrollGrund");
        map.put("E062", "Ergebnis");
        map.put("E063", "Ergebnis2");
        map.put("E064", "ErgebnisStatus");
        map.put("E065", "ResistenzNach");
        // FIXME: E066 not supported yet
        map.put("E067", "DatensatzAbsender");
        map.put("E068", "Zeiteinheit");
        map.put("E069", "Nachweisverfahren");
        map.put("E070", "EinheitMesswert");
        map.put("E146", "Untersuchungsanlass");
        map.put("E147", "SpezifizierungVeranlassungsgrund");
        map.put("E156", "MedikationsStatus");
        REPLACEMENTS = Collections.unmodifiableMap(map);
    }
}
