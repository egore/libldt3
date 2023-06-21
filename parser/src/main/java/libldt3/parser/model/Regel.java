package libldt3.parser.model;

public abstract class Regel implements Comparable<Regel> {

    public enum Kategorie {
        KBV,
        Basis,
        SV
    }

    public enum Fehlerstatus {
        F, W, I
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

    @Override
    public int compareTo(Regel o) {
        return this.regelnummer.compareTo(o.regelnummer);
    }
}

