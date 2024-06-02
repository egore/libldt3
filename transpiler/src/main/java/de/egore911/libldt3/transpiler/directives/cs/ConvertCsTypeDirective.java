package de.egore911.libldt3.transpiler.directives.cs;

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

public class ConvertCsTypeDirective implements TemplateDirectiveModel {

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
        // Quirk for generics
        case "?": name = "object"; break;
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
        case "java.util.ArrayList": name = "List"; break;
        case "java.util.regex.Pattern": name = "Regex"; break;
        case "java.lang.reflect.Field": name = "FieldInfo"; break;
        case "java.lang.Iterable": name = "IEnumerable"; break;
        case "java.util.Collection": name = "IEnumerable"; break;
        case "java.lang.UnsupportedOperationException": name = "NotImplementedException"; break;
        case "org.slf4j.Logger": name = "ILogger"; break;
        default: name = ConvertClassDirective.CLASS_REPLACEMENTS.getOrDefault(type.getQualifiedName(), type.getSimpleName()); break;
        }
        if (withNullability && (type.isEnum() || type.isAnnotationType() ||type.getQualifiedName().startsWith("java.time."))) {
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
