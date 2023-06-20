package libldt3.parser.model;

import libldt3.parser.generation.Normalizer;

import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Objekt {

    public static class FeldExtended {
        public Feld feld;
        public Vorkommen vorkommen = new Vorkommen();
        public String bezeichnung;
        public Feldart feldart;
        public List<Regel> regeln = new ArrayList<>();
        public String erlaeuterung;

        public String getName() {
            return Normalizer.getFieldName(bezeichnung);
        }

        public List<Regel> getFeldregeln() {
            return regeln
                    .stream()
                    .filter(regel -> !(regel instanceof Kontextregel))
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return feld.toString() + ": " + bezeichnung;
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
    public List<Objekt> children = new ArrayList<>();

    public List<Kontextregel> getKontextregeln() {
        List<Kontextregel> result = new ArrayList<>();
        for (FeldExtended feld : felder) {
            for (Regel regel : feld.regeln) {
                if (regel instanceof Kontextregel) {
                    if (!result.contains(regel)) {
                        result.add((Kontextregel) regel);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Obj_" + nummer + " " + name;
    }

    public boolean isUsingList() {
        for (var feld : felder) {
            if (feld.vorkommen.wert.equals("n")) {
                return true;
            }
        }
        for (var child : children) {
            if (child.isUsingList()) {
                return true;
            }
        }
        return false;
    }

    public TreeSet<String> getImports() {
        TreeSet<String> enums = new TreeSet<>();
        for (var feld : felder) {
            for (var regel : feld.regeln) {
                if (regel instanceof ErlaubterInhalt) {
                    enums.add("enum." + regel.regelnummer);
                } else if (regel instanceof Kontextregel) {
                    enums.add("regel.kontext." + regel.regelnummer);
                } else if (regel instanceof Formatregel) {
                    enums.add("regel." + regel.regelnummer);
                }
            }
        }
        for (var child : children) {
            enums.addAll(child.getImports());
        }
        return enums;
    }

}
