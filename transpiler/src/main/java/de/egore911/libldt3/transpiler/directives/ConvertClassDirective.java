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
        map.put("libldt3.model.objekte.AbrechnungIgeLeistungen$KostenuebernahmeerklaerungAuftraggeberLiegtVor", "KostenuebernahmeerklaerungAuftraggeberLiegtVor_");
        map.put("libldt3.model.objekte.AbrechnungSonstigeKostenuebernahme$KostenuebernahmeerklaerungAuftraggeberLiegtVor", "KostenuebernahmeerklaerungAuftraggeberLiegtVor_");
        map.put("libldt3.model.objekte.Anschrift$Plz", "Plz_");
        map.put("libldt3.model.objekte.Anschrift$PostfachPlz", "PostfachPlz_");
        map.put("libldt3.model.objekte.Antibiogramm$WirkstoffIdent", "WirkstoffIdent_");
        map.put("libldt3.model.objekte.Arztidentifikation$ArztIdEinesArztes", "ArztIdEinesArztes_");
        map.put("libldt3.model.objekte.Auftragsinformation$AuftragsnummerEinsender", "AuftragsnummerEinsender_");
        map.put("libldt3.model.objekte.Auftragsinformation$FallakteOderStudieId", "FallakteOderStudieId_");
        map.put("libldt3.model.objekte.Befundinformationen$AuftragsnummerEinsender", "AuftragsnummerEinsender_");
        map.put("libldt3.model.objekte.Befundinformationen$AuftragsnummerLaborId", "AuftragsnummerLaborId_");
        map.put("libldt3.model.objekte.Befundinformationen$DerFallakteOderStudieId", "DerFallakteOderStudieId_");
        map.put("libldt3.model.objekte.Befundinformationen$KatalogDurchgefuehrteLeistungenId", "KatalogDurchgefuehrteLeistungenId_");
        map.put("libldt3.model.objekte.Befundinformationen$OrderNumber", "OrderNumber_");
        map.put("libldt3.model.objekte.Befundinformationen$RecallEmpfohlen", "RecallEmpfohlen_");
        map.put("libldt3.model.objekte.Befundinformationen$RechtsgrundlageTestung", "RechtsgrundlageTestung_");
        map.put("libldt3.model.objekte.Betriebsstaette$BsnrBezeichnung", "BsnrBezeichnung_");
        map.put("libldt3.model.objekte.Blutgruppenzugehoerigkeit$ErgebnisKreuzprobe", "ErgebnisKreuzprobe_");
        map.put("libldt3.model.objekte.Diagnose$IcdCode", "IcdCode_");
        map.put("libldt3.model.objekte.Einsenderidentifikation$KundenNummer", "KundenNummer_");
        map.put("libldt3.model.objekte.FehlermeldungAufmerksamkeit$GrundBenachrichtigung", "GrundBenachrichtigung_");
        map.put("libldt3.model.objekte.Koerperkenngroessen$GewichtDesPatienten", "GewichtDesPatienten_");
        map.put("libldt3.model.objekte.Koerperkenngroessen$GroesseDesPatienten", "GroesseDesPatienten_");
        map.put("libldt3.model.objekte.Kommunikationsdaten$AlternativeElektronischePostadresse", "AlternativeElektronischePostadresse_");
        map.put("libldt3.model.objekte.Kommunikationsdaten$ElektronischePostadresse", "ElektronischePostadresse_");
        map.put("libldt3.model.objekte.KrebsfrueherkennungZervixKarzinom$Gyn_OpStrahlenOderChemotherapieGenitale", "Gyn_OpStrahlenOderChemotherapieGenitale_");
        map.put("libldt3.model.objekte.KrebsfrueherkennungZervixKarzinom$HpvHrTest", "HpvHrTest_");
        map.put("libldt3.model.objekte.Material$ArtMaterial", "ArtMaterial_");
        map.put("libldt3.model.objekte.Material$Medikamenteneinnahme", "Medikamenteneinnahme_");
        map.put("libldt3.model.objekte.Material$MedikamenteneinnahmeZumZeitpunktMaterialentnahme", "MedikamenteneinnahmeZumZeitpunktMaterialentnahme_");
        map.put("libldt3.model.objekte.Material$MengeProbenmaterial", "MengeProbenmaterial_");
        map.put("libldt3.model.objekte.Medikament$Rezeptur", "Rezeptur_");
        map.put("libldt3.model.objekte.Medikament$WirkstoffmengeMengeBezugsmengeWirkstaerke", "WirkstoffmengeMengeBezugsmengeWirkstaerke_");
        map.put("libldt3.model.objekte.Mutterschaft$AnzahlSchwangerschaften", "AnzahlSchwangerschaften_");
        map.put("libldt3.model.objekte.Normalwert$AlarmwertObereGrenze", "AlarmwertObereGrenze_");
        map.put("libldt3.model.objekte.Normalwert$AlarmwertUntereGrenze", "AlarmwertUntereGrenze_");
        map.put("libldt3.model.objekte.Normalwert$GrenzwertindikatorLaborwerte", "GrenzwertindikatorLaborwerte_");
        map.put("libldt3.model.objekte.Normalwert$NormalwertListenbezeichnung", "NormalwertListenbezeichnung_");
        map.put("libldt3.model.objekte.Normalwert$NormalwertObereGrenze", "NormalwertObereGrenze_");
        map.put("libldt3.model.objekte.Normalwert$NormalwertUntereGrenze", "NormalwertUntereGrenze_");
        map.put("libldt3.model.objekte.Normalwert$Normalwertspezifikation", "Normalwertspezifikation_");
        map.put("libldt3.model.objekte.Organisation$Funktionsbezeichnung", "Funktionsbezeichnung_");
        map.put("libldt3.model.objekte.Organisation$OrganisationFirma", "OrganisationFirma_");
        map.put("libldt3.model.objekte.Rechnungsempfaenger$NameEinrichtungAuftraggeber", "NameEinrichtungAuftraggeber_");
        map.put("libldt3.model.objekte.Schwangerschaft$LetztePeriode", "LetztePeriode_");
        map.put("libldt3.model.objekte.SendendesSystem$SoftwareNameSoftware", "SoftwareNameSoftware_");
        map.put("libldt3.model.objekte.SonstigeUntersuchungsergebnisse$ErgebnisId", "ErgebnisId_");
        map.put("libldt3.model.objekte.SonstigeUntersuchungsergebnisse$Ergebnisstatus", "Ergebnisstatus_");
        map.put("libldt3.model.objekte.SonstigeUntersuchungsergebnisse$KatalogAnforderbareLeistungenId", "KatalogAnforderbareLeistungenId_");
        map.put("libldt3.model.objekte.SonstigeUntersuchungsergebnisse$TestIdent", "TestIdent_");
        map.put("libldt3.model.objekte.TierSonstiges$Alter", "Alter_");
        map.put("libldt3.model.objekte.Timestamp$Uhrzeit", "Uhrzeit_");
        map.put("libldt3.model.objekte.Untersuchungsabrechnung$Gebuehrennummer", "Gebuehrennummer_");
        map.put("libldt3.model.objekte.Untersuchungsabrechnung$KatalogAbrechenbareLeistungenId", "KatalogAbrechenbareLeistungenId_");
        map.put("libldt3.model.objekte.Untersuchungsanforderung$EinwilligungserklaerungDesPatientenLiegtVor", "EinwilligungserklaerungDesPatientenLiegtVor_");
        map.put("libldt3.model.objekte.Untersuchungsanforderung$KatalogAnforderbareLeistungenId", "KatalogAnforderbareLeistungenId_");
        map.put("libldt3.model.objekte.Untersuchungsanforderung$ProbengefaessIdent", "ProbengefaessIdent_");
        map.put("libldt3.model.objekte.Untersuchungsanforderung$TestIdent", "TestIdent_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisKlinischeChemie$Ergebnisstatus", "Ergebnisstatus_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisKlinischeChemie$KatalogAnforderbareLeistungenId", "KatalogAnforderbareLeistungenId_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisKlinischeChemie$TestIdent", "TestIdent_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisKrebsfrueherkennungZervixKarzinom$Gruppe", "Gruppe_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisKrebsfrueherkennungZervixKarzinom$HpvHrTestergebnisObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom", "HpvHrTestergebnisObjUntersuchungsergebnisKrebsfrueherkennungZervixKarzinom_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisKrebsfrueherkennungZervixKarzinom$TestIdent", "TestIdent_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisKrebsfrueherkennungZervixKarzinom$ZytologischeKontrolle", "ZytologischeKontrolle_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisMikrobiologie$KatalogAnforderbareLeistungenId", "KatalogAnforderbareLeistungenId_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisMikrobiologie$KeimPilzIdentifizierung", "KeimPilzIdentifizierung_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisMikrobiologie$TestIdent", "TestIdent_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologie$ErgebnisId", "ErgebnisId_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologie$Ergebnisstatus", "Ergebnisstatus_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologie$Gruppe", "Gruppe_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologie$HpvBefundObjUntersuchungsergebnisZytologie", "HpvBefundObjUntersuchungsergebnisZytologie_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologie$KatalogAnforderbareLeistungenId", "KatalogAnforderbareLeistungenId_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologie$RecallEmpfohlen", "RecallEmpfohlen_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologie$TestIdent", "TestIdent_");
        map.put("libldt3.model.objekte.UntersuchungsergebnisZytologieKrebsvorsorge$TestIdent", "TestIdent_");
        map.put("libldt3.model.objekte.Veranlassungsgrund$AbrechnungsinfoZurUntersuchung", "AbrechnungsinfoZurUntersuchung_");
        map.put("libldt3.model.objekte.Wirkstoff$ArzneimittelwirkstoffWirkstoffWirkstoffbezeichnung", "ArzneimittelwirkstoffWirkstoffWirkstoffbezeichnung_");

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
