package de.egore911.libldt3.transpiler.directives;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;

import freemarker.core.Environment;
import freemarker.core.ParseException;
import freemarker.ext.beans.BeanModel;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNotFoundException;
import libldt3.annotations.Feld;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.reference.CtTypeReference;

public class InvocationFixupDirective implements TemplateDirectiveModel {

    private static final Map<Method, String> METHOD_TEMPLATE;
    static {
        Map<Method, String> map = new HashMap<>();
        try {
            map.put(Field.class.getMethod("get", Object.class), "${target}.GetValue(${arguments})");
            map.put(Field.class.getMethod("getAnnotation", Class.class), "${target}.GetCustomAttribute<${arguments}>()");
            map.put(Field.class.getMethod("setAccessible", boolean.class), "");

            map.put(Pattern.class.getMethod("compile", String.class), "new Regex(${arguments})");

            // Ignore matches call, but fixup ${target}
            map.put(Matcher.class.getMethod("matches"), "${target}");

            map.put(Logger.class.getMethod("error", String.class), "Trace.TraceError(${arguments})");
            map.put(Logger.class.getMethod("error", String.class, Object.class, Object.class), "Trace.TraceError(${arguments})");
            map.put(Logger.class.getMethod("warn", String.class), "Trace.TraceWarning(${arguments})");
            map.put(Logger.class.getMethod("warn", String.class, Object.class, Object.class), "Trace.TraceWarning(${arguments})");

            map.put(Collections.class.getMethod("unmodifiableSet", Set.class), "${arguments}");

            map.put(Map.class.getMethod("size"), "${target}.Count");
            map.put(Map.class.getMethod("get", Object.class), "${target}[${arguments}]");
            map.put(Map.class.getMethod("values"), "${target}.Values");
            map.put(Map.class.getMethod("put", Object.class, Object.class), "${target}[${arguments:0}] = ${arguments:1}");
            map.put(Map.class.getMethod("putAll", Map.class), "foreach (var x in ${arguments}) { ${target}[x.Key] = x.Value; }");

            map.put(Set.class.getMethod("size"), "${target}.Count");

            map.put(List.class.getMethod("add", Object.class), "${target}.Add(${arguments})");
            map.put(List.class.getMethod("isEmpty"), "${target}.Count == 0");

            map.put(String.class.getMethod("isEmpty"), "string.IsNullOrEmpty(${target})");

            map.put(Object.class.getMethod("getClass"), "${target}.GetType()");

            map.put(Class.class.getMethod("getDeclaredFields"), "${target}.GetFields()");
            map.put(Class.class.getMethod("getAnnotation", Class.class), "${target}.GetCustomAttribute<${arguments}>()");

            map.put(Feld.class.getMethod("value"), "${target}.Value");

        } catch (NoSuchMethodException | SecurityException e) {
            throw new Error(e);
        }
        METHOD_TEMPLATE = Collections.unmodifiableMap(map);
    }

    public interface ArgumentHandler {
        String fixArguments(String argument, int index);
    }

    private static final Map<Method, ArgumentHandler> METHOD_TEMPLATE_ARGUMENT_HANDLERS;
    static {
        Map<Method, ArgumentHandler> map = new HashMap<>();
        try {
            map.put(Logger.class.getMethod("error", String.class, Object.class, Object.class), (argument, index) -> {
                if (index == 0) {
                    int counter = 0;
                    Matcher m = Pattern.compile("\\{\\}").matcher(argument);
                    StringBuilder sb = new StringBuilder();
                    while (m.find()) {
                        m.appendReplacement(sb, "{" + counter + "}");
                        counter++;
                    }
                    m.appendTail(sb);
                    return sb.toString();
                }
                return argument;
            });
            map.put(Field.class.getMethod("getAnnotation", Class.class), (argument, index) -> {
                if (index == 0) {
                    String replace = argument.replace("typeof(", "");
                    return replace.substring(0, replace.length() - 1);
                }
                return argument;
            });
            map.put(Class.class.getMethod("getAnnotation", Class.class), (argument, index) -> {
                if (index == 0) {
                    String replace = argument.replace("typeof(", "");
                    return replace.substring(0, replace.length() - 1);
                }
                return argument;
            });
            map.put(Matcher.class.getMethod("matches"), (argument, index) -> {
                if (index == -1) {
                    return argument.replace(".Matcher(", ".IsMatch(");
                }
                return argument;
            });
        } catch (NoSuchMethodException | SecurityException e) {
            throw new Error(e);
        }
        METHOD_TEMPLATE_ARGUMENT_HANDLERS = Collections.unmodifiableMap(map);
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        // Get invocation
        CtInvocation<?> invocation = (CtInvocation<?>) (((BeanModel) params.get("invocation")).getWrappedObject());

        // Build has key
        Method method = null;
        try {
            CtExpression<?> target = invocation.getTarget();
            CtTypeReference<?> type;
            if (target instanceof CtTypeAccess<?>) {
                type = ((CtTypeAccess<?>) target).getAccessedType();
            } else {
                type = target.getType();
            }
            Class<?> class_ = determineClass(type.getQualifiedName());
            if (class_ != null) {
                String methodName = invocation.getExecutable().getSimpleName();
                List<Class<?>> argumentClasses = new ArrayList<>(invocation.getArguments().size());
                for (CtExpression<?> argument : invocation.getArguments()) {
                    argumentClasses.add(determineClass(argument.getType().getQualifiedName()));
                }
                try {
                    method = class_.getMethod(methodName, argumentClasses.toArray(new Class<?>[0]));
                } catch (NoSuchMethodException e) {
                    // Try for assignable matches
                    for (Method declaredMethod : class_.getDeclaredMethods()) {
                        if (declaredMethod.getName().equals(methodName) &&
                                declaredMethod.getParameterCount() == argumentClasses.size()) {
                            boolean possible = true;
                            for (int i = 0; i < declaredMethod.getParameterCount(); i++) {
                                Class<?> left = declaredMethod.getParameters()[i].getType();
                                Class<?> right = argumentClasses.get(i);
                                if (!left.isAssignableFrom(right) && !right.isAssignableFrom(left)) {
                                    possible = false;
                                    break;
                                }
                            }
                            if (possible) {
                                method = declaredMethod;
                                break;
                            }
                        }
                    }
                    // No perfect match, try with same number of arguments
                    if (method == null) {
                        for (Method declaredMethod : class_.getDeclaredMethods()) {
                            if (declaredMethod.getName().equals(methodName) &&
                                    declaredMethod.getParameterCount() == argumentClasses.size()) {
                                System.err.println("No perfect match found for " + methodName + ", using match with correct number of parameters");
                                method = declaredMethod;
                                break;
                            }
                        }
                    }
                    // No argument match, use first
                    if (method == null) {
                        for (Method declaredMethod : class_.getDeclaredMethods()) {
                            if (declaredMethod.getName().equals(methodName)) {
                                System.err.println("No perfect match found for " + methodName + ", using first match");
                                method = declaredMethod;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SecurityException e) {
            throw new TemplateException(e, env);
        }

        // Load template
        String template = METHOD_TEMPLATE.get(method);
        if (template != null) {

            // Replace all arguments in one block
            if (template.contains("${arguments}")) {
                StringBuilder arguments = new StringBuilder();
                for (int i = 0; i < invocation.getArguments().size(); i++) {
                    String argument = render(env, invocation.getArguments().get(i), "argument");
                    ArgumentHandler argumentHandler = METHOD_TEMPLATE_ARGUMENT_HANDLERS.get(method);
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
            for (int i = 0; i < invocation.getArguments().size(); i++) {
                if (!template.contains("${arguments:" + i + "}")) {
                    break;
                }
                String argument = render(env, invocation.getArguments().get(i), "argument");
                ArgumentHandler argumentHandler = METHOD_TEMPLATE_ARGUMENT_HANDLERS.get(method);
                if (argumentHandler != null) {
                    argument = argumentHandler.fixArguments(argument, i);
                }
                template = template.replace("${arguments:" + i + "}", argument);
            }

            // Replace the target
            if (template.contains("${target}")) {
                template = template.replace("${target}", render(env, invocation, "target"));
                ArgumentHandler argumentHandler = METHOD_TEMPLATE_ARGUMENT_HANDLERS.get(method);
                if (argumentHandler != null) {
                    template = argumentHandler.fixArguments(template, -1);
                }
            }

            // And this is it
            env.getOut().write(template);
            return;
        }

        // Default to what was specified in the .ftl otherwise
        body.render(env.getOut());
    }

    static Class<?> determineClass(String qualifiedName) throws ClassNotFoundException {
        if (qualifiedName.equals("void")) {
            return null;
        }
        if ("boolean".equals(qualifiedName)) {
            return boolean.class;
        }
        if ("int".equals(qualifiedName)) {
            return int.class;
        }
        return Class.forName(qualifiedName);
    }

    static String render(Environment env, CtElement invocation, String type) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Template template = env.getConfiguration().getTemplate("invocation/" + type + ".ftl");
        Map<String, Object> data = Collections.singletonMap("expression", invocation);
        Writer writer = new StringWriter();
        template.process(data, writer);
        return writer.toString();
    }

}
