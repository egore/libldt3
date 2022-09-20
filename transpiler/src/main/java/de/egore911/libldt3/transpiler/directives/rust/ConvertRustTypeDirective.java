package de.egore911.libldt3.transpiler.directives.rust;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.stream.Collectors;

import de.egore911.libldt3.transpiler.directives.ConvertClassDirective;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import spoon.reflect.reference.CtTypeReference;

public class ConvertRustTypeDirective implements TemplateDirectiveModel {

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        CtTypeReference<?> type = (CtTypeReference<?>) (((BeanModel) params.get("type")).getWrappedObject());

        Writer out = env.getOut();
        String qualifiedName = convertType(type);
        out.append(qualifiedName);
    }

    private static String convertType(CtTypeReference<?> type) {
        String name;
        switch (type.getQualifiedName()) {
            case "int": name = "i32"; break;
            case "java.lang.Boolean": name = "Option<bool>"; break;
            case "java.lang.Float": name = "Option<f32>"; break;
            case "java.lang.Integer": name = "Option<i32>"; break;
            case "java.util.List": name = "Vec"; break;
            case "java.util.Set": name = "HashSet"; break;
            case "java.util.regex.Pattern": name = "Regex"; break;
            default: {
                if (type.getTopLevelType().getSimpleName().equals(type.getSimpleName())) {
                    name = type.getSimpleName();
                } else {
                    name = type.getTopLevelType().getSimpleName() + "_" + type.getSimpleName();
                }
                break;
            }
        }
        if (type.getActualTypeArguments() != null && !type.getActualTypeArguments().isEmpty()) {
            name += "<" + type.getActualTypeArguments()
                .stream()
                .map(x -> convertType(x))
                .collect(Collectors.joining(", "))+ ">";
        }
        return name;
    }
}
