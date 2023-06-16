package libldt3.parser.model;

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

    public String typ;
}
