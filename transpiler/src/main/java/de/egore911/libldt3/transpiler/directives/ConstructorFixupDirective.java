package de.egore911.libldt3.transpiler.directives;

import static de.egore911.libldt3.transpiler.directives.InvocationFixupDirective.determineClass;
import static de.egore911.libldt3.transpiler.directives.InvocationFixupDirective.render;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import de.egore911.libldt3.transpiler.directives.InvocationFixupDirective.ArgumentHandler;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtExpression;
import spoon.reflect.reference.CtTypeReference;

public class ConstructorFixupDirective implements TemplateDirectiveModel {

    private static final Map<Constructor<?>, String> CONSTRUCTOR_TEMPLATE;
    static {
        Map<Constructor<?>, String> map = new HashMap<>();
        try {
            map.put(HashSet.class.getConstructor(Collection.class), "new ${type} { ${arguments} }");
        } catch (NoSuchMethodException | SecurityException e) {
            throw new Error(e);
        }
        CONSTRUCTOR_TEMPLATE = Collections.unmodifiableMap(map);
    }

    private static final Map<Constructor<?>, ArgumentHandler> CONSTRUCTOR_TEMPLATE_ARGUMENT_HANDLERS;
    static {
        Map<Constructor<?>, ArgumentHandler> map = new HashMap<>();
        try {
            map.put(HashSet.class.getConstructor(Collection.class), (argument, index) -> {
                if (index == 0) {
                    String replace = argument.replace("Arrays.AsList(", "");
                    return replace.substring(0, replace.length() - 1);
                }
                return argument;
            });
        } catch (NoSuchMethodException | SecurityException e) {
            throw new Error(e);
        }
        CONSTRUCTOR_TEMPLATE_ARGUMENT_HANDLERS = Collections.unmodifiableMap(map);
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
     // Get invocation
        CtConstructorCall<?> constructorCall = (CtConstructorCall<?>) (((BeanModel) params.get("constructor")).getWrappedObject());

        Constructor<?> constructor = null;
        try {
            CtTypeReference<?> type = constructorCall.getExecutable().getType();
            Class<?> class_ = determineClass(type.getQualifiedName());
            if (class_ != null) {
                List<Class<?>> argumentClasses = new ArrayList<>(constructorCall.getArguments().size());
                for (CtExpression<?> argument : constructorCall.getArguments()) {
                    argumentClasses.add(determineClass(argument.getType().getQualifiedName()));
                }
                try {
                    constructor = class_.getConstructor(argumentClasses.toArray(new Class<?>[0]));
                } catch (NoSuchMethodException e) {
                    // Try for assignable matches
                    for (Constructor<?> declaredConstructor : class_.getConstructors()) {
                        if (declaredConstructor.getParameterCount() == argumentClasses.size()) {
                            boolean possible = true;
                            for (int i = 0; i < declaredConstructor.getParameterCount(); i++) {
                                Class<?> left = declaredConstructor.getParameters()[i].getType();
                                Class<?> right = argumentClasses.get(i);
                                if (!left.isAssignableFrom(right) && !right.isAssignableFrom(left)) {
                                    possible = false;
                                    break;
                                }
                            }
                            if (possible) {
                                constructor = declaredConstructor;
                                break;
                            }
                        }
                    }
                    // No perfect match, try with same number of arguments
                    if (constructor == null) {
                        for (Constructor<?> declaredConstructor : class_.getConstructors()) {
                            if (declaredConstructor.getParameterCount() == argumentClasses.size()) {
                                System.err.println("No perfect match found for " + type + ", using match with correct number of parameters");
                                constructor = declaredConstructor;
                                break;
                            }
                        }
                    }
                    // No argument match, use first
                    if (constructor == null) {
                        for (Constructor<?> declaredConstructor : class_.getConstructors()) {
                            System.err.println("No perfect match found for " + type + ", using first match");
                            constructor = declaredConstructor;
                            break;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SecurityException e) {
            throw new TemplateException(e, env);
        }

        // Load template
        String template = CONSTRUCTOR_TEMPLATE.get(constructor);
        if (template != null) {

            // Replace all arguments in one block
            if (template.contains("${arguments}")) {
                StringBuilder arguments = new StringBuilder();
                for (int i = 0; i < constructorCall.getArguments().size(); i++) {
                    String argument = render(env, constructorCall.getArguments().get(i), "argument");
                    ArgumentHandler argumentHandler = CONSTRUCTOR_TEMPLATE_ARGUMENT_HANDLERS.get(constructor);
                    if (argumentHandler != null) {
                        argument = argumentHandler.fixArguments(argument, i);
                    }
                    if (!arguments.isEmpty()) {
                        arguments.append(", ");
                    }
                    arguments.append(argument);
                }
                template = template.replace("${arguments}", arguments.toString());
            }

            // Replace single arguments
            for (int i = 0; i < constructorCall.getArguments().size(); i++) {
                if (!template.contains("${arguments:" + i + "}")) {
                    break;
                }
                String argument = render(env, constructorCall.getArguments().get(i), "argument");
                template = template.replace("${arguments:" + i + "}", argument);
            }

            // Replace the target
            if (template.contains("${type}")) {
                template = template.replace("${type}", render(env, constructorCall.getExecutable().getType(), "type"));
            }

            // And this is it
            env.getOut().write(template);
            return;
        }

        // Default to what was specified in the .ftl otherwise
        body.render(env.getOut());
    }
}
