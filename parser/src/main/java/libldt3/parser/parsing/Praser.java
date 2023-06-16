package libldt3.parser.parsing;

import libldt3.parser.model.ErlaubterInhalt;
import libldt3.parser.model.Feld;
import libldt3.parser.model.Regel;
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

public class Praser {

    private static final Logger LOG = LoggerFactory.getLogger(Praser.class);

    static class Column {
        float x = 0.0f;
        float y = 0.0f;
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    enum State {
        HEADER,
        BODY
    }

    static class Holder<T> {
        T value;
        public Holder(T value) {
            this.value = value;
        }
    }

    public static Pair<Map<String, Regel>, Map<String, Feld>> parse(String filePath) throws IOException {
        Map<String, Regel> regeln = new HashMap<>();
        Map<String, Feld> felder = new HashMap<>();

        try (PDDocument document = Loader.loadPDF(new File(filePath))) {
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
                                    handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText);
                                }
                                buffer.setLength(0);
                                firstX = p.getX();
                                firstY = p.getY();
                            }
                            lastX = p.getEndX();
                            buffer.append(p.getUnicode());
                        }
                        if (buffer.length() > 0) {
                            handleRegel(firstX, firstY, buffer.toString().trim(), columns, currentRegel, lastColumn, regeln, state, lastText);
                        }
                        super.writeString(text, textPositions);
                    }
                };
                stripper.setStartPage(78);
                stripper.setEndPage(100);
                stripper.getText(document);
            }

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
                stripper.setEndPage(60);
                stripper.getText(document);
            }
        }
        return Pair.of(regeln, felder);
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
        } if (state.value == State.BODY) {
            int column = determineColumn(columns, x);
            LOG.debug("Setting {} in column {}", text, column);

            switch (column) {
                case 0:
                    if (lastColumn.value >= 5 || lastColumn.value == -1) {
                        feld.value = new Feld();
                    }
                    feld.value.fk = text;
                    felder.put(feld.value.fk, feld.value);
                    LOG.info("Adding feld {}", feld.value.fk);
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
                             Holder<String> lastText) {
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
                        regel.value = new ErlaubterInhalt();
                    }
                    regel.value.regelnummer = text;
                    regeln.put(regel.value.regelnummer, regel.value);
                    LOG.info("Adding regel {}", regel.value.regelnummer);
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
