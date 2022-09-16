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
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.code.CtThisAccess;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.code.CtVariableAccess;

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
			if (fieldRead.getTarget() instanceof CtTypeAccess<?>) {
			String typeName = ((CtTypeAccess<?>) fieldRead.getTarget()).getAccessedType().getSimpleName();
			if (fieldName.equals("class")) {
				out.append("typeof(").append(typeName).append(")");
			} else {
				out.append(typeName).append(".").append(fieldName);
			}
			} else if (fieldRead.getTarget() instanceof CtThisAccess<?>) {
				out.append("this").append(".").append(fieldName);
			} else {
				throw new UnsupportedOperationException(fieldRead.getTarget().getClass().getSimpleName());
			}
			if (forceArray) {
				out.append(" }");
			}
		} else if (expression instanceof CtVariableAccess<?>) {
			CtVariableAccess<?> variableRead = (CtVariableAccess<?>) expression;
			out.append(variableRead.getVariable().getSimpleName());
		} else if (expression instanceof CtBinaryOperator<?>) {
			CtBinaryOperator<?> binaryOperator = (CtBinaryOperator<?>) expression;
			renderExpression(binaryOperator.getLeftHandOperand(), out, false);
			switch (binaryOperator.getKind()) {
				case AND: out.append(" && "); break;
				case BITAND: out.append(" & "); break;
				case BITOR: out.append(" | "); break;
				case BITXOR: out.append(" ^ "); break;
				case DIV: out.append(" / "); break;
				case EQ: out.append(" == "); break;
				case GE: out.append(" >= "); break;
				case GT: out.append(" > "); break;
				case LE: out.append(" < "); break;
				case LT: out.append(" <= "); break;
				case MINUS: out.append(" - "); break;
				case MOD: out.append(" % "); break;
				case MUL: out.append(" * "); break;
				case NE: out.append(" != "); break;
				case OR: out.append(" || "); break;
				case PLUS: out.append(" + "); break;
				case SL: out.append(" << "); break;
				case SR: out.append(" >> "); break;
				case USR: out.append(" <<< "); break;
				default: throw new UnsupportedOperationException(binaryOperator.getKind().toString());
			}
			renderExpression(binaryOperator.getRightHandOperand(), out, false);
		} else if (expression instanceof CtInvocation<?>) {
			CtInvocation<?> invocation = (CtInvocation<?>) expression;
			if (invocation.getTarget() != null) {
				renderExpression(invocation.getTarget(), out, false);
				out.append('.');
			}
			out.append(invocation.getExecutable().getSimpleName());
			out.append('(');
			boolean first = true;
			for (CtExpression<?> ex : invocation.getArguments()) {
				if (first) {
					first = false;
				} else {
					out.append(", ");
				}
				renderExpression(ex, out, false);
			}
			out.append(')');
		} else {
			throw new UnsupportedOperationException(expression.getClass().getSimpleName());
		}
	}

}
