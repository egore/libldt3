package libldt3.ruleparser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KontextParserTest {

    private static final BaseErrorListener FAIL_ERROR_LISTENER = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            Assertions.fail("line " + line + ":" + charPositionInLine + " " + msg);
        }
    };

    @Test
    public void testOrRule_Two() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Entweder FK 6305 oder FK 8242 ist vorhanden."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        KontextBaseListener kontextBaseListener = new KontextBaseListener() {
            /*@Override
            public void exitEitherExists(KontextParser.EitherExistsContext ctx) {
                Assertions.assertEquals(2, ctx.fieldExists().fk().size());
                super.exitEitherExists(ctx);
            }*/
        };
        parser.addParseListener(kontextBaseListener);
        parser.regel();
    }

    @Test
    public void testOrRule_Three() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Entweder FK 6305 oder FK 8242 oder FK 4711 ist vorhanden."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        KontextBaseListener kontextBaseListener = new KontextBaseListener() {
            /*@Override
            public void exitEitherExists(KontextParser.EitherExistsContext ctx) {
                Assertions.assertEquals(3, ctx.fieldExists().fk().size());
                super.exitEitherExists(ctx);
            }*/
        };
        parser.addParseListener(kontextBaseListener);
        parser.regel();
    }

    @Test
    public void testK001() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Entweder FK 6305 oder FK 8242 ist vorhanden."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK002() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Feldinhalt von FK 8419 = 1 oder 2, muss FK 8421 vorkommen. Wenn Feldinhalt von FK 8419 = 9, darf FK 8421 nicht vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK003() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Feldinhalt von FK 7303 = 1, 8 oder 9 ist und FK 8410 vorhanden, muss auch FK 8411 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK005() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 1, darf FK 4121 nicht vorhanden sein. Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 2, kann FK 4121 vorhanden sein"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK006() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 8428 oder FK 8430 oder FK 8429 vorhanden ist, kann FK 8431 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK008() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn der Inhalt von  FK 8002 = Obj_0058 (Obj_Untersuchungsabrechnung) und der Inhalt FK 7303 = 1, 2, 8, 9 oder 10 dann sind als Inhalte FK 4121 nur 0, 1, 2 oder 3 erlaubt."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK009() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn der Inhalt von FK 8002 = Obj_0035 (Obj_Laborergebnisbericht), dann muss mindestens eine FK 8002 mit den Werten Obj_0060(Obj_Untersuchungsergebnis Klinische Chemie), Obj_0061(Obj_Untersuchungsergebnis Mikrobiologie), Obj_0062(Obj_Untersuchungsergebnis Krebsfrueherkennung Zervix-Karzinom), Obj_0063(Obj_Untersuchungsergebnis Zytologie), Obj_0073(Sonstige Untersuchungsergebnisse) oder Obj_0055(Obj_Blutgruppenzugehoerigkeit) vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK010() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 8002 = Obj_0059 (Obj_Untersuchungsanforderung) oder FK 8002 = Obj_0061 (Obj_Untersuchungsergebnis Mikrobiologie), dann muss FK 8410 oder FK 7260 oder FK 8434 vorhanden sein (FK 8410 und FK 7260 dürfen nicht gemeinsam vorhanden sein)."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK011() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8000 = 8215 und FK 8002 = Obj_0059 (Obj_Untersuchungsanforderung) und FK 7303 = 2 oder 10 dann muss FK 8410 vorhanden sein und FK 7260 darf nicht vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK012() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 4239 = 27 oder 28, dann muss das FK 4221 vorhanden sein. FK 4221 darf nicht vorhanden sein, wenn Inhalt von FK 4239 ≠ 27 oder 28 ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK014() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Nur wenn FK 4239 = 27, können die folgenden Kombinationen vorhanden sein: - FK 4217 und FK 4241 oder - FK 4225 und FK 4241 oder - FK 4225 und FK 4248."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK015() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Nur wenn FK 4239 = 27 oder 28, kann FK 4229 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK016() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 4239 ≠ 27, 28, dann muss FK 8241 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK017() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 3112 und/oder FK 3121 muss vorhanden sein. Ausnahmen: Nur wenn FK 3114 vorhanden und der Feldinhalt ungleich „D“ ist, dann gilt: Ist die FK 4109 vorhanden, dann muss die FK 3112 nicht vorhanden sein. Nur wenn FK 3124 vorhanden und der Feldinhalt ungleich „D“ ist, dann gilt: Ist die FK 4109 vorhanden, dann muss die FK 3121 nicht vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK019() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 4121 = 0, 1 oder 2, dann gilt für den Inhalt FK 5001 die Regel F009."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK020() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt FK 8002 = Obj_0002 (Obj_Abrechnung GKV) und FK 0201 in Satzart 8230 oder 8215 vorhanden, dann muss auch FK 0212 oder FK 0223 in Satzart 8230 oder 8215 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK021() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 4239 = 28, dann muss Inhalt von FK 4221 ungleich 3 sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK022() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 4131 = „07” oder „08“, dann muss Inhalt von FK 4106 = „01“ sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK023() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 4131 = „06”, dann muss Inhalt von FK 4106 = „02“ sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK024() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 4131 = „04”, dann muss Inhalt von FK 4106 = „00“ sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK025() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8000 = 8215, dann gilt: Falls FK 4109 vorhanden ist, muss mindestens FK 3105 oder FK 3119 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK027() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8000 = 8215, dann muss im Obj_0001(Obj_Abrechnungsinformationen) mindestens einmal eine Feldkennung aus nachfolgender Liste vorhanden sein: 8102, 8103, 8104, 8105, 8106, 8109."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK029() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7421 = 15, 16 oder 90 dann muss FK 8143 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK030() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7421 = 02, 03, 04, 05, 06, 11 oder 12 muss FK 8147 im Obj_0048 (Obj_RgEmpfaenger) vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK031() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in der Satzart 8215 mehrere Objekte mit FK 8002 = Obj_0002 (Obj_Abrechnung GKV) vorhanden sind, dann müssen sich diese in der Kombination der Inhalte der FK 4239/FK 4221 unterscheiden."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK032() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Für Satzart 8215 gilt: Wenn Inhalt von FK 7303 = 1 muss FK 4239 = 27 vorhanden sein. Wenn Inhalt von FK 7303 = 2 muss FK 4239 = 28 vorhanden sein. Wenn Inhalt von FK 7303 = 9 muss FK 4239 = 27 in Kombination mit FK 4221 = 2 vorhanden sein. Wenn Inhalt von FK 7303 = 10 muss FK 4239 = 28 in Kombination mit FK 4221 = 2 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK033() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in der Satzart 8215 mehrere Objekte mit FK 8002 = Obj_0003 (Obj_Abrechnung PKV) vorhanden sind, muss Inhalt von FK 7362 jeweils einen anderen Wert aufweisen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK034() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7303 = 3 muss FK 7362 = 1 vorhanden sein. Wenn Inhalt von FK 7303 = 4 muss FK 7362 = 2 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK037() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 8434 in Obj_0059 (Obj_Untersuchungsanforderung) kann nur vorkommen, wenn FK 8410 und FK 7260 nicht vorhanden sind."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK038() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7310 = 1, dann kann FK 7311 vorhanden sein und FK 7312 darf nicht vorhanden sein. Wenn Inhalt von FK 7310 = 2, dann kann FK 7312 vorhanden sein und FK 7311 darf nicht vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK039() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 7310 kann nur vorhanden sein, wenn Obj_0053 (Obj_Tier/Sonstiges) vorhanden."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK041() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 4239 = 27 und FK 8240 vorhanden, dann muss eine der folgenden Kombinationen vorhanden sein: - FK 4217 und FK 4241 oder - FK 4225 und FK 4241 oder - FK 4225 und FK 4248."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK042() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Obj_0022 (Obj_Einsenderidentifikation) in Satzart 8215 muss nur dann verwendet werden, wenn die Inhalte des Obj_0022 (Obj_Einsenderidentifikation) von denen in Satzart 8230 abweichen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK043() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 0204 im Obj_0019 (Obj_Betriebsstätte) nur einmal vorkommt, muss der Inhalt der FK 0204 = 1, 2, 3 oder 4 sein. Wenn FK 0204 im Obj_0019 (Obj_Betriebsstätte) zweimal vorkommt, muss der Inhalt der FK 0204 einmal mit 1, 2, 3 oder 4 und einmal mit 5 oder 6 gefüllt sein. Im Obj_0019 (Obj_Betriebsstätte) darf die FK 0204 maximal zweimal vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK044() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 0200 oder FK 0201 müssen vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK045() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7321 = 03, 04, 05, 06, 08, 11, 12, 14 oder 16 ist, dann muss FK 8147 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK046() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7321 = 01, 02, 07, 08, 14 oder 17, dann muss FK 8119 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK047() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7321 = 03, 15 oder 16, darf FK 8119 nicht vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK048() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7321 = 03, 15 oder 16, muss FK 8143 im  Obj_0022 (Obj_Einsenderidentifikation) vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK050() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8002 = Obj_0002 (Obj_Abrechnung GKV) vorhanden, dann muss FK 0105 und FK 4239 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK053() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7260 = 4 muss FK 7352 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK054() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Obj_0042 (Obj_Normalwert) mehr- fach im Obj_0060 (Obj_Untersuchungsergebnis Klinische Chemie) bzw. Obj_0061 (Obj_Untersuchungsergebnis Mikrobiologie) vorkommt, darf der Wert 13 in der FK 8424 mehrfach vorkommen, alle anderen Werte dürfen nur jeweils einmal vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK055() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 8460 oder FK 8461 oder FK 8462 oder FK 7316 muss vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK056() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 3108 muss nur vorhanden sein, wenn der Inhalt von FK 7303 = 1, 2, 8, 9 oder 10  ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK057() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 0222 muss vorhanden sein, wenn in mindestens einem  Obj_0059 (Obj_Untersuchungsanforderung) die FK 7303 mit dem Inhalt 8 vorhanden ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK059() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Mindestens eine der FK 7330, FK 7331, FK 7332, FK 7333, FK 7334 oder FK 7335 muss vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK060() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Inhalt von FK 7303 = 11 darf nur im Obj_0027 (Obj_Veranlassungsgrund) vorkommen"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK063() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("In Satzart 8215 darf im Obj_0037 (Obj_Material) nur FK 8219 vorkommen, in Satzart 8205 darf auch die FK 8220 verwendet werden."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK069() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8215 die FK 8137 vorhanden ist, müssen die FK 8113 und FK 8159 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK070() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8215 die FK 8102, FK 8103, FK 8104, FK 8106 oder FK 8109 vorkommen, muss auch FK 8145 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK071() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 8158 kann im Obj_0055 nur vorhanden sein, wenn FK 3412, FK 3413, FK 3414, FK 3415, FK 3416, FK 3417, FK 3418 oder FK 3419 vorhanden ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK075() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 9970 = 999, dann muss FK 6327 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK076() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8418 ≠ 01 oder 02 oder 09 oder 11 oder 12 ist, dann muss FK 8225 mindestens einmal vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK078() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 3412, FK 3413, FK 3414, FK 3415, FK 3416, FK 3417, FK 3418 oder FK 3419 vorhanden sind, dann muss FK 8225 mindestens einmal im Obj_0055 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK080() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 8158 kann im Obj_0063 nur vorhanden sein, wenn FK 7368 nicht vorhanden ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK081() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 7368 im Obj_0063 nicht vorhanden ist, muss FK 8225 mindestens einmal im Obj_0063 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK082() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8418 = 11 oder FK 7368 vorhanden ist, muss FK 8126 im Obj_0037 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK083() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8220 oder 8205 die FK 7266 mit den Inhalten 1 oder 2 vorkommt, muss in Satzart 8205 die FK 8145 vorkommen, die FK 8153 darf nicht vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK084() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8220 oder 8205 die FK 7266 mit den Inhalten 1 oder 2 vorkommt, muss in Satzart 8205 die FK 8145 oder FK 8153 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK085() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 8111 kann nur vorkommen, wenn FK 7286 mit Inhalt ≠ 0 vorkommt."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK086() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 7293 kann nur vorkommen, wenn Inhalt von FK 7286 = 1 oder 2 ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK087() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Falls eine Versichertenkarte eingelesen wurde, dann muss die FK 4109 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK088() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Falls die Werte der Feldkennungen FK 4110 und FK 3116 bekannt sind, dann müssen die Felder übertragen werden."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK089() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Eine der FK 7319 oder FK 7313 oder FK 7314 muss vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK090() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Falls die FK 4109 vorhanden ist und der Feldinhalt >= „01.01.2015“ sowie der Inhalt der Stellen 3 – 5 der FK 4104 >= 800, dann müssen die FK 3105 und FK 4110 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK091() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Falls die FK 4109 vorhanden ist und der Feldinhalt >= „01.01.2015“ sowie der Inhalt der Stellen 3 – 5 der FK 4104 < 800, dann müssen die FK 3119 und FK 4133 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK092() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("In Satzart 8220 muss einmal die FK 8147 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK093() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7362 = 2, dann darf Inhalt FK 7421 im Obj_0048 nur der Wert 02 sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK094() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7420 = 12 und FK 7303 mit den Werten 1, 2, 3, 8, 9 oder 10 in jeweiliger Satzart 8205 oder 8215 vorkommen, dann müssen die FK 3103, FK 3110 und FK 8228 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK095() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 7368 im Obj_0073 nicht vorhanden ist, muss FK 8225 mindestens einmal im Obj_0073 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK096() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8401 = 2, darf der Inhalt von FK 8418 nicht 02, 05 oder 10 sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK097() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8215 die FK 7303 mit den Inhalten 1, 2, 8, 9, 10, 13, 15 oder 16 vorkommt, muss die FK 8102 mindestens einmal vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK098() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8215 die FK 7303 mit den Inhalten 3, 4 oder 14 vorkommt, muss die FK 8103 mindestens einmal vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK099() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn der Inhalt der FK 8422 = !L oder !- oder !H oder !+ ist, muss FK 8126 der FK 8422 folgen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK100() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 8002 = Obj_0068 der FK 8242 folgt, muss FK 6329 in diesem Objekt vorkommen und FK 3564 darf nicht vorkommen. Wenn FK 8002 = Obj_0068 den FK 8167, FK 8217, FK 8236, FK 8237 oder FK 8238 folgt, muss FK 3564 in diesem Objekt vorkommen und FK 6329 darf nicht vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK101() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("In Obj_0008 (Obj_Adressat) muss entweder FK 8143 oder FK 8147 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK102() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8215 die FK 7303 mit dem Inhalt 6 vorkommt, muss die FK 8104 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK103() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8215 die FK 7303 mit dem Inhalt 5 vorkommt, muss die FK 8106 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK104() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 8147 im Obj_0045 (Patient) vorkommt, dann muss der Inhalt der FK 7420 der Wert 12 sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK105() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn in Satzart 8215 die FK 7303 mit dem Inhalt 7 vorkommt, muss die FK 8105 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK106() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Im Obj_0060 muss entweder die FK 7260 oder die FK 8410 vorkommen"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK107() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7321 = 01, 02 oder 07 ist, dann muss FK 8114 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK112() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Die FK 8310 muss nur dann vorkommen, wenn der Inhalt der FK 8310 im Auftrag übermittelt wurde."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK113() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn der Inhalt von FK 7303 = 99, muss Inhalt von FK 8000 = 8215 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK114() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn der Inhalt von FK 8000 = 8215 und der Inhalt von FK 7303 in mindestens einem Obj_0059 mit dem Wert 99 vorkommt, muss im Obj_0013 die FK 8313 vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK115() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Entweder die FK 0212 oder die FK 0223 muss jeweils mindestens einmal vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK116() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Feldinhalt von FK 4239 ≠ 28 und wenn FK 0222 vorhanden ist, dann muss entweder FK 0212 oder FK 0223 vorhanden sein. Wenn Feldinhalt von FK 4239 = 28 und wenn FK 0222 vorhanden ist, dann muss ein FK 0212 vorhanden sein. Die FK 0223 darf nicht vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK117() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn FK 8147 im Obj_Tier/Sonstiges vorkommt, muss im folgenden Obj_0047 der Inhalt der FK 7420 = 11 oder 16 sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK118() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Die FK 8512 muss nur dann vorkommen, wenn der Inhalt der FK 8512 im Auftrag übermittelt wurde."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK119() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Im Obj_0063 muss entweder die FK 7260 oder die FK 8410 vorkommen"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK120() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Im Obj_0073 muss entweder die FK 7260 oder die FK 8410 vorkommen"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK121() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 8158 kann im Obj_0073 nur vorhanden sein, wenn FK 7368 nicht vorhanden ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK122() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 3317 kann nur vorhanden sein, wenn Inhalt FK 3316 im Obj_0062 = 1 ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK123() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 3320 kann nur vorhanden sein, wenn FK 7415 oder FK 7417 oder FK 3318 oder FK 3319 vorhanden sind, und FK 3321 nicht vorhanden ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK124() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 3321 kann nur vorhanden sein, wenn FK 7415 oder FK 7417 oder FK 3318 oder FK 3319 vorhanden sind, und FK 3320 nicht vorhanden ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK125() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 8158 kann im Obj_0062 nur vorhanden sein, wenn die FK 7414 vorkommt und der Inhalt von FK 7414 ≠ 0 ist oder die FK 3316 vorkommt und der Inhalt von FK 3316 ≠ 3 ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK126() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 8225 muss im Obj_0062 mindestens einmal vorkommen, wenn Inhalt von FK 7414 ≠ 0."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK128() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 3316 darf nur vorhanden sein, wenn Inhalt von FK 3314 = 1 im Obj_0034 vorhanden ist."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK130() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Es kann entweder die FK 8618 oder FK 8619 vorhanden sein. Beide Feldkennungen dürfen nicht gleichzeitig vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK131() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8626 = 2, muss entweder FK 8627 oder FK 4111 vorhanden sein. Beide Feldkennungen dürfen gleichzeitig vorhanden sein. Wenn Inhalt von FK 8626 = 1 oder 3, darf FK 8627 und FK 4111 nicht vorhanden sein. Wenn Inhalt von FK 8626 = 3, darf FK 8617, 8618, 8619  und 8620 nicht vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK132() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 8626 = 1, muss innerhalb des entsprechenden Objektes min. die FK 8617 oder die FK 8631 vorhanden sein. Es kann eine beliebige Kombination der zwei Feldkennungen vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK133() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7303 im Obj_0027 (Obj_Veranlassungsgrund) = 2 oder 10 ist, dann kann die FK 4209 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK134() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn im Obj_0062 die FK 7414 vorkommt und der Inhalt von FK 7414 ungleich 0 ist, dann müssen die FK 7405, FK 7406, FK 7407, FK 7408, FK 7409, FK 7410, FK 7411 und FK 7412 vorkommen. Wenn im Obj_0062 die FK 7414 nicht vorkommt, dann dürfen die FK 7405, FK 7406, FK 7407, FK 7408, FK 7409, FK 7410, FK 7411 und FK 7412 nicht vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

    @Test
    public void testK135() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Die FK 8632 darf im Obj_0009 und Obj_0017 nicht vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

}
