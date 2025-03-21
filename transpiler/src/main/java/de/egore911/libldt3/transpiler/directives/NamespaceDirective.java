package de.egore911.libldt3.transpiler.directives;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class NamespaceDirective implements TemplateDirectiveModel {

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        TemplateModel p = (TemplateModel) params.get("package");
        if (!(p instanceof StringModel)) {
            throw new UnsupportedOperationException("Cannot handle class template model " + p.getClass().getSimpleName());
        }

        Writer writer = env.getOut();

        String namespace = ((StringModel) p).getAsString();
        String[] parts = namespace.split("\\.");
        int length = parts.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                writer.append("    ");
            }
            writer.append("namespace ");
            writer.append(parts[i]);
            writer.append("\n");
            for (int j = 0; j < i; j++) {
                writer.append("    ");
            }
            writer.append("{\n");
        }

        body.render(writer);

        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j > i; j--) {
                writer.append("    ");
            }
            writer.append("}\n");
        }
    }

}
