package libldt3.parser.model;

import libldt3.parser.RegelNaming;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Feld {

    private static final Pattern PATTERN_NUMBER = Pattern.compile("^[0-9]+$");
    private static final Pattern PATTERN_MIN_MAX = Pattern.compile("^([0-9]+) *[0-9,≤–-]+ *([0-9]+)$");
    private static final Pattern PATTERN_MIN_MID_MAX = Pattern.compile("^([0-9]+), *[0-9]+, *([0-9]+)$");
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

    public Feld() {
    }

    public Feld(String fk) {
        this.fk = fk;
    }

    public String getTyp() {
        switch (format) {
            case num:
                for (Regel regel : regeln) {
                    // Workaround field 3103
                    if (regel.regelnummer.equals("F003")) {
                        return "LocalDate";
                    }
                    // Number fields with format rules are not actually numbers, but strings consisting of numbers only
                    if (regel.regelnummer.startsWith("F")) {
                        return "String";
                    }
                    if (regel.regelnummer.startsWith("E")) {
                        return RegelNaming.REPLACEMENTS.getOrDefault(regel.regelnummer, "String");
                    }
                }
                // Number fields with length are not necessary numbers, treat them as Strings
                if (getMinLaenge() != null || getMaxLaenge() != null || getLaenge() != null) {
                    return "String";
                }
                return "int";
            case f: return "float";
            case alnum:
                for (Regel regel : regeln) {
                    // Number fields with format rules are not actually numbers, but strings consisting of numbers only
                    if (regel.regelnummer.startsWith("F")) {
                        return "String";
                    }
                    if (regel.regelnummer.startsWith("E")) {
                        return RegelNaming.REPLACEMENTS.getOrDefault(regel.regelnummer, "String");
                    }
                }
                return "String";
            case date: return "LocalDate";
            default: return "?";
        }
    }

    public Integer getMinLaenge() {
        if ("var".equals(laenge)) {
            return null;
        }
        // Just length, no boundaries
        if (PATTERN_NUMBER.matcher(laenge).matches()) {
            return null;
        }
        Matcher matcher = PATTERN_MIN_MAX.matcher(laenge);
        if (matcher.matches()) {
            return Integer.valueOf(matcher.group(1));
        }
        matcher = PATTERN_MIN_MID_MAX.matcher(laenge);
        if (matcher.matches()) {
            return Integer.valueOf(matcher.group(1));
        }
        return null;
    }

    public Integer getMaxLaenge() {
        if ("var".equals(laenge)) {
            return null;
        }
        // Just length, no boundaries
        if (PATTERN_NUMBER.matcher(laenge).matches()) {
            return null;
        }
        Matcher matcher = PATTERN_MIN_MAX.matcher(laenge);
        if (matcher.matches()) {
            return Integer.valueOf(matcher.group(2));
        }
        matcher = PATTERN_MIN_MID_MAX.matcher(laenge);
        if (matcher.matches()) {
            return Integer.valueOf(matcher.group(2));
        }
        matcher = PATTERN_MAX.matcher(laenge);
        if (matcher.matches()) {
            return Integer.valueOf(matcher.group(1));
        }
        return null;
    }

    public String getLaenge() {
        if ("var".equals(laenge)) {
            return null;
        }
        return laenge;
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
