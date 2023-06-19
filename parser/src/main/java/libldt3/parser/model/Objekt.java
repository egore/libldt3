package libldt3.parser.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Objekt {

    public static class FeldExtended {
        public Feld feld;
        public Vorkommen vorkommen = new Vorkommen();
        public String bezeichnung;
        public Feldart feldart;
        public List<Regel> regeln = new ArrayList<>();
        public String erlaeuterung;

        public String getName() {
            String lowercased;
            if (bezeichnung.matches("^[A-Z]+")) {
                lowercased = bezeichnung.toLowerCase();
            } else {
                lowercased = bezeichnung.substring(0, 1).toLowerCase() + bezeichnung.substring(1);
            }
            return ErlaubterInhalt.normalizeJavaIdentifier(lowercased);
        }
    }

    public static class Vorkommen {
        public int position;
        public String wert;
    }

    public enum Feldart {
        M("muss"), m("bedingt_muss"), K("kann"), k("bedingt_kann");

        private final String readable;

        Feldart(String readable) {
            this.readable = readable;
        }

        public String readable() {
            return readable;
        }
    }

    public Objekt(String nummer, String name, boolean stub) {
        this.nummer = nummer;
        this.name = name;
        this.stub = stub;
    }

    public String name;
    public String beschreibung;
    public String nummer;
    public List<FeldExtended> felder = new ArrayList<>();
    public boolean stub;

    @Override
    public String toString() {
        return "Obj_" + nummer + " " + name;
    }
}
