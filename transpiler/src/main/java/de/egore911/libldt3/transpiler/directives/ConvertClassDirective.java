package de.egore911.libldt3.transpiler.directives;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModel;
import spoon.reflect.declaration.CtClass;

public class ConvertClassDirective implements TemplateDirectiveModel {

    public static final Map<String, String> CLASS_REPLACEMENTS;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologie$RecallEmpfohlen", "RecallEmpfohlen_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologieKrebsvorsorge$TestIdent", "TestIdent_");
        map.put("libldt3.model.objekte.Antibiogramm$WirkstoffIdent", "WirkstoffIdent_");
        map.put("libldt3.model.objekte.Befundinformationen$OrderNumber", "OrderNumber_");
        map.put("libldt3.model.objekte.Material$Medikamenteneinnahme", "Medikamenteneinnahme_");
        map.put("libldt3.model.objekte.Kommunikationsdaten$ElektronischePostadresse", "ElektronischePostadresse_");
        map.put("libldt3.model.objekte.Untersuchungsabrechnung$Gebuehrennummer", "Gebuehrennummer_");
        map.put("libldt3.model.objekte.Organisation$Funktionsbezeichnung", "Funktionsbezeichnung_");
        map.put("libldt3.model.objekte.Untersuchungsanforderung$ProbengefaessIdent", "ProbengefaessIdent_");
        map.put("libldt3.model.objekte.Wirkstoff$ArzneimittelwirkstoffWirkstoffWirkstoffbezeichnung", "ArzneimittelwirkstoffWirkstoffWirkstoffbezeichnung_");
        map.put("libldt3.model.objekte.FehlermeldungAufmerksamkeit$GrundBenachrichtigung", "GrundBenachrichtigung_");
        map.put("libldt3.model.objekte.AbrechnungIgeLeistungen$KostenuebernahmeerklaerungAuftraggeberLiegtVor", "KostenuebernahmeerklaerungAuftraggeberLiegtVor_");
        map.put("libldt3.model.objekte.AbrechnungSonstigeKostenuebernahme$KostenuebernahmeerklaerungAuftraggeberLiegtVor", "KostenuebernahmeerklaerungAuftraggeberLiegtVor_");
        map.put("libldt3.model.objekte.Anschrift$Plz", "Plz_");
        map.put("libldt3.model.objekte.Anschrift$PostfachPlz", "PostfachPlz_");
        map.put("libldt3.model.objekte.Arztidentifikation$ArztIdEinesArztes", "ArztIdEinesArztes_");
        map.put("libldt3.model.objekte.Auftragsinformation$AuftragsnummerEinsender", "AuftragsnummerEinsender_");
        map.put("libldt3.model.objekte.Auftragsinformation$FallakteOderStudieId", "FallakteOderStudieId_");
        map.put("libldt3.model.objekte.Befundinformationen$AuftragsnummerEinsender", "AuftragsnummerEinsender_");
        map.put("libldt3.model.objekte.Befundinformationen$RechtsgrundlageTestung", "RechtsgrundlageTestung_");
        CLASS_REPLACEMENTS = Collections.unmodifiableMap(map);
    }

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
