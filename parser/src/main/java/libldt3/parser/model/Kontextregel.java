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
        public boolean inverted;

        public FeldInitialized(Feld feld, String init, boolean inverted) {
            this.feld = feld;
            this.init = init;
            this.inverted = inverted;
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
                    for (var x : ctx.fieldExists().fk()) {
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

                    return ctx.getPayload().getText();

                    /*return IntStream
                            .range(0, ctx.getChildCount())
                            .mapToObj(i -> ctx.getChild(i).getText())
                            .collect(Collectors.joining(" "));*/
                }

                @Override
                public void exitIfThenValue(KontextParser.IfThenValueContext ctx) {
                    MustRule rule = new MustRule();
                    rule.comment = getSpacedText(ctx);
                    for (var ifBody : ctx.ifCondition()) {
                        extracted(ifBody.fieldExistsOrHasSpecificValue().fieldContent(), rule);
                    }
                    for (var x : ctx.fieldExistsOrHasSpecificValue().fieldExists()) {
                        for (var y : x.fk()) {
                            String fk = y.INTEGER().toString();
                            Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                            if (!usedFields.contains(e)) {
                                usedFields.add(e);
                            }
                            // FIXME: Needs to be a list and adhere to boolean combinations of the list
                            rule.must = e;
                        }
                    }
                    mustRules.add(rule);
                    super.exitIfThenValue(ctx);
                }

                private void extracted(List<KontextParser.FieldContentContext> ctx, MustRule rule) {
                    if (ctx != null) {
                        for (var inhalt : ctx) {
                            var fieldAssignment = inhalt.fieldAssignment();
                            String fk = fieldAssignment.fk().INTEGER().toString();
                            Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                            if (!usedFields.contains(e)) {
                                usedFields.add(e);
                            }
                            for (var i : fieldAssignment.INTEGER()) {
                                String value = i.toString();
                                boolean inverted = fieldAssignment.fieldAssignmentOperator().fieldAssignmentOperatorEquals() == null || fieldAssignment.fieldAssignmentOperator().fieldAssignmentOperatorEquals().isEmpty();
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
                                rule.felder.add(new FeldInitialized(e, value, inverted));
                            }
                        }
                    }
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
