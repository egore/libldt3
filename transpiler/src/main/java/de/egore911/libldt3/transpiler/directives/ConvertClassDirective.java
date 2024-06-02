package de.egore911.libldt3.transpiler.directives;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModel;
import spoon.reflect.declaration.CtClass;

public class ConvertClassDirective implements TemplateDirectiveModel {

    public static final Map<String, String> CLASS_REPLACEMENTS = Map.of(
            "libldt3.model.objekte.UntersuchungsergebnisZytologie$RecallEmpfohlen", "RecallEmpfohlen_",
            "libldt3.model.objekte.UntersuchungsergebnisZytologieKrebsvorsorge$TestIdent", "TestIdent_",
            "libldt3.model.objekte.Antibiogramm$WirkstoffIdent", "WirkstoffIdent_",
            "libldt3.model.objekte.Befundinformationen$OrderNumber", "OrderNumber_",
            "libldt3.model.objekte.Material$Medikamenteneinnahme", "Medikamenteneinnahme_",
            "libldt3.model.objekte.Kommunikationsdaten$ElektronischePostadresse", "ElektronischePostadresse_",
            "libldt3.model.objekte.Untersuchungsabrechnung$Gebuehrennummer", "Gebuehrennummer_",
            "libldt3.model.objekte.Organisation$Funktionsbezeichnung", "Funktionsbezeichnung_",
            "libldt3.model.objekte.Untersuchungsanforderung$ProbengefaessIdent", "ProbengefaessIdent_");

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws IOException {

        CtClass<?> ctClass = (CtClass<?>) (((BeanModel) params.get("class")).getWrappedObject());

        Writer out = env.getOut();
        String qualifiedName = convertClass(ctClass);
        out.append(qualifiedName);
    }

    private static String convertClass(CtClass<?> ctClass) {
        String name = CLASS_REPLACEMENTS.getOrDefault(ctClass.getQualifiedName(), ctClass.getSimpleName());
        if (ctClass.isGenerics()) {
            throw new UnsupportedOperationException();
        }
        return name;
    }

}
