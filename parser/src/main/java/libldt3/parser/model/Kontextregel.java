package libldt3.parser.model;

import libldt3.parser.RegelNaming;
import libldt3.ruleparser.KontextBaseListener;
import libldt3.ruleparser.KontextLexer;
import libldt3.ruleparser.KontextParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
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
    public List<Feld> usedFields;
    public List<MustRule> mustRules;

    private final Map<String, Feld> felder;
    private final Map<String, Regel> regeln;

    public Kontextregel(Map<String, Feld> felder, Map<String, Regel> regeln) {
        this.felder = felder;
        this.regeln = regeln;
    }

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
                public void exitEitherExists(KontextParser.EitherExistsContext ctx) {
                    for (var x : ctx.exists().fk()) {
                        String fk = x.INTEGER().toString();
                        Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                        if (!usedFields.contains(e)) {
                            usedFields.add(e);
                        }
                        mandatoryFields.add(e);
                    }
                    super.exitEitherExists(ctx);
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
                    extracted(ctx.inhaltExists().inhalt(), rule);
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
                public void exitIfRuleMay(KontextParser.IfRuleMayContext ctx) {
                    MustRule rule = new MustRule();
                    rule.comment = getSpacedText(ctx);
                    // Workaround for incomplete parsing
                    extracted(ctx.inhaltExists().inhalt(), rule);
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
                    super.exitIfRuleMay(ctx);
                }

                private void extracted(List<KontextParser.InhaltContext> ctx, MustRule rule) {
                    if (ctx != null) {
                        for (var inhalt : ctx) {
                            var fkAssignment = inhalt.fkAssignment();
                            String fk = fkAssignment.fk().INTEGER().toString();
                            Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                            if (!usedFields.contains(e)) {
                                usedFields.add(e);
                            }
                            for (var i : fkAssignment.values().INTEGER()) {
                                String value = i.toString();
                                outer:
                                for (Regel r : e.regeln) {
                                    if (r.regelnummer.startsWith("E")) {
                                        ErlaubterInhalt regel = (ErlaubterInhalt) regeln.get(r.regelnummer);
                                        for (var x : regel.getMultiple()) {
                                            if (x.code.equals(value)) {
                                                value = RegelNaming.REPLACEMENTS.get(r.regelnummer) + "." + x.value;
                                                break outer;
                                            }
                                        }
                                    }
                                }
                                rule.felder.add(new FeldInitialized(e, value));
                            }
                        }
                    }
                }

                @Override
                public void exitIfRuleMustNot(KontextParser.IfRuleMustNotContext ctx) {
                    MustRule rule = new MustRule();
                    rule.comment = getSpacedText(ctx);
                    rule.inverted = true;
                    // Workaround for incomplete parsing
                    extracted(ctx.inhaltExists().inhalt(), rule);
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

    public TreeSet<Feld> getMustRuleFields() {
        TreeSet<Feld> result = new TreeSet<>();
        for (var rule : mustRules) {
            for (var feld : rule.felder) {
                result.add(feld.feld);
            }
        }
        return result;
    }

}
