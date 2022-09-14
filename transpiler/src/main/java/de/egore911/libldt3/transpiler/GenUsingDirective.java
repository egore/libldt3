package de.egore911.libldt3.transpiler;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.reference.CtPackageReference;
import spoon.reflect.reference.CtTypeReference;

public class GenUsingDirective implements TemplateDirectiveModel {

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		TemplateModel p = (TemplateModel) params.get("class");
		
		CtClass<?> class_ = (CtClass<?>) (((BeanModel) p).getWrappedObject());
		
		// Collect all things for "using ..." statements
		Set<String> usings = new TreeSet<String>();
		
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
				addIfDifferent(usings, class_.getPackage(), field.getType().getPackage());
				if (field.getAnnotations() != null) {
					for (CtAnnotation<?> annotation : field.getAnnotations()) {
						addIfDifferent(usings, class_.getPackage(), annotation.getType().getPackage());
					}
				}
			}
		}
		
		// 3.) Method arguments and return values
		if (class_.getMethods() != null) {
			for (CtMethod<?> method : class_.getMethods()) {
				if (method.getThrownTypes() != null) {
					for (CtTypeReference<?> thrownType : method.getThrownTypes()) {
						addIfDifferent(usings, class_.getPackage(), thrownType.getPackage());
					}
				}
				if (method.getParameters() != null) {
					for (CtParameter<?> parameter : method.getParameters()) {
						for (CtTypeReference<?> ref : parameter.getReferencedTypes()) {
							addIfDifferent(usings, class_.getPackage(), ref.getPackage());
						}
					}
				}
				if (method.getAnnotations() != null) {
					for (CtAnnotation<?> annotation : method.getAnnotations()) {
						addIfDifferent(usings, class_.getPackage(), annotation.getType().getPackage());
					}
				}
				addIfDifferent(usings, class_.getPackage(), method.getType().getPackage());
			}
		}
		
		// 4.) Annotations
		if (class_.getAnnotations() != null) {
			for (CtAnnotation<?> annotation : class_.getAnnotations()) {
				addIfDifferent(usings, class_.getPackage(), annotation.getType().getPackage());
			}
		}
		
		Writer out = env.getOut();
		for (String using : usings) {
			out.append("using ").append(using).append(";\n");
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
		if (package_.equals("java.lang")) {
			return null;
		}
		if (package_.equals("libldt3.annotations")) {
			return "libldt3.attributes";
		}
		return package_;
	}

}
