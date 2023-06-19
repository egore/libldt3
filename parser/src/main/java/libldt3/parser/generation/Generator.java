package libldt3.parser.generation;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import libldt3.parser.Main;
import libldt3.parser.RegelNaming;
import libldt3.parser.model.ErlaubterInhalt;
import libldt3.parser.model.Formatregel;
import libldt3.parser.model.Kontextregel;
import libldt3.parser.model.Objekt;
import libldt3.parser.model.Regel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class Generator {

    private static final Logger LOG = LoggerFactory.getLogger(Generator.class);

    private final Configuration config;

    public Generator() throws TemplateModelException {
        DefaultObjectWrapper wrapper = new DefaultObjectWrapper(Configuration.VERSION_2_3_32);
        wrapper.setExposeFields(true);

         config = new Configuration(Configuration.VERSION_2_3_32);
        config.setTemplateLoader(new ClassTemplateLoader(Main.class, "/ldt/"));
        config.setObjectWrapper(wrapper);
        config.setSharedVariable("year", Integer.toString(LocalDate.now().getYear()));
        config.setSharedVariable("linewrap", new LineWrapDirective());
        config.setSharedVariable("regelnaming", RegelNaming.REPLACEMENTS);
    }

    public void generateErlaubteInhalte(Collection<Regel> regeln) throws IOException, TemplateException {
        Template enumTemplate = config.getTemplate("enum.ftl");
        Files.createDirectories(Path.of("./generated/libldt3/model/enums"));
        for (Regel regel : regeln) {
            if (regel instanceof ErlaubterInhalt) {
                String name = RegelNaming.REPLACEMENTS.get(regel.regelnummer);
                if (name == null || "Boolean".equals(name)) {
                    continue;
                }
                try (Writer writer = Files.newBufferedWriter(Path.of("./generated/libldt3/model/enums/" + name + ".java"), StandardCharsets.UTF_8)) {
                    enumTemplate.process(Map.of("enum", regel), writer);
                }
            }
        }
    }

    public void generateKontextregeln(Collection<Regel> regeln) throws IOException, TemplateException {
        Template kontextTemplate = config.getTemplate("kontext.ftl");
        Files.createDirectories(Path.of("./generated/libldt3/model/regel/kontext"));
        for (Regel regel : regeln) {
            if (regel instanceof Kontextregel) {
                try (Writer writer = Files.newBufferedWriter(Path.of("./generated/libldt3/model/regel/kontext/" + regel.regelnummer + ".java"), StandardCharsets.UTF_8)) {
                    kontextTemplate.process(Map.of("kontext", regel), writer);
                }
            }
        }
    }

    public void generateFormatregeln(Collection<Regel> regeln) throws IOException, TemplateException {
        Template formatTemplate = config.getTemplate("format.ftl");
        Files.createDirectories(Path.of("./generated/libldt3/model/regel"));
        for (Regel regel : regeln) {
            if (regel instanceof Formatregel) {
                try (Writer writer = Files.newBufferedWriter(Path.of("./generated/libldt3/model/regel/" + regel.regelnummer + ".java"), StandardCharsets.UTF_8)) {
                    formatTemplate.process(Map.of("format", regel), writer);
                }
            }
        }
    }


    public void generateObjekte(Collection<Objekt> objekte) throws IOException, TemplateException {
        Template objektTemplate = config.getTemplate("objekt.ftl");
        Files.createDirectories(Path.of("./generated/libldt3/model/objekte"));
        for (Objekt objekt : objekte) {
            if (objekt.stub) {
                LOG.warn("Skipping stub {}", objekt.name);
                continue;
            }
            try (Writer writer = Files.newBufferedWriter(Path.of("./generated/libldt3/model/objekte/" + objekt.name + ".java"), StandardCharsets.UTF_8)) {
                objektTemplate.process(Map.of("objekt", objekt), writer);
            }
        }
    }
}
