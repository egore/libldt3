package libldt3.parser.model;

public abstract class Regel {

    public enum Kategorie {
        KBV,
        Basis,
        SV
    }

    public enum Fehlerstatus {
        F, W
    }

    /**
     * e.g. F001
     */
    public String regelnummer;
    public Kategorie kategorie;
    public Fehlerstatus fehlerstatus;
    public String pruefung;
    public StringBuilder erlaeuterung = new StringBuilder();

    @Override
    public String toString() {
        return '\'' + regelnummer + "' '" + pruefung + '\'';
    }
}

