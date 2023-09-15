package libldt3.parser.model;

import libldt3.parser.RegelNaming;
import libldt3.ruleparser.KontextBaseListener;
import libldt3.ruleparser.KontextLexer;
import libldt3.ruleparser.KontextParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Kontextregel extends Regel {

    private static final Logger LOG = LoggerFactory.getLogger(Kontextregel.class);

    public static class RuleCondition {
        public Feld feld;
        public String init;

        public RuleCondition(Feld feld, String init) {
            this.feld = feld;
            this.init = init;
        }
    }

    public static class MustRule {
        public String comment;
        public List<RuleCondition> conditions = new ArrayList<>();
        public Feld must;
        public boolean inverted;
    }

    public List<Feld> excludingFields;
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
            excludingFields = new ArrayList<>();
            mandatoryFields = new ArrayList<>();
            mustRules = new ArrayList<>();
            KontextLexer lexer = new KontextLexer(CharStreams.fromString(pruefung));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            KontextParser parser = new KontextParser(tokens);
            KontextBaseListener kontextBaseListener = new KontextBaseListener() {

                @Override
                public void exitEitherFieldExists(KontextParser.EitherFieldExistsContext ctx) {
                    for (var x : ctx.fieldExists().fk()) {
                        for (var fki : x.INTEGER()) {
                            String fk = fki.toString();
                            Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                            if (!usedFields.contains(e)) {
                                usedFields.add(e);
                            }
                            mandatoryFields.add(e);
                        }
                    }
                    super.exitEitherFieldExists(ctx);
                }

                @Override
                public void exitEitherFieldExistsInverted(KontextParser.EitherFieldExistsInvertedContext ctx) {
                    for (var y : ctx.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement()) {
                        if (y.fieldExists() != null) {
                            for (var x : y.fieldExists().fk()) {
                                for (var fki : x.INTEGER()) {
                                    String fk = fki.toString();
                                    Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                                    if (!usedFields.contains(e)) {
                                        usedFields.add(e);
                                    }
                                    mandatoryFields.add(e);
                                }
                            }
                        }
                    }
                    super.exitEitherFieldExistsInverted(ctx);
                }

                @Override
                public void exitEitherFieldExistsExclusion(KontextParser.EitherFieldExistsExclusionContext ctx) {
                    excludingFields.addAll(mandatoryFields);
                    mandatoryFields.clear();
                    super.exitEitherFieldExistsExclusion(ctx);
                }

                // TODO honor eitherFieldExistsExclusion

                @Override
                public void exitAnyFieldExists(KontextParser.AnyFieldExistsContext ctx) {
                    for (var x : ctx.fk()) {
                        for (var fki : x.INTEGER()) {
                            String fk = fki.toString();
                            Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                            if (!usedFields.contains(e)) {
                                usedFields.add(e);
                            }
                            mandatoryFields.add(e);
                        }
                    }
                    super.exitAnyFieldExists(ctx);
                }

                private String getOriginalText(ParserRuleContext ctx) {
                   return pruefung.substring(ctx.getStart().getCharPositionInLine(), ctx.getStop().getStopIndex());
                }

                @Override
                public void exitIfThenFieldExistsOrValue(KontextParser.IfThenFieldExistsOrValueContext ctx) {
                    MustRule rule = new MustRule();
                    rule.comment = getOriginalText(ctx);
                    for (var ifBody : ctx.ifCondition()) {
                        for (var fieldExistsOrHasSpecificValue : ifBody.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement()) {
                            extracted(fieldExistsOrHasSpecificValue.fieldContent(), rule);
                        }
                    }
                    for (var z : ctx.fieldExistsOrHasSpecificValue().fieldExistsOrHasSpecificValueElement()) {
                        if (z.fieldExists() == null) {
                            LOG.warn("Cannot handle {} yet", z);
                            continue;
                        }
                        var x = z.fieldExists();
                        for (var y : x.fk()) {
                            for (var fki : y.INTEGER()) {
                                String fk = fki.toString();
                                Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                                if (!usedFields.contains(e)) {
                                    usedFields.add(e);
                                }
                                // FIXME: Needs to be a list and adhere to boolean combinations of the list
                                rule.must = e;
                            }
                        }
                    }
                    if (!rule.conditions.isEmpty()) {
                        if (rule.must != null && rule.must.fk != null) {
                            mustRules.add(rule);
                        } else {
                            LOG.warn("Got rule {} without field", rule);
                        }
                    } else {
                        LOG.warn("Got rule {} without any fields", rule);
                    }
                    super.exitIfThenFieldExistsOrValue(ctx);
                }

                private void extracted(KontextParser.FieldContentContext ctx, MustRule rule) {
                    if (ctx != null) {
                            var fieldAssignment = ctx.fieldAssignment();
                            if (fieldAssignment.fk() == null) {
                                LOG.warn("Got assignment without any fields");
                                return;
                            }
                            for (var fki : fieldAssignment.fk().INTEGER()) {
                                String fk = fki.toString();
                                Feld e = felder.computeIfAbsent(fk, (k) -> new Feld(fk));
                                if (!usedFields.contains(e)) {
                                    usedFields.add(e);
                                }
                                for (var fav : fieldAssignment.fieldAssignmentValue()) {
                                    if (fav.INTEGER() == null) {
                                        LOG.warn("Cannot handle {} yet", fav);
                                        continue;
                                    }
                                    String value = fav.INTEGER().toString();
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
                                    rule.conditions.add(new RuleCondition(e, value));
                                    // FIXME: wrong, as this needs to be done per condition
                                    rule.inverted = inverted;
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
            for (var feld : rule.conditions) {
                result.add(feld.feld);
            }
        }
        return result;
    }

    public TreeSet<String> getEnumImports() {
        TreeSet<String> result = new TreeSet<>();
        for (var rule : mustRules) {
            for (var feld : rule.conditions) {
                result.addAll(feld.feld.getEnumImports());
            }
        }
        return result;
    }

}
