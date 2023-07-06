package libldt3.parser;

import freemarker.template.TemplateException;
import libldt3.parser.generation.Generator;
import libldt3.parser.model.Feld;
import libldt3.parser.model.Objekt;
import libldt3.parser.model.Regel;
import libldt3.parser.parsing.Parser;
import org.apache.commons.lang3.tuple.Triple;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {

        Triple<Map<String, Regel>, Map<String, Feld>, Map<String, Objekt>> regeln = Parser.parse("./EXT_ITA_VGEX_LDT 3_2_15_Gesamtdokument.pdf");

        var generator = new Generator();
        generator.generateErlaubteInhalte(regeln.getLeft().values());
        generator.generateKontextregeln(regeln.getLeft().values());
        generator.generateFormatregeln(regeln.getLeft().values());
        generator.generateObjekte(regeln.getRight().values());
        generator.generateKontextParserTest(regeln.getLeft().values());
    }


    private static List<Regel> all(Map<String, Regel> regeln, String... keys) {
        return Arrays
                .stream(keys)
                .map(regeln::get)
                .collect(Collectors.toList());
    }

}
