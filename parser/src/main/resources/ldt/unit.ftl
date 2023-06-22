package libldt3.ruleparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KontextParserTest {

<#list regeln as regel>
    @Test
    public void test${regel.regelnummer}() {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString("${regel.pruefung}"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(FAIL_ERROR_LISTENER);
        parser.regel();
    }

</#list>
}
