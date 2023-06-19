package libldt3.parser;

import freemarker.template.TemplateException;
import libldt3.parser.generation.Generator;
import libldt3.parser.model.Feld;
import libldt3.parser.model.Objekt;
import libldt3.parser.model.Regel;
import libldt3.parser.parsing.Praser;
import org.apache.commons.lang3.tuple.Triple;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {

        /*Map<String, Feld> felder = new HashMap<>();
        Feld feld8102 = new Feld();
        feld8102.fk = "8102";
        feld8102.inhalt = "Abrechnung_GKV";
        feld8102.laenge = "14";
        feld8102.format = Feld.Format.alnum;
        feld8102.regeln = all(regeln, "E072", "K027", "K070", "K097");
        feld8102.typ = "AbrechnungGKV";
        felder.put(feld8102.fk, feld8102);

        Objekt objekt = new Objekt();
        objekt.name = "Abrechnungsinformationen";
        objekt.beschreibung = "Dieses Objekt wird als Zusammenfassung aller im Auftrag vorhandenen Abrechnungsarten genutzt. An Hand der hier gemachten Angaben kann bei der Implementierung " +
                "eine Prüfroutine hinsichtlich der Vollständigkeit der darunterliegenden Objekte eingeführt werden. Pro Satzart „8215“ darf dieses Objekt nur einmal vorhanden sein.";
        objekt.nummer = "0001";
        Objekt.FeldExtended feld = new Objekt.FeldExtended();
        feld.feld = felder.get("8102");
        feld.vorkommen.position = 1;
        feld.vorkommen.wert = "n";
        feld.bezeichnung = "Abrechnung_GKV";
        feld.feldart = Objekt.Feldart.m;
        feld.regeln = all(regeln, "K027", "K070");
        objekt.felder = Collections.singletonList(feld);


        Template objektTemplate = config.getTemplate("objekt.ftl");
        Files.createDirectories(Path.of("./generated/libldt3/model/objekte"));
        try (Writer writer = Files.newBufferedWriter(Path.of("./generated/libldt3/model/objekte/Abrechnungsinformationen.java"), StandardCharsets.UTF_8)) {
            objektTemplate.process(Map.of("objekt", objekt), writer);
        }*/

        Triple<Map<String, Regel>, Map<String, Feld>, Map<String, Objekt>> regeln = Praser.parse("./EXT_ITA_VGEX_LDT 3_2_15_Gesamtdokument.pdf");

        var generator = new Generator();
        generator.generateErlaubteInhalte(regeln.getLeft().values());
        generator.generateKontextregeln(regeln.getLeft().values());
        generator.generateFormatregeln(regeln.getLeft().values());
        generator.generateObjekte(regeln.getRight().values());
    }


    private static List<Regel> all(Map<String, Regel> regeln, String... keys) {
        return Arrays
                .stream(keys)
                .map(regeln::get)
                .collect(Collectors.toList());
    }

}
