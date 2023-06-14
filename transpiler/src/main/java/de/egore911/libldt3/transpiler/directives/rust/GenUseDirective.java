package de.egore911.libldt3.transpiler.directives.rust;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModel;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;

public class GenUseDirective implements TemplateDirectiveModel {

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws IOException {
        CtType<?> struct = (CtType<?>) (((StringModel) params.get("struct")).getWrappedObject());
        String structQualifiedName = struct.getQualifiedName();

        Set<String> uses = new TreeSet<>();

        addReferencedTypes(struct.getUsedTypes(true), structQualifiedName, uses);

        Writer out = env.getOut();
        for (String use : uses) {
            out.append("use ").append(use).append(";\n");
        }
    }

    private void addReferencedTypes(Collection<CtTypeReference<?>> types, String structQualifiedName, Set<String> uses) {
        for (CtTypeReference<?> type : types) {
            if (type.getPackage() == null) {
                continue;
            }
            String qualifiedName = type.getQualifiedName();
            if (qualifiedName.equals(structQualifiedName)) {
                continue;
            }
            switch (qualifiedName) {
                case "java.lang.Boolean": uses.add("std::option::Option"); break;
                case "java.lang.Float": uses.add("std::option::Option"); break;
                case "java.lang.Integer": uses.add("std::option::Option"); break;
                case "java.time.LocalDate": uses.add("datetime::LocalDate"); break;
                case "java.time.LocalTime": uses.add("datetime::LocalTime"); break;
                case "java.util.Set": uses.add("std::collections::HashSet"); break;
                case "java.util.regex.Pattern": uses.add("regex::Regex"); break;
            }
            if (qualifiedName.startsWith("libldt3.model.")) {
                uses.add("crate::model::" + qualifiedName.substring("libldt3.model.".length()).replace(".", "::") + "::" + type.getSimpleName());
            }
            if (type.getReferencedTypes() != null) {
                Set<CtTypeReference<?>> referencedTypes = new HashSet<>(type.getReferencedTypes());
                referencedTypes.remove(type);
                addReferencedTypes(referencedTypes, structQualifiedName, uses);
            }
        }
    }
}
