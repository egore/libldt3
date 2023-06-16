package libldt3.parser.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ErlaubterInhalt extends Regel {

    private static String normalizeEnumValue(String value) {
        return value
                .replace('.', '_')
                .replace(" / ", "_")
                .replaceAll("([a-z])/([a-zA-Z])", "$1_$2")
                .replaceAll(" ([a-z])", "_$1")
                .replaceAll("\\([^)]+\\)", "")
                .replace("ä", "ae")
                .replace("ö", "oe")
                .replace("ü", "ue")
                .replace("Ä", "Ae")
                .replace("Ö", "Oe")
                .replace("Ü", "Ue")
                .replace("ß", "ss")
                .replace(",", "")
                .replaceAll("([A-Z])-([A-Z])", "$1_$2")
                .replace("-/", "_")
                .replace("-", "")
                .replace("“", "")
                .replace("„", "")
                .replace("*", "")
                .replace(" + ", "_und_")
                .replaceAll("([A-Z]) ([A-Z])", "$1_$2")
                .replace(" ", "")
                .replace("§", "")
                .replaceAll("[0-9]+\\)", "")
                .replaceAll("^1fach", "einfach")
                .replaceAll("^2fach", "zweifach")
                .replaceAll("^3fach", "dreifach")
                .replaceAll("^4fach", "vierfach");
    }

    public String getNormalizedPruefung() {
        return normalizeEnumValue(pruefung);
    }

    public boolean isSingle() {
        return !pruefung.contains(", ");
    }

    public static class EnumValue {
        public final String code;
        public final String value;
        public String comment;
        public final boolean isDeprecated;

        public EnumValue(String code, String value, String comment, boolean isDeprecated) {
            this.code = code;
            this.value = value;
            this.comment = comment;
            this.isDeprecated = isDeprecated;
        }
    }

    static Map<String, Map<String, String>> FALLBACK = Map.of(
            "E004", Map.of(
                    "8220", "LaborDatenpaketHeader",
                    "8221", "LaborDatenpaketAbschluss",
                    "8230", "PraxisDatenpaketHeader",
                    "8231", "PraxisDatenpaketAbschluss",
                    "8205", "Befund",
                    "8215", "Auftrag"
            ),
            "E009", Map.of(
                    "27", "Muster10",
                    "28", "Muster10A"
            )
    );

    public List<EnumValue> getMultiple() {
        List<EnumValue> result = new ArrayList<>();
        if (erlaeuterung.length() == 0) {
            for (String s : pruefung.split(", ")) {
                result.add(new EnumValue(s, FALLBACK.getOrDefault(regelnummer, Collections.emptyMap()).getOrDefault(s, "?"), null, false));
            }
        } else {
            String[] lines = erlaeuterung.toString().split("\n");
            StringBuilder code = new StringBuilder();
            StringBuilder value = new StringBuilder();
            EnumValue e = null;
            for (String line : lines) {
                if (!line.contains(" = ")) {
                    if (e != null) {
                        if (e.comment == null) {
                            e.comment = line.replace("\n", "");
                        } else if (e.comment.contains("\n")) {
                            e.comment += " " + line.replace("\n", "");
                        } else {
                            e.comment += "\n" + line.replace("\n", "");
                        }
                    }
                    continue;
                }
                boolean isDeprecated = false;
                if (line.startsWith("(") && line.endsWith(")")) {
                    isDeprecated = true;
                    line = line.substring(1, line.length() - 1);
                } else if (line.equals("49 = Pfalz)")) {
                    isDeprecated = true;
                    line = line.substring(0, line.length() - 1);
                }
                String[] split = line.split(" = ");
                code.append(split[0]);
                value.append(split[1]);
                if (line.contains(" = ")) {
                    String string = code.toString();
                    if (string.startsWith("- ")) {
                        string = string.substring(2);
                    }
                    string = string.replace("„", "").replace("“", "").replace("”", "");
                    String[] values = string.split(" oder ");
                    String normalizedValue = normalizeEnumValue(value.toString());
                    String comment = value.toString();
                    for (String v : values) {
                        e = new EnumValue(v, FALLBACK.getOrDefault(regelnummer, Collections.emptyMap()).getOrDefault(v, normalizedValue), comment.equals(normalizedValue) ? null : comment, isDeprecated);
                        result.add(e);
                    }
                    code.setLength(0);
                    value.setLength(0);
                }
            }
        }
        return result;
    }

}
