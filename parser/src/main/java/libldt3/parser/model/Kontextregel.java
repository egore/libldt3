package libldt3.parser.model;

import libldt3.ruleparser.KontextBaseListener;
import libldt3.ruleparser.KontextLexer;
import libldt3.ruleparser.KontextParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;

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
    }

    public List<Feld> mandatoryFields;
    public List<Feld> usedFields;
    public List<MustRule> mustRules;

    public List<Feld> getUsedFields() {
        if (usedFields == null) {
            usedFields = new ArrayList<>();
            mandatoryFields = new ArrayList<>();
            mustRules = new ArrayList<>();
            KontextLexer lexer = new KontextLexer(CharStreams.fromString(pruefung));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            KontextParser parser = new KontextParser(tokens);
            KontextBaseListener kontextBaseListener = new KontextBaseListener() {
                @Override
                public void exitOrRule(KontextParser.OrRuleContext ctx) {
                    for (var x : ctx.fk()) {
                        Feld e = new Feld(x.INTEGER().toString());
                        if (!usedFields.contains(e)) {
                            usedFields.add(e);
                        }
                        mandatoryFields.add(e);
                    }
                    super.exitOrRule(ctx);
                }

                @Override
                public void exitIfRuleMust(KontextParser.IfRuleMustContext ctx) {
                    MustRule rule = new MustRule();
                    rule.comment = ctx.getText();
                    // Workaround for incomplete parsing
                    if (ctx.fkInitialized() != null) {
                        for (var fkInitialized : ctx.fkInitialized()) {
                            Feld e = new Feld(fkInitialized.fk().INTEGER().toString());
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
                        Feld e = new Feld(ctx.fk().INTEGER().toString());
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
                    // Workaround for incomplete parsing
                    if (ctx.fkInitialized() != null) {
                        for (var fkInitialized : ctx.fkInitialized()) {
                            Feld e = new Feld(fkInitialized.fk().INTEGER().toString());
                            if (!usedFields.contains(e)) {
                                usedFields.add(e);
                            }
                        }
                    }
                    // Workaround for incomplete parsing
                    if (ctx.fk() != null) {
                        Feld e = new Feld(ctx.fk().INTEGER().toString());
                        if (!usedFields.contains(e)) {
                            usedFields.add(e);
                        }
                    }
                    //System.err.println(ctx.fkInitialized().fk().INTEGER() + "=" + ctx.fkInitialized().values().INTEGER() + " -> !" + ctx.fk().INTEGER());
                    super.exitIfRuleMustNot(ctx);
                }
            };
            parser.addParseListener(kontextBaseListener);
            parser.regel();
        }
        return usedFields;
    }
}
