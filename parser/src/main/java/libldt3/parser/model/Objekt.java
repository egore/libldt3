package libldt3.parser.model;

import libldt3.parser.RegelNaming;
import libldt3.parser.generation.Normalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Objekt {

    public static class FeldExtended {
        public Feld feld;
        public Vorkommen vorkommen = new Vorkommen();
        public String bezeichnung;
        public Feldart feldart;
        public List<Regel> regeln = new ArrayList<>();
        public String erlaeuterung;
        public Objekt forcedTyp;

        public String getName() {
            return Normalizer.getFieldName(bezeichnung);
        }

        public List<Regel> getFeldregeln() {
            List<Regel> result = new ArrayList<>();
            for (var regel : feld.regeln) {
                if (!(regel instanceof Kontextregel)) {
                    // Skip actual enums which enforce naming themselves
                    if (regel instanceof ErlaubterInhalt) {
                        if (RegelNaming.REPLACEMENTS.containsKey(regel.regelnummer) ||
                                RegelNaming.SKIPPERS.contains(regel.regelnummer)) {
                            continue;
                        }
                    }
                    if (!result.contains(regel)) {
                        result.add(regel);
                    }
                }
            }
            for (var regel : regeln) {
                if (!(regel instanceof Kontextregel)) {
                    // Skip actual enums which enforce naming themselves
                    if (regel instanceof ErlaubterInhalt) {
                        if (RegelNaming.REPLACEMENTS.containsKey(regel.regelnummer) ||
                                RegelNaming.SKIPPERS.contains(regel.regelnummer)) {
                            continue;
                        }
                    }
                    if (!result.contains(regel)) {
                        result.add(regel);
                    }
                }
            }
            return result;
        }

        @Override
        public String toString() {
            return feld.toString() + ": " + bezeichnung;
        }

        public String getTyp() {
            if (forcedTyp != null) {
                return forcedTyp.name;
            }
            return feld.getTyp();
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
    public String nameOverride;

    public TreeSet<Kontextregel> getKontextregeln() {
        TreeSet<Kontextregel> result = new TreeSet<>();
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

    public boolean isUsingLocalDate() {
        for (var feld : felder) {
            if (feld.getTyp().equals("LocalDate")) {
                return true;
            }
        }
        for (var child : children) {
            if (child.isUsingLocalDate()) {
                return true;
            }
        }
        return false;
    }

    public TreeSet<String> getImports() {
        TreeSet<String> imports = getImports(felder);
        for (var child : children) {
            imports.addAll(child.getImports());
        }
        return imports;
    }

    static TreeSet<String> getImports(List<FeldExtended> felder) {
        TreeSet<String> imports = new TreeSet<>();
        for (var feld : felder) {
            for (var regel : feld.regeln) {
                if (regel instanceof ErlaubterInhalt) {
                    if (!RegelNaming.REPLACEMENTS.containsKey(regel.regelnummer)) {
                        if (!RegelNaming.SKIPPERS.contains(regel.regelnummer)) {
                            imports.add("regel.erlaubt." + regel.regelnummer);
                        }
                    } else {
                        String s = RegelNaming.REPLACEMENTS.get(regel.regelnummer);
                        if (!"Boolean".equals(s)) {
                            imports.add("enums." + s);
                        }
                    }
                } else if (regel instanceof Kontextregel) {
                    imports.add("regel.kontext." + regel.regelnummer);
                } else if (regel instanceof Formatregel) {
                    imports.add("regel.format." + regel.regelnummer);
                }
            }
            for (var regel : feld.feld.regeln) {
                if (regel instanceof ErlaubterInhalt) {
                    if (!RegelNaming.REPLACEMENTS.containsKey(regel.regelnummer)) {
                        if (!RegelNaming.SKIPPERS.contains(regel.regelnummer)) {
                            imports.add("regel.erlaubt." + regel.regelnummer);
                        }
                    } else {
                        String s = RegelNaming.REPLACEMENTS.get(regel.regelnummer);
                        if (!"Boolean".equals(s)) {
                            imports.add("enums." + s);
                        }
                    }
                } else if (regel instanceof Formatregel) {
                    imports.add("regel.format." + regel.regelnummer);
                }
            }
        }
        return imports;
    }

}
