package libldt3.parser.generation;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import libldt3.parser.Main;
import libldt3.parser.RegelNaming;
import libldt3.parser.model.Regel;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class Generator {

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

    public void generateRegeln(Collection<Regel> regeln) throws IOException, TemplateException {
        Template enumTemplate = config.getTemplate("enum.ftl");
        Files.createDirectories(Path.of("./generated/libldt3/model/enums"));
        for (Regel regel : regeln) {
            // TODO hack
            if (regel == null || !RegelNaming.REPLACEMENTS.containsKey(regel.regelnummer)) {
                continue;
            }
            try (Writer writer = Files.newBufferedWriter(Path.of("./generated/libldt3/model/enums/" + RegelNaming.REPLACEMENTS.get(regel.regelnummer) + ".java"), StandardCharsets.UTF_8)) {
                enumTemplate.process(Map.of("enum", regel), writer);
            }
        }
    }
}
