package libldt3.ruleparser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.gui.TreeViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class KontextParserTest {

    private static class FailErrorListener extends BaseErrorListener {

        private List<String> failures = new ArrayList<>();

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            failures.add("line " + line + ":" + charPositionInLine + " " + msg);
        }

        public boolean hasFailed() {
            return !failures.isEmpty();
        }
    }

    private static Stream<Arguments> allKontextRules() {
        return Stream.of(
<#list regeln as regel>            Arguments.of("${regel.regelnummer}", "${regel.pruefung}")<#sep>,
            </#list>
        );
    }

    @ParameterizedTest
    @MethodSource("allKontextRules")
    public void testKontextRules(String rule, String text) throws InterruptedException, ExecutionException {
        KontextLexer lexer = new KontextLexer(CharStreams.fromString(text));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KontextParser parser = new KontextParser(tokens);
        parser.removeErrorListeners();
        FailErrorListener listener = new FailErrorListener();
        parser.addErrorListener(listener);
        ParseTree tree = parser.regel();
        if (listener.hasFailed()) {
            if ("true".equals(System.getenv("SHOW_TREE_UI"))) {
                openTreeUi(parser, tree);
            }
            Assertions.fail(rule + ": " + text + "\n" + tree.toStringTree(parser) + "\n" + String.join(";", listener.failures));
        }
    }

    private static void openTreeUi(KontextParser parser, ParseTree tree) throws InterruptedException, ExecutionException {
        JFrame dialog = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree).open().get();
        Object lock = new Object();
        Thread t = new Thread(() -> {
            synchronized(lock) {
                while (dialog.isVisible()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                synchronized (lock) {
                    dialog.setVisible(false);
                    lock.notify();
                }
            }
        });
        t.join();
    }

}
