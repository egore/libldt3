package libldt3.parser.model;

import libldt3.parser.RegelNaming;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Feld {

    private static final Pattern PATTERN_NUMBER = Pattern.compile("^[0-9]+$");
    private static final Pattern PATTERN_MIN_MAX = Pattern.compile("^([0-9]+) *[,≤] *([0-9]+)$");
    private static final Pattern PATTERN_MAX = Pattern.compile("^ *[,≤] *([0-9]+)$");

    public enum Format {
        alnum, num, date, f
    }

    /** e.g. "0001" */
    public String fk;
    public String inhalt;
    public String laenge;
    public Format format;
    public List<Regel> regeln = new ArrayList<>();
    public String inhalte;
    public Objekt forcedTyp;

    public Feld() {
    }

    public Feld(String fk) {
        this.fk = fk;
    }

    public String getTyp() {
        if (forcedTyp != null) {
            return forcedTyp.name;
        }
        switch (format) {
            case num:
                for (Regel regel : regeln) {
                    if (regel.regelnummer.startsWith("E")) {
                        return RegelNaming.REPLACEMENTS.getOrDefault(regel.regelnummer, "String");
                    }
                }
                return "int";
            case f: return "float";
            case alnum: return "String";
            case date: return "LocalDate";
            default: return "?";
        }
    }

    public Integer getMinLaenge() {
        // Just length, no boundaries
        if (PATTERN_NUMBER.matcher(laenge).matches()) {
            return null;
        }
        Matcher matcher = PATTERN_MIN_MAX.matcher(laenge);
        if (matcher.matches()) {
            return Integer.valueOf(matcher.group(1));
        }
        return null;
    }

    public Integer getMaxLaenge() {
        // Just length, no boundaries
        if (PATTERN_NUMBER.matcher(laenge).matches()) {
            return null;
        }
        Matcher matcher = PATTERN_MIN_MAX.matcher(laenge);
        if (matcher.matches()) {
            return Integer.valueOf(matcher.group(2));
        }
        matcher = PATTERN_MAX.matcher(laenge);
        if (matcher.matches()) {
            return Integer.valueOf(matcher.group(1));
        }
        return null;
    }

    @Override
    public String toString() {
        return "Feld[" + fk + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feld feld = (Feld) o;
        return Objects.equals(fk, feld.fk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fk);
    }
}
