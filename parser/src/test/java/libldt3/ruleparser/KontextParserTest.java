package libldt3.ruleparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KontextParserTest {

    @Test
    public void testOrRule_Two() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Entweder FK 6305 oder FK 8242 ist vorhanden."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        KontextBaseListener kontextBaseListener = new KontextBaseListener() {
            @Override
            public void exitMustExistRule(KontextParser.MustExistRuleContext ctx) {
                Assertions.assertEquals(2, ctx.fk().size());
                super.exitMustExistRule(ctx);
            }
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
            @Override
            public void exitMustExistRule(KontextParser.MustExistRuleContext ctx) {
                Assertions.assertEquals(3, ctx.fk().size());
                super.exitMustExistRule(ctx);
            }
        };
        parser.addParseListener(kontextBaseListener);
        parser.regel();
    }

    @Test
    public void testWennRule() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Inhalt von FK 7260 = 4 muss FK 7352 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        KontextBaseListener kontextBaseListener = new KontextBaseListener() {
            @Override
            public void exitIfRuleMust(KontextParser.IfRuleMustContext ctx) {
                for (var fkInitialized : ctx.fkInitialized()) {
                    System.err.println(fkInitialized.fk().INTEGER() + "=" + fkInitialized.values().INTEGER() + " -> " + ctx.fk().INTEGER());
                }
                super.exitIfRuleMust(ctx);
            }
        };
        parser.addParseListener(kontextBaseListener);
        parser.regel();
    }

    @Test
    public void testWennAndWennNicht() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Feldinhalt von FK 8419 = 1 oder 2, muss FK 8421 vorkommen. Wenn Feldinhalt von FK 8419 = 9, darf FK 8421 nicht vorkommen."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        KontextBaseListener kontextBaseListener = new KontextBaseListener() {
            @Override
            public void exitIfRuleMust(KontextParser.IfRuleMustContext ctx) {
                for (var fkInitialized : ctx.fkInitialized()) {
                    System.err.println(fkInitialized.fk().INTEGER() + "=" + fkInitialized.values().INTEGER() + " -> " + ctx.fk().INTEGER());
                }
                super.exitIfRuleMust(ctx);
            }

            @Override
            public void exitIfRuleMustNot(KontextParser.IfRuleMustNotContext ctx) {
                for (var fkInitialized : ctx.fkInitialized()) {
                    System.err.println(fkInitialized.fk().INTEGER() + "=" + fkInitialized.values().INTEGER() + " -> !" + ctx.fk().INTEGER());
                }
                super.exitIfRuleMustNot(ctx);
            }
        };
        parser.addParseListener(kontextBaseListener);
        parser.regel();
    }

    @Test
    public void testK005() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 1, darf FK 4121 nicht vorhanden sein. Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 2, kann FK 4121 vorhanden sein."));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        KontextBaseListener kontextBaseListener = new KontextBaseListener() {
            @Override
            public void exitIfRuleMust(KontextParser.IfRuleMustContext ctx) {
                for (var fkInitialized : ctx.fkInitialized()) {
                    System.err.println(fkInitialized.fk().INTEGER() + "=" + fkInitialized.values().INTEGER() + " -> " + ctx.fk().INTEGER());
                }
                super.exitIfRuleMust(ctx);
            }

            @Override
            public void exitIfRuleMustNot(KontextParser.IfRuleMustNotContext ctx) {
                for (var fkInitialized : ctx.fkInitialized()) {
                    System.err.println(fkInitialized.fk().INTEGER() + "=" + fkInitialized.values().INTEGER() + " -> !" + ctx.fk().INTEGER());
                }
                super.exitIfRuleMustNot(ctx);
            }
        };
        parser.addParseListener(kontextBaseListener);
        parser.regel();
    }

}
