package libldt3.parser.generation;

public class Normalizer {

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

                .replace("-", "")
                .replace(" + ", "_und_")
                .replace(" ", "")

                .replaceAll("[0-9]+\\)", "")
                .replaceAll("^1fach", "einfach")
                .replaceAll("^2fach", "zweifach")
                .replaceAll("^3fach", "dreifach")
                .replaceAll("^4fach", "vierfach");
    }

    public static String getObjektName(String value) {
        return cleanup(value)

                // Preparation: Ensure word separation is done by spaces
                .replace("_", " ")

                // Rule: When a word ends in lowercase and is followed by a word starting in uppercase, combine them
                .replaceAll("([a-z])[/ ]+([a-zA-Z])", "$1$2")

                // Rule: everything in parentheses is removed
                .replaceAll("\\([^)]+\\)", "")

                // Rule: Words ending in uppercase are assumed to be uppercase only and will be combined
                .replaceAll("([A-Z])[ -]([A-Z])", "$1$2")

                // Remove word separators
                .replace(".", "_")
                .replace("-/", "_")
                .replace("-", "")
                .replace(" ", "");
    }

    public static String getFieldName(String value) {


        if (value.matches("^[A-Z]+$")) {
            value = value.toLowerCase();
        } else {
            value = value.replaceAll("^ID (.*)$", "$1Id");
            value = value.substring(0, 1).toLowerCase() + value.substring(1);
        }

        value = value
                .replaceAll(" des ([A-Za-z]+)s", "$1");

        return getObjektName(value);
    }

}
