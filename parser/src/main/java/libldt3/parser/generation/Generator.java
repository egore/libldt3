package libldt3.parser.generation;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import libldt3.parser.ParsePdf;
import libldt3.parser.RegelNaming;
import libldt3.parser.model.ErlaubterInhalt;
import libldt3.parser.model.Formatregel;
import libldt3.parser.model.Kontextregel;
import libldt3.parser.model.Objekt;
import libldt3.parser.model.Regel;
import libldt3.parser.model.Satz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Generator {

    private static final Logger LOG = LoggerFactory.getLogger(Generator.class);

    private final Configuration config;

    public Generator() throws TemplateModelException {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        DefaultObjectWrapper wrapper = new DefaultObjectWrapper(Configuration.VERSION_2_3_33);
        wrapper.setExposeFields(true);

        config = new Configuration(Configuration.VERSION_2_3_33);
        config.setTemplateLoader(new ClassTemplateLoader(ParsePdf.class, "/ldt/"));
        config.setObjectWrapper(wrapper);
        config.setSharedVariable("year", Integer.toString(LocalDate.now().getYear()));
        config.setSharedVariable("linewrap", new LineWrapDirective());
        config.setSharedVariable("regelnaming", RegelNaming.REPLACEMENTS);
    }

    public void generateErlaubteInhalte(Collection<Regel> regeln) throws IOException, TemplateException {
        Template enumTemplate = config.getTemplate("enum.ftl");
        Path dir = Path.of("./generated/libldt3/model/enums");
        initDir(dir);
        for (Regel regel : regeln) {
            if (regel instanceof ErlaubterInhalt) {
                String name = RegelNaming.REPLACEMENTS.get(regel.regelnummer);
                if (name == null || "Boolean".equals(name)) {
                    continue;
                }
                try (Writer writer = Files.newBufferedWriter(dir.resolve(name + ".java"), StandardCharsets.UTF_8)) {
                    enumTemplate.process(Map.of("enum", regel), writer);
                }
            }
        }
    }

    private static void initDir(Path dir) throws IOException {
        if (Files.exists(dir)) {
            Files.walk(dir)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        Files.createDirectories(dir);
    }

    public void generateKontextregeln(Collection<Regel> regeln) throws IOException, TemplateException {
        Template kontextTemplate = config.getTemplate("kontext.ftl");
        Path dir = Path.of("./generated/libldt3/model/regel/kontext");
        initDir(dir);
        for (Regel regel : regeln) {
            if (regel instanceof Kontextregel) {
                LOG.info("Generating kontext {}", regel.regelnummer);
                try (Writer writer = Files.newBufferedWriter(dir.resolve(regel.regelnummer + ".java"), StandardCharsets.UTF_8)) {
                    kontextTemplate.process(Map.of("kontext", regel), writer);
                }
            }
        }
    }

    public void generateFormatregeln(Collection<Regel> regeln) throws IOException, TemplateException {
        Template formatTemplate = config.getTemplate("format.ftl");
        Path dir = Path.of("./generated/libldt3/model/regel/format");
        initDir(dir);
        for (Regel regel : regeln) {
            if (regel instanceof Formatregel) {
                try (Writer writer = Files.newBufferedWriter(dir.resolve(regel.regelnummer + ".java"), StandardCharsets.UTF_8)) {
                    formatTemplate.process(Map.of("format", regel), writer);
                }
            }
        }
    }


    public void generateObjekte(Collection<Objekt> objekte) throws IOException, TemplateException {
        Template objektTemplate = config.getTemplate("objekt.ftl");
        Path dir = Path.of("./generated/libldt3/model/objekte");
        initDir(dir);
        for (Objekt objekt : objekte) {
            if (objekt.stub) {
                LOG.warn("Skipping stub {}", objekt.name);
                continue;
            }
            try (Writer writer = Files.newBufferedWriter(dir.resolve(objekt.name + ".java"), StandardCharsets.UTF_8)) {
                objektTemplate.process(Map.of("objekt", objekt), writer);
            }
        }
    }

    public void generateKontextParserTest(Collection<Regel> regeln) throws IOException, TemplateException {
        Template kontextTemplate = config.getTemplate("unit.ftl");
        Path dir = Path.of("./generated/test/libldt3/ruleparser");
        initDir(dir);
        List<Kontextregel> kontextregeln = new ArrayList<>();
        for (Regel regel : regeln) {
            if (regel instanceof Kontextregel) {
                kontextregeln.add((Kontextregel) regel);
            }
        }
        Collections.sort(kontextregeln);
        try (Writer writer = Files.newBufferedWriter(dir.resolve("KontextParserTest.java"), StandardCharsets.UTF_8)) {
            kontextTemplate.process(Map.of("regeln", kontextregeln), writer);
        }
    }

    public void generateSaetze(Collection<Satz> saetze) throws IOException, TemplateException {
        Template objektTemplate = config.getTemplate("satz.ftl");
        Path dir = Path.of("./generated/libldt3/model/saetze");
        initDir(dir);
        for (var satz : saetze) {
            try (Writer writer = Files.newBufferedWriter(dir.resolve(satz.name + ".java"), StandardCharsets.UTF_8)) {
                objektTemplate.process(Map.of("satz", satz), writer);
            }
        }
    }
}
