package de.egore911.libldt3.transpiler.directives;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.CtTypeMember;
import spoon.reflect.reference.CtPackageReference;
import spoon.reflect.reference.CtTypeReference;

public class GenUsingDirective implements TemplateDirectiveModel {

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		CtClass<?> class_ = (CtClass<?>) (((BeanModel) (TemplateModel) params.get("class")).getWrappedObject());
		
		// Collect all things for "using ..." statements
		Set<String> usings = new TreeSet<String>();
		
		addForClass(class_, usings);
		
		Writer out = env.getOut();
		for (String using : usings) {
			out.append("using ").append(using).append(";\n");
		}
		
		
	}

	private void addForClass(CtClass<?> class_, Set<String> usings) {
		// 1.) Parents
		if (class_.getSuperclass() != null) {
			addIfDifferent(usings, class_.getPackage(), class_.getSuperclass().getPackage());
		}
		if (class_.getSuperInterfaces() != null) {
			for (CtTypeReference<?> superInterface : class_.getSuperInterfaces()) {
				addIfDifferent(usings, class_.getPackage(), superInterface.getPackage());
			}
		}
		
		// 2.) Fields
		if (class_.getFields() != null) {
			for (CtField<?> field : class_.getFields()) {
				addFromType(class_, usings, field.getType());
				// Add annotations, if available
				if (field.getAnnotations() != null) {
					for (CtAnnotation<?> annotation : field.getAnnotations()) {
						addFromAnnotation(class_, usings, annotation);
					}
				}
			}
		}
		
		// 3.) Method arguments and return values
		if (class_.getMethods() != null) {
			for (CtMethod<?> method : class_.getMethods()) {
				// Add return value
				addFromType(class_, usings, method.getType());
				// Add parameters, if available
				if (method.getParameters() != null) {
					for (CtParameter<?> parameter : method.getParameters()) {
						for (CtTypeReference<?> ref : parameter.getReferencedTypes()) {
							addFromType(class_, usings, ref);
						}
					}
				}
				// Add thrown types, if available
				if (method.getThrownTypes() != null) {
					for (CtTypeReference<?> thrownType : method.getThrownTypes()) {
						addIfDifferent(usings, class_.getPackage(), thrownType.getPackage());
					}
				}
				// Add annotations, if available
				if (method.getAnnotations() != null) {
					for (CtAnnotation<?> annotation : method.getAnnotations()) {
						addFromAnnotation(class_, usings, annotation);
					}
				}
			}
		}
		
		// 4.) Annotations
		if (class_.getAnnotations() != null) {
			for (CtAnnotation<?> annotation : class_.getAnnotations()) {
				addFromAnnotation(class_, usings, annotation);
			}
		}
		
		// 5.) Child classes
		for (CtType<?> member : class_.getNestedTypes()) {
			if (member instanceof CtClass<?>) {
				addForClass((CtClass<?>) member, usings);
			} else {
				throw new UnsupportedOperationException(member.getClass().getSimpleName());
			}
		}
	}

	private void addFromType(CtClass<?> class_, Set<String> usings, CtTypeReference<?> type) {
		// Add type itself
		addIfDifferent(usings, class_.getPackage(), type.getPackage());
		// Add generics, if available
		if (type.getActualTypeArguments() != null) {
			for (CtTypeReference<?> argument : type.getActualTypeArguments()) {
				addIfDifferent(usings, class_.getPackage(), argument.getPackage());
			}
		}
	}

	private void addFromAnnotation(CtClass<?> class_, Set<String> usings, CtAnnotation<?> annotation) {
		addIfDifferent(usings, class_.getPackage(), annotation.getType().getPackage());
		for (CtExpression<?> expression : annotation.getValues().values()) {
			addFromExpression(class_, usings, expression);
		}
	}

	private void addFromExpression(CtClass<?> class_, Set<String> usings, CtExpression<?> expression) {
		if (expression instanceof CtNewArray<?>) {
			for (CtExpression<?> x : ((CtNewArray<?>) expression).getElements()) {
				addFromExpression(class_, usings, x);
			}
		} else if (expression instanceof CtLiteral<?>) {
			// Skip
		} else if (expression instanceof CtFieldRead<?>) {
			addIfDifferent(usings, class_.getPackage(), ((CtTypeAccess<?>) ((CtFieldRead<?>) expression).getTarget()).getAccessedType().getPackage());
		} else {
			throw new UnsupportedOperationException(expression.getClass().getSimpleName());
		}
	}
	
	private static void addIfDifferent(@Nonnull Set<String> usings, @Nonnull CtPackage self, @Nullable CtPackageReference other) {
		if (other == null) {
			return;
		}
		if (!self.getQualifiedName().equals(other.getQualifiedName())) {
			String using = convertPackageToUsing(other.getQualifiedName());
			if (using != null) {
				usings.add(using);
			}
		}
	}
	
	private static final String convertPackageToUsing(String package_) {
		if (package_.equals("java.lang") || package_.equals("java.util")) {
			return null;
		}
		if (package_.equals("java.time")) {
			return "NodaTime";
		}
		if (package_.equals("libldt3.annotations")) {
			return "libldt3.attributes";
		}
		return package_;
	}

}
