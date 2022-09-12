package de.egore911.libldt3.transpiler;

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
		Writer writer = env.getOut();
		int length = 1;
		if (p instanceof StringModel) {
			String namespace = ((StringModel) p).getAsString();
			String[] parts = namespace.split("\\.");
			length = parts.length;
			for (int i = 0; i < length; i++) {
				if (i != 0) {
					writer.append(" ");
				}
				writer.append("namespace ");
				writer.append(parts[i]);
				writer.append(" {");
			}
			writer.append("\n");
		} else {
			throw new UnsupportedOperationException("Cannot handle class template model " + p.getClass().getSimpleName());
		}
		body.render(writer);
		for (int i = 0; i < length; i++) {
			if (i != 0) {
				writer.append(" ");
			}
			writer.append("}");
		}
		writer.append("\n");
	}

}
