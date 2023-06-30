package libldt3.ruleparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrammarTest {

    @Test
    void testWithoutName() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Obj_0001"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        KontextParser.ObjektContext ctx = parser.objekt();
        Assertions.assertEquals("0001", ctx.INTEGER().toString());
    }

    @Test
    void testWithProperNameName() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Obj_0001 (Obj_Abrechnungsinformationen)"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        KontextParser.ObjektContext ctx = parser.objekt();
        Assertions.assertEquals("0001", ctx.INTEGER().toString());
    }

    @Test
    void testWithImproperNameName() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Obj_0045 (Patient)"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        KontextParser.ObjektContext ctx = parser.objekt();
        Assertions.assertEquals("0045", ctx.INTEGER().toString());
    }

    @Test
    void testAssignment() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Inhalt von FK 7420 = 12"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        KontextParser.IfConditionContext ctx = parser.ifCondition();
        Assertions.assertEquals(1, ctx.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement().size());
    }

    @Test
    void testAssignment2() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("FK 7303 mit den Werten 1, 2, 3, 8, 9 oder 10 in jeweiliger Satzart 8205 oder 8215 vorkommen"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        KontextParser.IfConditionContext ctx = parser.ifCondition();
        Assertions.assertEquals(1, ctx.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement().size());
    }

    @Test
    void testAssignmentAnd() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("Inhalt von FK 7420 = 12 und FK 7303 mit den Werten 1, 2, 3, 8, 9 oder 10 in jeweiliger Satzart 8205 oder 8215 vorkommen"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        KontextParser.IfConditionContext ctx = parser.ifCondition();
        Assertions.assertEquals(2, ctx.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement().size());
        Assertions.assertEquals("FK7420", ctx.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement().get(0).fieldContent().fieldAssignment().fk().getText());
        Assertions.assertEquals(1, ctx.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement().get(0).fieldContent().fieldAssignment().fieldAssignmentValue().size());
        Assertions.assertEquals(6, ctx.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement().get(1).fieldContent().fieldAssignment().fieldAssignmentValue().size());
    }

    @Test
    public void testIfConditionK114() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("der Inhalt von FK 8000 = 8215 und der Inhalt von FK 7303 in mindestens einem Obj_0059 mit dem Wert 99 vorkommt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        KontextParser.FieldExistsOrHasSpecificValueContext ctx = parser.fieldExistsOrHasSpecificValue();
        Assertions.assertEquals(2, ctx.fieldExistsOrHasSpecificValueElement().size());

        // FK 8000 = 8215
        Assertions.assertEquals("8000", ctx.fieldExistsOrHasSpecificValueElement().get(0).fieldContent().fieldAssignment().fk().INTEGER().getText());
        Assertions.assertEquals(1, ctx.fieldExistsOrHasSpecificValueElement().get(0).fieldContent().fieldAssignment().fieldAssignmentValue().size());

        // Inhalt von FK 7303 in mindestens einem Obj_0059 mit dem Wert 99
        Assertions.assertEquals("7303", ctx.fieldExistsOrHasSpecificValueElement().get(1).fieldContent().fieldAssignment().fk().INTEGER().getText());
        Assertions.assertEquals(1, ctx.fieldExistsOrHasSpecificValueElement().get(1).fieldContent().fieldAssignment().fieldAssignmentValue().size());
        Assertions.assertEquals("99", ctx.fieldExistsOrHasSpecificValueElement().get(1).fieldContent().fieldAssignment().fieldAssignmentValue().get(0).getText());
    }

    @Test
    public void testFieldExists() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("die FK 3103, FK 3110 und FK 8228 vorhanden sein"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        KontextParser.FieldExistsOrHasSpecificValueContext ctx = parser.fieldExistsOrHasSpecificValue();
        Assertions.assertEquals(1, ctx.fieldExistsOrHasSpecificValueElement().size());
        Assertions.assertEquals(3, ctx.fieldExistsOrHasSpecificValueElement().get(0).fieldExists().fk().size());
    }


}
