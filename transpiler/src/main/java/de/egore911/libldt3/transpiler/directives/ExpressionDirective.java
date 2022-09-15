package de.egore911.libldt3.transpiler.directives;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.code.CtTypeAccess;

public class ExpressionDirective implements TemplateDirectiveModel {

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		
		CtExpression<?> expression = (CtExpression<?>) (((StringModel) params.get("expression")).getWrappedObject());
		boolean forceArray = false;
		TemplateBooleanModel forceArrayModel = (TemplateBooleanModel) params.get("force_array");
		if (forceArrayModel != null) {
			forceArray = forceArrayModel.getAsBoolean();
		}
		
		Writer out = env.getOut();
		
		renderExpression(expression, out, forceArray);
		
	}

	private void renderExpression(CtExpression<?> expression, Writer out, boolean forceArray) throws IOException {
		if (expression instanceof CtLiteral<?>) {
			Object value = ((CtLiteral<?>) expression).getValue();
			if (value instanceof String) {
				out.append("\"").append((String) value).append("\"");
			} else {
				out.append(value != null ? value.toString() : "null");
			}
		} else if (expression instanceof CtNewArray<?>) {
			CtNewArray<?> newArray = (CtNewArray<?>) expression;
			out.append("new [] { ");
			boolean first = true;
			for (CtExpression<?> x : newArray.getElements()) {
				if (first) {
					first = false;
				} else {
					out.append(", ");
				}
				renderExpression(x, out, false);
			}
			out.append(" }");
		} else if (expression instanceof CtFieldRead<?>) {
			if (forceArray) {
				out.append("new [] { ");
			}
			CtFieldRead<?> fieldRead = (CtFieldRead<?>) expression;
			String fieldName = fieldRead.getVariable().getSimpleName();
			String typeName = ((CtTypeAccess<?>) fieldRead.getTarget()).getAccessedType().getSimpleName();
			if (fieldName.equals("class")) {
				out.append("typeof(").append(typeName).append(")");
			} else {
				out.append(typeName).append(".").append(fieldName);
			}
			if (forceArray) {
				out.append(" }");
			}
		} else if (expression instanceof CtConstructorCall<?>) {
			System.err.print(expression);
		} else {
			throw new UnsupportedOperationException(expression.getClass().getSimpleName());
		}
	}

}
