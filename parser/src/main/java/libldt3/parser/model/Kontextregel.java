package libldt3.parser.model;

import libldt3.ruleparser.KontextBaseListener;
import libldt3.ruleparser.KontextLexer;
import libldt3.ruleparser.KontextParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kontextregel extends Regel {

    public static class FeldInitialized {
        public Feld feld;
        public String init;

        public FeldInitialized(Feld feld, String init) {
            this.feld = feld;
            this.init = init;
        }
    }

    public static class MustRule {
        public String comment;
        public List<FeldInitialized> felder = new ArrayList<>();
        public Feld must;
        public boolean inverted;
    }

    public List<Feld> mandatoryFields;
    public List<Feld> possibleFields;
    public List<Feld> usedFields;
    public List<MustRule> mustRules;

    private final Map<String, Feld> felder;

    public Kontextregel(Map<String, Feld> felder) {
        this.felder = felder;
    }

    public List<Feld> getUsedFields() {
        if (usedFields == null) {
            usedFields = new ArrayList<>();
            mandatoryFields = new ArrayList<>();
            possibleFields = new ArrayList<>();
            mustRules = new ArrayList<>();
            KontextLexer lexer = new KontextLexer(CharStreams.fromString(pruefung));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            KontextParser parser = new KontextParser(tokens);
            KontextBaseListener kontextBaseListener = new KontextBaseListener() {

                @Override
                public void exitMustExistRule(KontextParser.MustExistRuleContext ctx) {
                    for (var x : ctx.fk()) {
                        String fk = x.INTEGER().toString();
                        Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                        if (!usedFields.contains(e)) {
                            usedFields.add(e);
                        }
                        mandatoryFields.add(e);
                    }
                    super.exitMustExistRule(ctx);
                }

                @Override
                public void enterCanExistRule(KontextParser.CanExistRuleContext ctx) {
                    for (var x : ctx.fk()) {
                        String fk = x.INTEGER().toString();
                        Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                        if (!usedFields.contains(e)) {
                            usedFields.add(e);
                        }
                        possibleFields.add(e);
                    }
                    super.enterCanExistRule(ctx);
                }

                private String getSpacedText(RuleContext ctx) {
                    if (ctx.getChildCount() == 0) {
                        return "";
                    }

                    return IntStream
                            .range(0, ctx.getChildCount())
                            .mapToObj(i -> ctx.getChild(i).getText())
                            .collect(Collectors.joining(" "));
                }

                @Override
                public void exitIfRuleMust(KontextParser.IfRuleMustContext ctx) {
                    MustRule rule = new MustRule();
                    rule.comment = getSpacedText(ctx);
                    // Workaround for incomplete parsing
                    if (ctx.fkInitialized() != null) {
                        for (var fkInitialized : ctx.fkInitialized()) {
                            String fk = fkInitialized.fk().INTEGER().toString();
                            Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                            if (!usedFields.contains(e)) {
                                usedFields.add(e);
                            }
                            for (var i : fkInitialized.values().INTEGER()) {
                                rule.felder.add(new FeldInitialized(e, i.toString()));
                            }
                        }
                    }
                    // Workaround for incomplete parsing
                    if (ctx.fk() != null) {
                        String fk = ctx.fk().INTEGER().toString();
                        Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                        if (!usedFields.contains(e)) {
                            usedFields.add(e);
                        }
                        rule.must = e;
                    }
                    mustRules.add(rule);
                    super.exitIfRuleMust(ctx);
                }

                @Override
                public void exitIfRuleMustNot(KontextParser.IfRuleMustNotContext ctx) {
                    MustRule rule = new MustRule();
                    rule.comment = getSpacedText(ctx);
                    rule.inverted = true;
                    // Workaround for incomplete parsing
                    if (ctx.fkInitialized() != null) {
                        for (var fkInitialized : ctx.fkInitialized()) {
                            String fk = fkInitialized.fk().INTEGER().toString();
                            Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                            if (!usedFields.contains(e)) {
                                usedFields.add(e);
                            }
                            for (var i : fkInitialized.values().INTEGER()) {
                                rule.felder.add(new FeldInitialized(e, i.toString()));
                            }
                        }
                    }
                    // Workaround for incomplete parsing
                    if (ctx.fk() != null) {
                        String fk = ctx.fk().INTEGER().toString();
                        Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                        if (!usedFields.contains(e)) {
                            usedFields.add(e);
                        }
                        rule.must = e;
                    }
                    mustRules.add(rule);
                    super.exitIfRuleMustNot(ctx);
                }
            };
            parser.addParseListener(kontextBaseListener);
            parser.regel();
        }
        return usedFields;
    }
}
