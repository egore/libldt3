package libldt3.parser.generation;

import java.util.regex.Pattern;

public class Normalizer {

    private static final Pattern PATTERN_LOW_END_LOW_START = Pattern.compile("([a-z])[/ ]+([a-z])");
    private static final Pattern PATTERN_UPPER = Pattern.compile("([A-Z]+)");

    private static String cleanup(String value) {
        return value
                // replace umlauts
                .replace("ä", "ae")
                .replace("ö", "oe")
                .replace("ü", "ue")
                .replace("Ä", "Ae")
                .replace("Ö", "Oe")
                .replace("Ü", "Ue")
                .replace("ß", "ss")

                // replace cash symbol
                .replace("€", "EUR")

                // remove special chars
                .replace("“", "")
                .replace("„", "")
                .replace("*", "")
                .replace("§", "")
                .replace(",", "");
    }

    public static String getEnumIdentifier(String value) {
        return cleanup(value)

                // Replace word separators by underscores
                .replace('.', '_')
                .replace(" / ", "_")
                .replace("-/", "_")

                // Rule: When a word ends in lowercase and is followed by a word starting in uppercase, combine them
                // using underscores
                .replaceAll("([a-z])/([a-zA-Z])", "$1_$2")

                // Rule: A word starting with lowercase is combined using an underscore
                .replaceAll(" ([a-z])", "_$1")

                // Rule: everything in parentheses is removed
                .replaceAll("\\([^)]+\\)", "")

                // Rule: Words ending in uppercase are assumed to be uppercase only and will be combined using
                // underscores
                .replaceAll("([A-Z])[ -]([A-Z])", "$1_$2")

                // Workaround for E167
                .replace("20-29", "ZwanzigBisNeunundzwanzig")
                .replace("30-34", "DreissigBisVierunddreissig")

                .replace("-", "")
                .replace(" + ", "_und_")
                .replace(" ", "")
                .replace("−", "_MINUS_")
                .replace("+", "_PLUS_")
                .replace("|", "_")

                // Rule: everything after a colon is removed
                .replaceAll(":(.*)$", "_$1")

                .replaceAll("[0-9]+\\)", "")
                .replaceAll("^1fach", "einfach")
                .replaceAll("^2fach", "zweifach")
                .replaceAll("^3fach", "dreifach")
                .replaceAll("^4fach", "vierfach")
                .replaceAll("^1$", "Eins")
                .replaceAll("^2$", "Zwei")
                .replaceAll("^3$", "Drei")
                .replaceAll("^4$", "Vier");
    }

    public static String getObjektName(String value) {
        String replace = cleanup(value)

                // Preparation: Ensure word separation is done by spaces
                .replace("_", " ")

                // Rule: When a word ends in lowercase and is followed by a word starting in uppercase, combine them
                .replaceAll("([a-z])[/ ]+([A-Z])", "$1$2");

        // Rule: When a word ends in lowercase and is followed by a word starting in lowercase, combine them
        replace = PATTERN_LOW_END_LOW_START.matcher(replace).replaceAll(match -> match.group(1) + match.group(2).toUpperCase());

        replace = replace
                // Rule: everything in parentheses is removed
                .replaceAll("\\([^)]+\\)", "")

                // Rule: Words ending in uppercase are assumed to be uppercase only and will be combined
                .replaceAll("([A-Z])[ -]([A-Z])", "$1$2")

                // Remove word separators
                .replace(".", "_")
                .replace("-/", "_")
                .replace("-", "")
                .replace("–", "")
                .replace("?", "")
                .replace(" ", "")
                .replace(":", "")
                .replace("/", "")

                // Workaround for parentheses in parentheses (page 167)
                .replace(")", "");

        // Workaround for ugly naming
        if ("RgEmpfaenger".equals(replace)) {
            return "Rechnungsempfaenger";
        }

        return replace;
    }

    public static String getFieldName(String value) {

        value = value.replaceAll("^ID (.*)$", "$1Id");

        // Rule: Words in uppercase only will be converted to camel case
        value = PATTERN_UPPER.matcher(value).replaceAll(match -> toUppercaseFirstOnly(match.group(1)));

        value = value.replaceAll(" des ([A-Za-z]+)s", "$1");

        value = value.replaceAll("([A-Za-z]+) auf die ([A-Za-z]+)$", "$2$1");

        value = value.replace(" der ", " ");
        value = value.replace(" im ", " ");

        value = getObjektName(value);

        value = value.substring(0, 1).toLowerCase() + value.substring(1);

        return value;
    }

    public static String toUppercaseFirst(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static String toUppercaseFirstOnly(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }

}
