package de.egore911.libldt3.transpiler.directives.rust;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Pattern;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class SnakeCaseDirective implements TemplateDirectiveModel {

    private static final Pattern PATTERN = Pattern.compile("([a-z])([A-Z])");

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        String name = ((SimpleScalar) params.get("name")).getAsString();

        boolean uppercase = false;
        TemplateBooleanModel m = (TemplateBooleanModel) params.get("uppercase");
        if (m != null) {
            uppercase = m.getAsBoolean();
        }

        name = toSnakeCase(name, uppercase);
        Writer out = env.getOut();
        out.append(name.toString());
    }

    private static String toSnakeCase(String name, boolean uppercase) {
        String mixedCase = PATTERN.matcher(((String) name)).replaceAll("$1_$2");
        return uppercase ? mixedCase.toUpperCase() : mixedCase.toLowerCase();
    }

}
