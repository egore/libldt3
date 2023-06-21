package libldt3.parser.parsing;

import libldt3.parser.RegelNaming;
import libldt3.parser.generation.Normalizer;
import libldt3.parser.model.ErlaubterInhalt;
import libldt3.parser.model.Feld;
import libldt3.parser.model.Formatregel;
import libldt3.parser.model.Kontextregel;
import libldt3.parser.model.Objekt;
import libldt3.parser.model.Regel;
import org.apache.commons.lang3.tuple.Triple;
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
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final Logger LOG = LoggerFactory.getLogger(Parser.class);

    private static final Pattern OBJEKT_HEADLINE_PATTERN = Pattern.compile("^[0-9.]+[ ]+Obj_([A-Za-zÄÖÜäöüß()0-9 /-]+) „Obj_([0-9]+)“$");
    private static final Pattern OBJEKT_PATTERN = Pattern.compile("Obj_([0-9]{4}).*");

    static class Column {
        public List<Column> subcolumns;
        float x = 0.0f;
        float y = 0.0f;

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    enum State {
        NAME,
        HEADER,
        DESCRIPTION, BODY
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

    public static Triple<Map<String, Regel>, Map<String, Feld>, Map<String, Objekt>> parse(String filePath) throws IOException {
        Map<String, Regel> regeln = new HashMap<>();
        Map<String, Feld> felder = new HashMap<>();
        Map<String, Objekt> objekte = new HashMap<>();

        try (PDDocument document = Loader.loadPDF(new File(filePath))) {

            // Erlaubte Inhalte
            {
                Holder<State> state = new Holder<>(State.HEADER);
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
                                if (buffer.length() > 0) {
                                    handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, ErlaubterInhalt::new);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (buffer.length() > 0) {
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
                Holder<State> state = new Holder<>(State.HEADER);
                Holder<String> lastText = new Holder<>(null);
                Holder<Regel> currentRegel = new Holder<>(new Kontextregel(felder));

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
                                if (buffer.length() > 0) {
                                    handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, () -> new Kontextregel(felder));
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (buffer.length() > 0) {
                            handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, () -> new Kontextregel(felder));
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
                Holder<State> state = new Holder<>(State.HEADER);
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
                                if (buffer.length() > 0) {
                                    handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText, Formatregel::new);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (buffer.length() > 0) {
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
                Holder<State> state = new Holder<>(State.HEADER);
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
                                if (buffer.length() > 0) {
                                    handleFeld(firstX, firstY, buffer.toString().trim(), columns, currentFeld, lastColumn, felder, state, lastText, regeln);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (buffer.length() > 0) {
                            handleFeld(firstX, firstY, buffer.toString().trim(), columns, currentFeld, lastColumn, felder, state, lastText, regeln);
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(38);
                stripper.setEndPage(73);
                stripper.getText(document);
            }

            {
                Holder<State> state = new Holder<>(State.NAME);
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
                                if (buffer.length() > 0) {
                                    handleObjekt(firstX, firstY, buffer.toString().trim(), columns, currentObjekt, currentFeld, lastColumn, regeln, felder, objekte, state, lastText);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (buffer.length() > 0) {
                            handleObjekt(firstX, firstY, buffer.toString().trim(), columns, currentObjekt, currentFeld, lastColumn, regeln, felder, objekte, state, lastText);
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(117);
                stripper.setEndPage(127);
                stripper.getText(document);
                // Skip 128
                stripper.setStartPage(129);
                stripper.setEndPage(169);
                stripper.getText(document);
                // Skip 170
                stripper.setStartPage(171);
                stripper.setEndPage(185);
                stripper.getText(document);
            }
        }
        return Triple.of(regeln, felder, objekte);
    }

    private static void handleObjekt(float x, float y, String text, List<Column> columns, Holder<Objekt> objekt,
                                     Holder<Objekt.FeldExtended> currentFeld, Holder<Integer> lastColumn, Map<String, Regel> regeln,
                                     Map<String, Feld> felder, Map<String, Objekt> objekte, Holder<State> state, Holder<String> lastText) {
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
                state.value != State.BODY) {
            state.value = State.HEADER;
        } else if (state.value == State.NAME) {
            Matcher matcher = OBJEKT_HEADLINE_PATTERN.matcher(text);
            if (matcher.matches()) {
                String nummer = matcher.group(2);
                String name = Normalizer.getObjektName(matcher.group(1));
                objekt.value = objekte.computeIfAbsent(nummer, (k) -> new Objekt(nummer, name, false));
                objekt.value.stub = false;
                objekt.value.name = name;
                state.value = State.DESCRIPTION;
                LOG.debug("Adding objekt {}", objekt.value);
            }
        } else if (state.value == State.DESCRIPTION) {
            if (objekt.value.beschreibung == null) {
                objekt.value.beschreibung = text;
            } else if (!text.startsWith("Obj_")) {
                objekt.value.beschreibung += " " + text;
            }
        } else if (state.value == State.HEADER) {
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
                    state.value = State.BODY;
                    // Adjust values: The text in the header is centered
                    columns.get(0).x -= 4.02;
                    LOG.debug("All headers found, {}", columns);
                    break;
            }
            lastText.value = text;
        } else if (state.value == State.BODY) {
            // Workaround for page 143
            if ("FK".equals(text)) {
                state.value = State.HEADER;
                return;
            }
            int column = determineColumn(columns, x);
            LOG.debug("Setting {} in column {}", text, column);

            switch (column) {
                case 0:
                    if ("8003".equals(text)) {

                        LOG.debug("Completed {} with {} fields", objekt.value, objekt.value.felder.size());

                        // Cleanup fields of the object
                        for (int i = 1; i < objekt.value.felder.size(); i++) {

                            Objekt.FeldExtended current = objekt.value.felder.get(i);
                            Objekt.FeldExtended previous = objekt.value.felder.get(i - 1);
                            if (current.vorkommen.position == previous.vorkommen.position + 1) {

                                // We found a child definition, create it as Objekt below its parent
                                Objekt child = null;
                                String name = Normalizer.toUppercaseFirst(previous.getName());
                                String newObjektName = RegelNaming.REPLACEMENTS.containsValue(name) ? objekt.value.name + "_" + name : name;
                                for (Objekt o : objekt.value.children) {
                                    if (o.name.equals(newObjektName)) {
                                        child = o;
                                        break;
                                    }
                                }
                                boolean isNew = false;
                                if (child == null) {
                                    child = new Objekt("0", newObjektName, false);
                                    objekt.value.children.add(child);
                                    isNew = true;
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
                                    value.feld.format = Feld.Format.alnum;
                                    child.felder.add(value);
                                }

                                // Move the current field below the new object
                                child.felder.add(current);

                                if (isNew) {
                                    // Force type
                                    previous.forcedTyp = child;
                                }
                            }
                        }
                        state.value = State.NAME;
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
                        if (currentFeld.value.bezeichnung != null) {
                            currentFeld.value.bezeichnung += " " + text;
                        } else {
                            currentFeld.value.bezeichnung = text;
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

    private static void handleFeld(float x, float y, String text, List<Column> columns, Holder<Feld> feld,
                                   Holder<Integer> lastColumn, Map<String, Feld> felder, Holder<State> state,
                                   Holder<String> lastText, Map<String, Regel> regeln) {
        if (text.isEmpty()) {
            return;
        }
        if (text.matches("Seite [0-9]+ von [0-9]+")) {
            return;
        }
        LOG.debug("Found text '{}' at {},{}", text, x, y);
        if (state.value == State.HEADER) {
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
                    state.value = State.BODY;
                    LOG.debug("All headers found, {}", columns);
                    break;
            }
            lastText.value = text;
        } else if (state.value == State.BODY) {
            int column = determineColumn(columns, x);
            LOG.debug("Setting {} in column {}", text, column);

            switch (column) {
                case 0:
                    if (!text.matches("[0-9]{4}")) {
                        LOG.warn("Invalid field number detected: {}", text);
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
                                    Holder<Integer> lastColumn, Map<String, Regel> regeln, Holder<State> state,
                                    Holder<String> lastText, Supplier<Regel> constructor) {
        if (text.isEmpty()) {
            return;
        }
        if (text.matches("Seite [0-9]+ von [0-9]+")) {
            return;
        }
        LOG.debug("Found text '{}' at {},{}", text, x, y);
        if (state.value == State.HEADER) {
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
                    state.value = State.BODY;
                    LOG.debug("All headers found, {}", columns);
                    break;
            }
            lastText.value = text;
        } else if (state.value == State.BODY) {
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
                    if (regel.value.erlaeuterung.length() > 0 && text.startsWith("00 = ")) {
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
