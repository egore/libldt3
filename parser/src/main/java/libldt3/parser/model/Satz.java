package libldt3.parser.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Satz {
    public String nummer;
    public String name;
    public String fullname;
    public List<Objekt.FeldExtended> felder = new ArrayList<>();

    public Satz(String nummer, String name) {
        this.nummer = nummer;
        this.name = name;
    }

    public boolean isUsingList() {
        for (var feld : felder) {
            if (feld.vorkommen.wert.equals("n")) {
                return true;
            }
        }
        return false;
    }

    public TreeSet<String> getImports() {
        TreeSet<String> imports = Objekt.getImports(felder);
        for (var feld : felder) {
            if (feld.forcedTyp != null) {
                imports.add("objekte." + feld.forcedTyp.name);
            }
        }
        return imports;
    }
}
