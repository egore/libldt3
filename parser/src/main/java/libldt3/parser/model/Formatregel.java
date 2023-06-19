package libldt3.parser.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatregel extends Regel {

    private static final Pattern PATTERN = Pattern.compile("(JJJJ|MM|TT|ms|P|n+)");

    public String getRegexp() {
        String pruefung = this.pruefung;

        pruefung = pruefung.replace("hh", "([0-1][0-9]|2[0-3])");
        pruefung = pruefung.replace("mm", "([0-5][0-9])");
        pruefung = pruefung.replace("ss", "([0-5][0-9])");
        pruefung = pruefung.replace("(ms)", "([0-9]{3})?");
        pruefung = pruefung.replace("a", "([A-Z])");

        // Replace 'nnn' etc. by [0-9]{3}
        {
            StringBuilder result = new StringBuilder();
            Matcher matcher = PATTERN.matcher(pruefung);
            while (matcher.find()) {
                int length = matcher.group(1).length();
                StringBuilder buffer = new StringBuilder();
                if (length != pruefung.length()) {
                    buffer.append('(');
                }
                if (length > 1) {
                    buffer.append("[0-9]{").append(length).append("}");
                }
                else {
                    buffer.append("[0-9]");
                }
                if (length != pruefung.length()) {
                    buffer.append(')');
                }
                matcher.appendReplacement(result, buffer.toString());
            }
            matcher.appendTail(result);
            pruefung = result.toString();
        }

        if ("JJJJMMTT".equals(pruefung)) {
            return "^[0-9]{4}(0[1-9]|1[012])(0[1-9]|[1-2][0-9]|3[01])$";
        }
        return "^" + pruefung + "$";
    }

}
