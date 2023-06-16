package libldt3.parser.model;

import java.util.ArrayList;
import java.util.List;

public class ErlaubterInhalt extends Regel {

    private static String normalizeEnumValue(String value) {
        return value
                .replace('.', '_')
                .replace(" / ", "_")
                .replaceAll("([a-z])/([a-z])", "$1_$2")
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
                .replace("-/", "_")
                .replace("-", "")
                .replace("“", "")
                .replace("*", "")
                .replace(" ", "");
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
        public final String comment;
        public final boolean isDeprecated;

        public EnumValue(String code, String value, String comment, boolean isDeprecated) {
            this.code = code;
            this.value = value;
            this.comment = comment;
            this.isDeprecated = isDeprecated;
        }
    }

    public List<EnumValue> getMultiple() {
        String[] lines = erlaeuterung.toString().split("\n");
        StringBuilder code = new StringBuilder();
        StringBuilder value = new StringBuilder();
        List<EnumValue> result = new ArrayList<>();
        for (String line : lines) {
            if (!line.contains(" = ")) {
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
                    result.add(new EnumValue(v, normalizedValue, comment.equals(normalizedValue) ? null : comment, isDeprecated));
                }
                code.setLength(0);
                value.setLength(0);
            }
        }
        return result;
    }

}
