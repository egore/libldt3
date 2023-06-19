package libldt3.parser.model;

import libldt3.parser.RegelNaming;

import java.util.ArrayList;
import java.util.List;

public class Feld {

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
                        return RegelNaming.REPLACEMENTS.getOrDefault(regel.regelnummer, "UNKNOWN");
                    }
                }
                return "int";
            case f: return "float";
            case alnum: return "String";
            case date: return "LocalDate";
            default: return "?";
        }
    }

}
