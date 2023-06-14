package de.egore911.libldt3.transpiler.directives;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import spoon.reflect.declaration.CtClass;

public class ConvertClassDirective implements TemplateDirectiveModel {

    public static Map<String, String> CLASS_REPLACEMENTS = Map.of(
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
            throws TemplateException, IOException {

        CtClass<?> class_ = (CtClass<?>) (((BeanModel) params.get("class")).getWrappedObject());

        Writer out = env.getOut();
        String qualifiedName = convertClass(class_);
        out.append(qualifiedName);
    }

    private static String convertClass(CtClass<?> class_) {
        String name = CLASS_REPLACEMENTS.getOrDefault(class_.getQualifiedName(), class_.getSimpleName());
        if (class_.isGenerics()) {
            throw new UnsupportedOperationException();
        }
        return name;
    }

}
