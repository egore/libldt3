package libldt3.parser.parsing;

import libldt3.parser.RegelNaming;
import libldt3.parser.generation.Normalizer;
import libldt3.parser.model.ErlaubterInhalt;
import libldt3.parser.model.Feld;
import libldt3.parser.model.Formatregel;
import libldt3.parser.model.Kontextregel;
import libldt3.parser.model.Objekt;
import libldt3.parser.model.Regel;
import libldt3.parser.model.Satz;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final Logger LOG = LoggerFactory.getLogger(Parser.class);

    private static final Pattern OBJEKT_HEADLINE_PATTERN = Pattern.compile("^[0-9.]+[ ]+Obj_([A-Za-zÄÖÜäöüß()0-9 /-]+) „Obj_([0-9]+)[“\"]$");
    private static final Pattern OBJEKT_PATTERN = Pattern.compile("Obj_([0-9]{4}).*");
    private static final Pattern SATZ_HEADLINE_PATTERN = Pattern.compile("^Satzart: ([A-Za-zÄÖÜäöüß()0-9 /-]+) „([0-9]+)“$");

    static class Column {
        public List<Column> subcolumns;
        float x = 0.0f;
        float y = 0.0f;

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    enum States {
        NAME,
        HEADER,
        DESCRIPTION, BODY
    }

    static class State {
        public States current;

        public State(States initial) {
            current = initial;
        }

        public void to(States newState) {
            LOG.trace("Switching state from {} to {}", current, newState);
            current = newState;
        }
    }

    static class Holder<T> {
        T value;

        public Holder(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value != null ? value.toString() : "null";
        }
    }

    public static class ParseResult {
        public Map<String, Regel> regeln = new HashMap<>();
        public Map<String, Feld> felder = new HashMap<>();
        public Map<String, Objekt> objekte = new HashMap<>();
        public Map<String, Satz> saetze = new HashMap<>();
    }

    public static ParseResult parse(String filePath) throws IOException {
        var result = new ParseResult();
        Map<String, Regel> regeln = result.regeln;
        Map<String, Feld> felder = result.felder;
        Map<String, Objekt> objekte = result.objekte;
        Map<String, Satz> saetze = result.saetze;

        try (PDDocument document = Loader.loadPDF(new File(filePath))) {

            // Erlaubte Inhalte
            {
                var state = new State(States.HEADER);
                Holder<String> lastText = new Holder<>(null);
                Holder<Regel> currentRegel = new Holder<>(new ErlaubterInhalt());

                List<Column> columns = Arrays.asList(new Column(), new Column(), new Column(), new Column(), new Column());
                final Holder<Integer> lastColumn = new Holder<>(-1);

                PDFTextStripper stripper = new PDFTextStripper() {
                    @Override
                    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                        StringBuilder buffer = new StringBuilder();
                        float lastX = Float.NEGATIVE_INFINITY;
                        float firstX = 0.0f;
                        float firstY = 0.0f;
                        for (TextPosition p : textPositions) {
                            // If too much space is between the words, split them into two text blocks
                            if (p.getX() - lastX > 8.0f) {
                                if (!buffer.isEmpty()) {
                                    handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, ErlaubterInhalt::new);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (!buffer.isEmpty()) {
                            handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, ErlaubterInhalt::new);
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(78);
                stripper.setEndPage(100);
                stripper.getText(document);
            }

            // Kontextregeln
            {
                var state = new State(States.HEADER);
                Holder<String> lastText = new Holder<>(null);
                Holder<Regel> currentRegel = new Holder<>(new Kontextregel(felder, regeln));

                List<Column> columns = Arrays.asList(new Column(), new Column(), new Column(), new Column(), new Column());
                final Holder<Integer> lastColumn = new Holder<>(-1);

                PDFTextStripper stripper = new PDFTextStripper() {
                    @Override
                    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                        StringBuilder buffer = new StringBuilder();
                        float lastX = Float.NEGATIVE_INFINITY;
                        float firstX = 0.0f;
                        float firstY = 0.0f;
                        for (TextPosition p : textPositions) {
                            // If too much space is between the words, split them into two text blocks
                            if (p.getX() - lastX > 8.0f) {
                                if (!buffer.isEmpty()) {
                                    handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, () -> new Kontextregel(felder, regeln));
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (!buffer.isEmpty()) {
                            handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, () -> new Kontextregel(felder, regeln));
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(102);
                stripper.setEndPage(116);
                stripper.getText(document);
            }

            // Formatregeln
            {
                var state = new State(States.HEADER);
                Holder<String> lastText = new Holder<>(null);
                Holder<Regel> currentRegel = new Holder<>(new Formatregel());

                List<Column> columns = Arrays.asList(new Column(), new Column(), new Column(), new Column(), new Column());
                final Holder<Integer> lastColumn = new Holder<>(-1);

                PDFTextStripper stripper = new PDFTextStripper() {
                    @Override
                    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                        StringBuilder buffer = new StringBuilder();
                        float lastX = Float.NEGATIVE_INFINITY;
                        float firstX = 0.0f;
                        float firstY = 0.0f;
                        for (TextPosition p : textPositions) {
                            // If too much space is between the words, split them into two text blocks
                            if (p.getX() - lastX > 8.0f) {
                                if (!buffer.isEmpty()) {
                                    handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, Formatregel::new);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (!buffer.isEmpty()) {
                            handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, Formatregel::new);
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(74);
                stripper.setEndPage(77);
                stripper.getText(document);
            }

            // Felder
            {
                var state = new State(States.HEADER);
                Holder<String> lastText = new Holder<>(null);
                Holder<Feld> currentFeld = new Holder<>(new Feld());

                List<Column> columns = Arrays.asList(new Column(), new Column(), new Column(), new Column(), new Column(), new Column());
                final Holder<Integer> lastColumn = new Holder<>(-1);

                PDFTextStripper stripper = new PDFTextStripper() {
                    @Override
                    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                        StringBuilder buffer = new StringBuilder();
                        float lastX = Float.NEGATIVE_INFINITY;
                        float firstX = 0.0f;
                        float firstY = 0.0f;
                        for (TextPosition p : textPositions) {
                            // If too much space is between the words, split them into two text blocks
                            if (p.getX() - lastX > 8.0f) {
                                if (!buffer.isEmpty()) {
                                    handleFeld(firstX, firstY, buffer.toString().trim(), columns, currentFeld, lastColumn, felder, state, lastText, regeln);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (!buffer.isEmpty()) {
                            handleFeld(firstX, firstY, buffer.toString().trim(), columns, currentFeld, lastColumn, felder, state, lastText, regeln);
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(38);
                stripper.setEndPage(73);
                stripper.getText(document);
            }

            // Objekte
            {
                var state = new State(States.NAME);
                Holder<String> lastText = new Holder<>(null);
                Holder<Objekt> currentObjekt = new Holder<>(null);
                Holder<Objekt.FeldExtended> currentFeld = new Holder<>(null);

                List<Column> columns = Arrays.asList(new Column(), new Column(), new Column(), new Column(), new Column(), new Column());
                columns.get(1).subcolumns = Arrays.asList(new Column(), new Column(), new Column(), new Column(), new Column());
                final Holder<Integer> lastColumn = new Holder<>(-1);

                PDFTextStripper stripper = new PDFTextStripper() {
                    @Override
                    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                        StringBuilder buffer = new StringBuilder();
                        float lastX = Float.NEGATIVE_INFINITY;
                        float firstX = 0.0f;
                        float firstY = 0.0f;
                        for (TextPosition p : textPositions) {
                            // If too much space is between the words, split them into two text blocks
                            if (p.getX() - lastX > 8.0f) {
                                if (!buffer.isEmpty()) {
                                    handleObjekt(firstX, firstY, buffer.toString().trim(), columns, currentObjekt, currentFeld, lastColumn, regeln, felder, objekte, state, lastText);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (!buffer.isEmpty()) {
                            handleObjekt(firstX, firstY, buffer.toString().trim(), columns, currentObjekt, currentFeld, lastColumn, regeln, felder, objekte, state, lastText);
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(117);
                stripper.setEndPage(169);
                stripper.getText(document);
                // Skip 170
                stripper.setStartPage(171);
                stripper.setEndPage(185);
                stripper.getText(document);
            }

            // Sätze
            {
                var state = new State(States.NAME);
                Holder<String> lastText = new Holder<>(null);
                Holder<Satz> currentSatz = new Holder<>(null);
                Holder<Objekt.FeldExtended> currentFeld = new Holder<>(null);

                List<Column> columns = Arrays.asList(new Column(), new Column(), new Column(), new Column(), new Column(), new Column());
                columns.get(1).subcolumns = Arrays.asList(new Column(), new Column(), new Column(), new Column());
                final Holder<Integer> lastColumn = new Holder<>(-1);

                PDFTextStripper stripper = new PDFTextStripper() {
                    @Override
                    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                        StringBuilder buffer = new StringBuilder();
                        float lastX = Float.NEGATIVE_INFINITY;
                        float firstX = 0.0f;
                        float firstY = 0.0f;
                        for (TextPosition p : textPositions) {
                            // If too much space is between the words, split them into two text blocks
                            if (p.getX() - lastX > 8.0f) {
                                if (!buffer.isEmpty()) {
                                    handleSatz(firstX, firstY, lastX, buffer.toString().trim(), columns, currentSatz, currentFeld, lastColumn, regeln, felder, saetze, objekte, state, lastText);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (!buffer.isEmpty()) {
                            handleSatz(firstX, firstY, lastX, buffer.toString().trim(), columns, currentSatz, currentFeld, lastColumn, regeln, felder, saetze, objekte, state, lastText);
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(32);
                stripper.setEndPage(37);
                stripper.getText(document);
            }
        }
        return result;
    }

    private static void handleObjekt(float x, float y, String text, List<Column> columns, Holder<Objekt> objekt,
                                     Holder<Objekt.FeldExtended> currentFeld, Holder<Integer> lastColumn, Map<String, Regel> regeln,
                                     Map<String, Feld> felder, Map<String, Objekt> objekte, State state, Holder<String> lastText) {
        if (text.isEmpty()) {
            return;
        }
        if (text.matches("Seite [0-9]+ von [0-9]+")) {
            return;
        }
        LOG.debug("Found text '{}' at {},{}", text, x, y);
        // Workaround: From page 143 on the header is duplicated for objekte spanning multiple pages, therefore force
        // the state to HEADER
        if (("OID: noch nicht vergeben".equals(text) ||
                "OID noch nicht vergeben".equals(text) ||
                "OID: noch nicht vergeben,".equals(text)) &&
                state.current != States.BODY) {
            state.to(States.HEADER);
        } else if (state.current == States.NAME) {
            Matcher matcher = OBJEKT_HEADLINE_PATTERN.matcher(text);
            if (matcher.matches()) {
                String nummer = matcher.group(2);
                String name = Normalizer.getObjektName(matcher.group(1));
                objekt.value = objekte.computeIfAbsent(nummer, (k) -> new Objekt(nummer, name, false));
                if ("Rechnungsempfaenger".equals(name)) {
                    objekt.value.nameOverride = "RgEmpfaenger";
                }
                objekt.value.stub = false;
                objekt.value.name = name;
                state.to(States.DESCRIPTION);
                LOG.debug("Adding objekt {}", objekt.value);
            }
        } else if (state.current == States.DESCRIPTION) {
            if (objekt.value.beschreibung == null) {
                objekt.value.beschreibung = text;
            } else if (!text.startsWith("Obj_")) {
                objekt.value.beschreibung += " " + text;
            }
        } else if (state.current == States.HEADER) {
            switch (text) {
                case "FK":
                    columns.get(0).x = x;
                    columns.get(0).y = y;
                    break;
                case "1":
                    columns.get(1).x = x;
                    columns.get(1).y = y;
                    columns.get(1).subcolumns.get(0).x = x;
                    columns.get(1).subcolumns.get(0).y = y;
                    break;
                case "2":
                    columns.get(1).subcolumns.get(1).x = x;
                    columns.get(1).subcolumns.get(1).y = y;
                    break;
                case "3":
                    columns.get(1).subcolumns.get(2).x = x;
                    columns.get(1).subcolumns.get(2).y = y;
                    break;
                case "4":
                    columns.get(1).subcolumns.get(3).x = x;
                    columns.get(1).subcolumns.get(3).y = y;
                    break;
                case "Feld-/Objektbezeichnung":
                case "Bezeichnung der Feldinhalte":
                    columns.get(2).x = x;
                    columns.get(2).y = y;
                    break;
                case "Feldart":
                    columns.get(3).x = x;
                    columns.get(3).y = y;
                    break;
                case "Regel":
                    // Adjust values: The text in the header is centered
                    columns.get(4).x = x - 0.75f;
                    columns.get(4).y = y;
                    break;
                case "Erläuterung":
                    columns.get(5).x = x;
                    columns.get(5).y = y;
                    break;
                case "5":
                    columns.get(1).subcolumns.get(4).x = x;
                    columns.get(1).subcolumns.get(4).y = y;
                    state.to(States.BODY);
                    // Adjust values: The text in the header is centered
                    columns.get(0).x -= 4.02f;
                    LOG.debug("All headers found, {}", columns);
                    break;
            }
            lastText.value = text;
        } else if (state.current == States.BODY) {
            // Workaround for page 143
            if ("FK".equals(text)) {
                state.to(States.HEADER);
                return;
            }
            int column = determineColumn(columns, x);
            LOG.debug("Setting {} in column {}", text, column);

            switch (column) {
                case 0:
                    if ("8003".equals(text)) {

                        LOG.debug("Completed {} with {} fields", objekt.value, objekt.value.felder.size());

                        // Cleanup fields of the object
                        Stack<Pair<Integer, Objekt>> children = new Stack<>();
                        children.push(Pair.of(1, objekt.value));
                        Objekt.FeldExtended previous = objekt.value.felder.get(0);
                        for (int i = 1; i < objekt.value.felder.size(); i++) {

                            Objekt.FeldExtended current = objekt.value.felder.get(i);
                            while (!children.isEmpty()) {
                                if (current.vorkommen.position < children.peek().getLeft()) {
                                    children.pop();
                                } else {
                                    break;
                                }
                            }
                            Objekt parent = children.peek().getRight();

                            if (current.vorkommen.position > previous.vorkommen.position) {
                                // We found a child definition, create it as Objekt below its parent
                                Objekt child = null;
                                String name = Normalizer.toUppercaseFirst(previous.getName());
                                String newObjektName = RegelNaming.REPLACEMENTS.containsValue(name) ? parent.name + "_" + name : name;
                                for (Objekt o : objekt.value.children) {
                                    if (o.name.equals(newObjektName)) {
                                        child = o;
                                        break;
                                    }
                                }
                                boolean isNew = false;
                                if (child == null) {
                                    child = new Objekt(objekt.value.nummer, newObjektName, false);
                                    objekt.value.children.add(child);
                                    isNew = true;
                                    children.push(Pair.of(current.vorkommen.position, child));
                                }

                                // Detach the current field from its parent
                                objekt.value.felder.remove(current);
                                i--;

                                if (isNew) {
                                    // Add a value field as first member
                                    Objekt.FeldExtended value = new Objekt.FeldExtended();
                                    value.bezeichnung = "value";
                                    value.vorkommen = new Objekt.Vorkommen();
                                    value.vorkommen.wert = "1";
                                    value.feld = new Feld();
                                    value.feld.format = previous.feld.format;
                                    value.feld.regeln = previous.feld.regeln;
                                    value.feld.laenge = previous.feld.laenge;
                                    child.felder.add(value);
                                }

                                // Move the current field below the new object
                                addNonDuplicateField(current, child);

                                if (isNew) {
                                    // Force type
                                    previous.forcedTyp = child;
                                }

                            } else {
                                if (parent != objekt.value) {
                                    objekt.value.felder.remove(current);
                                    i--;
                                    addNonDuplicateField(current, parent);
                                }
                            }
                            previous = current;
                        }
                        state.to(States.NAME);
                        break;
                    }
                    Feld feld = felder.get(text);
                    currentFeld.value = new Objekt.FeldExtended();
                    currentFeld.value.feld = feld;
                    if (feld == null) {
                        LOG.warn("Feld {} not found, not adding to Objekt", text);
                    } else {
                        if ("8002".equals(text)) {
                            LOG.debug("Using field to fill, but not adding to Objekt");
                        } else {
                            objekt.value.felder.add(currentFeld.value);
                        }
                    }
                    break;
                case 1:
                    int subcolumn = determineColumn(columns.get(1).subcolumns, x);
                    if (currentFeld.value.vorkommen.wert != null) {
                        if (currentFeld.value.vorkommen.position < subcolumn) {
                            LOG.debug("Not replacing occurrence with higher precedence: {} vs {}", currentFeld.value.vorkommen.position, subcolumn);
                        } else {
                            currentFeld.value.vorkommen.wert = text;
                            currentFeld.value.vorkommen.position = subcolumn;
                        }
                    } else {
                        currentFeld.value.vorkommen.wert = text;
                        currentFeld.value.vorkommen.position = subcolumn;
                    }
                    break;
                case 2:
                    Matcher matcher = OBJEKT_PATTERN.matcher(text);
                    if (matcher.matches()) {
                        String childnummer = matcher.group(1);
                        currentFeld.value.forcedTyp = objekte.computeIfAbsent(childnummer, (k) -> new Objekt(childnummer, "Child" + childnummer + "_Parent" + objekt.value.nummer, true));
                    } else {
                        // Workaround page 160: Members are postfixed with the name of the object
                        if (text.endsWith("zum_" + objekt.value.name) ||
                                text.endsWith("zum " + objekt.value.name) ||
                                text.endsWith("des " + objekt.value.name)) {
                            text = text.substring(0, text.length() - objekt.value.name.length() - 4);
                        } else if (text.length() > objekt.value.name.length() && text.endsWith(objekt.value.name)) {
                            text = text.substring(0, text.length() - objekt.value.name.length());
                        }
                        // Workaround for page 145
                        if (!"Obj_".equals(text)) {
                            if (currentFeld.value.bezeichnung != null) {
                                currentFeld.value.bezeichnung += " " + text;
                            } else {
                                currentFeld.value.bezeichnung = text;
                            }
                        }
                    }
                    break;
                case 3:
                    currentFeld.value.feldart = Objekt.Feldart.valueOf(text);
                    break;
                case 4:
                    Regel regel = regeln.get(text);
                    if (regel == null) {
                        LOG.warn("Rule {} not found", text);
                    } else {
                        currentFeld.value.regeln.add(regel);
                    }
                    break;
                case 5:
                    currentFeld.value.erlaeuterung = text;
                    break;
            }
            lastColumn.value = column;
        }
    }

    private static void handleSatz(float x, float y, float lastX, String text, List<Column> columns, Holder<Satz> satz,
                                   Holder<Objekt.FeldExtended> currentFeld, Holder<Integer> lastColumn, Map<String, Regel> regeln,
                                   Map<String, Feld> felder, Map<String, Satz> saetze, Map<String, Objekt> objekte,
                                   State state, Holder<String> lastText) {
        if (text.isEmpty()) {
            return;
        }
        if (text.matches("Seite [0-9]+ von [0-9]+")) {
            return;
        }
        LOG.debug("Found text '{}' at {},{}", text, x, y);
        if (state.current == States.NAME) {
            Matcher matcher = SATZ_HEADLINE_PATTERN.matcher(text);
            if (matcher.matches()) {
                String nummer = matcher.group(2);
                String name = Normalizer.getSatzName(matcher.group(1));
                satz.value = saetze.computeIfAbsent(nummer, (k) -> new Satz(nummer, name));
                satz.value.fullname = matcher.group(1);
                LOG.debug("Adding satz {}", satz.value);
                state.to(States.HEADER);
            }
        } else if (state.current == States.HEADER) {
            switch (text) {
                case "FK":
                    // Adjust values: The text in the header is centered
                    columns.get(0).x = x - 4.02f;
                    columns.get(0).y = y;
                    break;
                case "1":
                    columns.get(1).x = x;
                    columns.get(1).y = y;
                    columns.get(1).subcolumns.get(0).x = x;
                    columns.get(1).subcolumns.get(0).y = y;
                    break;
                case "2 3 4":
                    columns.get(1).subcolumns.get(1).x = x;
                    columns.get(1).subcolumns.get(1).y = y;
                    columns.get(1).subcolumns.get(2).x = x + ((lastX - x) / 3);
                    columns.get(1).subcolumns.get(2).y = y;
                    columns.get(1).subcolumns.get(3).x = x + ((lastX - x) / 3 * 2);
                    columns.get(1).subcolumns.get(3).y = y;
                    break;
                case "2":
                    columns.get(1).subcolumns.get(1).x = x;
                    columns.get(1).subcolumns.get(1).y = y;
                    break;
                case "3":
                    columns.get(1).subcolumns.get(2).x = x;
                    columns.get(1).subcolumns.get(2).y = y;
                    break;
                case "4":
                    columns.get(1).subcolumns.get(3).x = x;
                    columns.get(1).subcolumns.get(3).y = y;
                    break;
                case "Feldbezeichnung":
                    columns.get(2).x = x;
                    columns.get(2).y = y;
                    break;
                case "Feldart":
                    columns.get(3).x = x;
                    columns.get(3).y = y;
                    break;
                case "Bedingung":
                    // Adjust values: The text in the header is centered
                    columns.get(4).x = x - 0.75f;
                    columns.get(4).y = y;
                    break;
                case "Erläuterung/Hinwei":
                    columns.get(5).x = x;
                    columns.get(5).y = y;
                    break;
                case "s":
                    state.to(States.BODY);
                    // Adjust values: The text in the header is centered
                    LOG.debug("All headers found, {}", columns);
                    break;
                case "Erläuterung/Hinweis":
                    columns.get(5).x = x;
                    columns.get(5).y = y;
                    state.to(States.BODY);
                    // Adjust values: The text in the header is centered
                    LOG.debug("All headers found, {}", columns);
                    break;
            }
            lastText.value = text;
        } else if (state.current == States.BODY) {
            int column = determineColumn(columns, x);
            LOG.debug("Setting {} in column {}", text, column);
            if (text.equals("Satzende")) {
                state.to(States.NAME);
                return;
            }
            // Workaround for page 35
            if ("FK".equals(text)) {
                state.to(States.HEADER);
                return;
            }

            switch (column) {
                case 0:
                    Feld feld = felder.get(text);
                    currentFeld.value = new Objekt.FeldExtended();
                    currentFeld.value.feld = feld;
                    if (feld == null) {
                        LOG.warn("Feld {} not found, not adding to Objekt", text);
                    } else {
                        if ("8000".equals(text) || "8001".equals(text)) {
                            LOG.debug("Using field to fill, but not adding to Satz");
                        } else {
                            satz.value.felder.add(currentFeld.value);
                        }
                    }
                    break;
                case 1:
                    int subcolumn = determineColumn(columns.get(1).subcolumns, x);
                    if (currentFeld.value.vorkommen.wert != null) {
                        LOG.debug("Not replacing occurrence set first: {} vs {}", currentFeld.value.vorkommen.position, subcolumn);
                    } else {
                        currentFeld.value.vorkommen.wert = text;
                        currentFeld.value.vorkommen.position = subcolumn;
                    }
                    break;
                case 2:
                    Matcher matcher = OBJEKT_PATTERN.matcher(text);
                    if (matcher.matches()) {
                        String childnummer = matcher.group(1);
                        currentFeld.value.forcedTyp = objekte.computeIfAbsent(childnummer, (k) -> new Objekt(childnummer, "Child" + childnummer + "_Parent" + satz.value.nummer, true));
                    } else {
                        if (currentFeld.value.bezeichnung == null) {
                            currentFeld.value.bezeichnung = text;
                        } else {
                            currentFeld.value.bezeichnung += text;
                        }
                    }
                    break;
                case 3:
                    try {
                        if (currentFeld.value.feldart != null) {
                            LOG.debug("Not replacing fieldvalue set first");
                        } else {
                            currentFeld.value.feldart = Objekt.Feldart.valueOf(text);
                        }
                    } catch (RuntimeException e) {
                        LOG.warn(text);
                        currentFeld.value.feldart = Objekt.Feldart.k;
                    }
                    break;
                case 4:
                    Regel regel = regeln.get(text);
                    if (regel == null) {
                        LOG.warn("Rule {} not found", text);
                    } else {
                        currentFeld.value.regeln.add(regel);
                    }
                    break;
                case 5:
                    currentFeld.value.erlaeuterung = text;
                    break;
                default:
                    LOG.warn("No column found for text {}", text);
            }
            lastColumn.value = column;
        }
    }

    private static void addNonDuplicateField(Objekt.FeldExtended current, Objekt parent) {
        // Workaround: Obj_0042 has multiple children with same semantics. This allows reusage (which is nice),
        // but at the cost of actually invalid usage (types are different). This only works coincidentally
        for (var existing : parent.felder) {
            if (existing.feld.equals(current.feld)) {
                return;
            }
        }
        parent.felder.add(current);
    }

    private static void handleFeld(float x, float y, String text, List<Column> columns, Holder<Feld> feld,
                                   Holder<Integer> lastColumn, Map<String, Feld> felder, State state,
                                   Holder<String> lastText, Map<String, Regel> regeln) {
        if (text.isEmpty()) {
            return;
        }
        if (text.matches("Seite [0-9]+ von [0-9]+")) {
            return;
        }
        LOG.debug("Found text '{}' at {},{}", text, x, y);
        if (state.current == States.HEADER) {
            switch (text) {
                case "FK":
                    columns.get(0).x = x;
                    columns.get(0).y = y;
                    break;
                case "Inhalt":
                    columns.get(1).x = x;
                    columns.get(1).y = y;
                    break;
                case "Feldlänge":
                    columns.get(2).x = x;
                    columns.get(2).y = y;
                    break;
                case "Format":
                    columns.get(3).x = x;
                    columns.get(3).y = y;
                    break;
                case "Regel":
                    columns.get(4).x = x;
                    columns.get(4).y = y;
                    break;
                case "Erlaubte Inhalte und deren Bedeutung":
                    columns.get(5).x = x;
                    columns.get(5).y = y;
                    state.to(States.BODY);
                    LOG.debug("All headers found, {}", columns);
                    break;
            }
            lastText.value = text;
        } else if (state.current == States.BODY) {
            int column = determineColumn(columns, x);
            LOG.debug("Setting {} in column {}", text, column);

            switch (column) {
                case 0:
                    if (!text.matches("[0-9]{4}")) {
                        LOG.warn("Invalid field number detected: {}", text);
                        return;
                    }
                    if (lastColumn.value >= 1 || lastColumn.value == -1) {
                        feld.value = felder.computeIfAbsent(text, (k) -> new Feld(text));
                    }
                    LOG.debug("Adding feld {}", feld.value.fk);
                    break;
                case 1:
                    feld.value.inhalt = text;
                    break;
                case 2:
                    feld.value.laenge = text;
                    break;
                case 3:
                    feld.value.format = Feld.Format.valueOf(text);
                    break;
                case 4:
                    Regel regel = regeln.get(text);
                    if (regel == null) {
                        LOG.warn("Rule {} not found", text);
                    } else {
                        feld.value.regeln.add(regel);
                    }
                    break;
                case 5:
                    feld.value.inhalt = text;
                    break;
            }
            lastColumn.value = column;
        }
    }

    private static void handleRegel(float x, float y, String text, List<Column> columns, Holder<Regel> regel,
                                    Holder<Integer> lastColumn, Map<String, Regel> regeln, State state,
                                    Holder<String> lastText, Supplier<Regel> constructor) {
        if (text.isEmpty()) {
            return;
        }
        if (text.matches("Seite [0-9]+ von [0-9]+")) {
            return;
        }
        LOG.debug("Found text '{}' at {},{}", text, x, y);
        if (state.current == States.HEADER) {
            switch (text) {
                case "mmer":
                    if ("Regelnu".equals(lastText.value)) {
                        columns.get(0).x = x;
                        columns.get(0).y = y;
                    }
                    break;
                case "Kategorie":
                    columns.get(1).x = x;
                    columns.get(1).y = y;
                    break;
                case "Fehlerstatus":
                    columns.get(2).x = x;
                    columns.get(2).y = y;
                    break;
                case "Prüfung":
                    columns.get(3).x = x;
                    columns.get(3).y = y;
                    break;
                case "Erläuterung":
                    columns.get(4).x = x;
                    columns.get(4).y = y;
                    state.to(States.BODY);
                    LOG.debug("All headers found, {}", columns);
                    break;
            }
            lastText.value = text;
        } else if (state.current == States.BODY) {
            int column = determineColumn(columns, x);
            LOG.debug("Setting {} in column {}", text, column);

            switch (column) {
                case 0:
                    if (lastColumn.value >= 3 || lastColumn.value == -1) {
                        regel.value = constructor.get();
                    }
                    regel.value.regelnummer = text;
                    regeln.put(regel.value.regelnummer, regel.value);
                    LOG.debug("Adding regel {}", regel.value.regelnummer);
                    break;
                case 1:
                    regel.value.kategorie = Regel.Kategorie.valueOf(text);
                    break;
                case 2:
                    regel.value.fehlerstatus = Regel.Fehlerstatus.valueOf(text);
                    break;
                case 3:
                    if (regel.value.pruefung == null) {
                        regel.value.pruefung = text;
                    } else {
                        regel.value.pruefung += " " + text;
                    }
                    break;
                case 4:
                    // Workaround: PDFBox returns the first prefix multiple times
                    if (!regel.value.erlaeuterung.isEmpty() && text.startsWith("00 = ")) {
                        text = text.substring(4);
                    }
                    regel.value.erlaeuterung.append(text).append("\n");
                    break;
            }
            lastColumn.value = column;
        }
    }

    private static int determineColumn(List<Column> columns, float lastX) {
        int column = -1;
        for (int i = 0; i < columns.size(); i++) {
            Column c = columns.get(i);
            if (lastX >= (c.x - 0.1f)) {
                column = i;
            }
        }
        return column;
    }

}
