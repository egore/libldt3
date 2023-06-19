package libldt3.parser.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kontextregel extends Regel {

    private static final Pattern MANDATORY_PATTERN = Pattern.compile("^Entweder FK ([0-9]{4}) oder FK ([0-9]{4}) ist vorhanden.$");

    public List<Feld> getMandatoryFields() {
        Matcher matcher = MANDATORY_PATTERN.matcher(pruefung);
        if (matcher.matches()) {
            return Arrays.asList(new Feld(matcher.group(1)), new Feld(matcher.group(2)));
        }
        return Collections.emptyList();
    }

}
