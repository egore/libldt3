package libldt3.parser;

import freemarker.template.TemplateException;
import libldt3.parser.generation.Generator;
import libldt3.parser.model.Regel;
import libldt3.parser.parsing.Parser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParsePdf {

    public static void main(String[] args) throws IOException, TemplateException {

        Parser.ParseResult regeln = Parser.parse("./doc/3.2.17/EXT_ITA_VGEX_LDT 3_2_17_Gesamtdokument.pdf");

        var generator = new Generator();
        generator.generateSaetze(regeln.saetze.values());
        generator.generateErlaubteInhalte(regeln.regeln.values());
        generator.generateKontextregeln(regeln.regeln.values());
        generator.generateFormatregeln(regeln.regeln.values());
        generator.generateObjekte(regeln.objekte.values());
        generator.generateKontextParserTest(regeln.regeln.values());
    }


    private static List<Regel> all(Map<String, Regel> regeln, String... keys) {
        return Arrays
                .stream(keys)
                .map(regeln::get)
                .collect(Collectors.toList());
    }

}
