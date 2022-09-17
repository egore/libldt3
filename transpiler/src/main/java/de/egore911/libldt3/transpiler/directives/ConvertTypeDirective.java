package de.egore911.libldt3.transpiler.directives;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.stream.Collectors;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.reference.CtTypeReference;

public class ConvertTypeDirective implements TemplateDirectiveModel {

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		
		CtTypeReference<?> type = (CtTypeReference<?>) (((BeanModel) params.get("type")).getWrappedObject());
		boolean withNullability = true;
		TemplateBooleanModel booleanModel = (TemplateBooleanModel) params.get("with_nullability");
		if (booleanModel != null) {
			withNullability = booleanModel.getAsBoolean();
		}
		
		Writer out = env.getOut();
		String qualifiedName = convertType(type, withNullability);
		out.append(qualifiedName);
	}

	private static String convertType(CtTypeReference<?> type, boolean withNullability) {
		String name;
		switch (type.getQualifiedName()) {
		case "boolean": name = "bool"; break;
		case "java.lang.Boolean": name = "bool?"; break;
		case "java.lang.Float": name = "float?"; break;
		case "java.lang.Integer": name = "int?"; break;
		case "java.lang.Object": name = "object"; break;
		case "java.lang.String": name = "string"; break;
		case "java.util.HashMap": name = "Dictionary"; break;
		case "java.util.List": name = "IList"; break;
		case "java.util.Map": name = "IDictionary"; break;
		case "java.util.Set": name = "ISet"; break;
		case "java.util.regex.Pattern": name = "Regex"; break;
		default: name = type.getSimpleName(); break;
		}
		if (withNullability && (type.isEnum() || type.getQualifiedName().startsWith("java.time."))) {
			name += "?";
		}
		if (type.getActualTypeArguments() != null && !type.getActualTypeArguments().isEmpty()) {
			name += "<" + type.getActualTypeArguments()
			.stream()
			.map(x -> convertType(x, withNullability))
			.collect(Collectors.joining(", "))+ ">";
		}
		return name;
	}

}