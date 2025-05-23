package de.egore911.libldt3.transpiler;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import de.egore911.libldt3.transpiler.directives.ConstructorFixupDirective;
import de.egore911.libldt3.transpiler.directives.ConvertClassDirective;
import de.egore911.libldt3.transpiler.directives.InvocationFixupDirective;
import de.egore911.libldt3.transpiler.directives.NamespaceDirective;
import de.egore911.libldt3.transpiler.directives.cs.ConvertCsTypeDirective;
import de.egore911.libldt3.transpiler.directives.cs.GenUsingDirective;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spoon.MavenLauncher;
import spoon.reflect.declaration.CtType;

public class TranspileCsharp {

    private static final Logger LOG = LoggerFactory.getLogger(TranspileCsharp.class);

    public static void main(String[] args) throws IOException, TemplateException {

        // Install java.util.Logging bridge to slf4j
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        // Read the maven model from the java folder
        MavenLauncher launcher = new MavenLauncher("./java", MavenLauncher.SOURCE_TYPE.APP_SOURCE);
        launcher.getEnvironment().setLevel("DEBUG");
        launcher.getEnvironment().setNoClasspath(true);
        launcher.run();

        // Build up the freemarker configuration for C#
        Configuration config = new Configuration(Configuration.VERSION_2_3_32);
        config.setTemplateLoader(new ClassTemplateLoader(TranspileCsharp.class, "/cs/"));

        // Add several directives which were simpler to implement in Java than in .ftl files
        config.setSharedVariable("namespace", new NamespaceDirective());
        config.setSharedVariable("genusing", new GenUsingDirective());
        config.setSharedVariable("converttype", new ConvertCsTypeDirective());
        config.setSharedVariable("convertclass", new ConvertClassDirective());
        config.setSharedVariable("invocationfixup", new InvocationFixupDirective());
        config.setSharedVariable("constructorfixup", new ConstructorFixupDirective());

        // Make the current year available as variable
        config.setSharedVariable("year", Integer.toString(LocalDate.now().getYear()));

        Path base = Paths.get("./cs");

        Path model = base.resolve("libldt3").resolve("model");
        deleteFiles(model.resolve("saetze"));
        deleteFiles(model.resolve("saetze"));
        deleteFiles(model.resolve("objekte"));
        deleteFiles(model.resolve("enums"));
        deleteFiles(model.resolve("regel"));
        deleteFiles(model.resolve("regel").resolve("erlaubt"));
        deleteFiles(model.resolve("regel").resolve("format"));
        deleteFiles(model.resolve("regel").resolve("kontext"));

        for (CtType<?> type : launcher.getModel().getAllTypes()) {
            if (type.getPackage().getQualifiedName().equals("libldt3.model.saetze")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.objekte")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.enums")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.regel")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.regel.erlaubt")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.regel.format")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.regel.kontext")) {
                Path file = getOutputFile(base, type);
                Template template;
                if (type.isClass()) {
                    template = config.getTemplate("class.ftl");
                } else if (type.isEnum()) {
                    template = config.getTemplate("enum.ftl");
                } else if (type.isInterface()) {
                    template = config.getTemplate("interface.ftl");
                } else {
                    throw new UnsupportedOperationException(type.getClass().getSimpleName());
                }
                try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                    template.process(Collections.singletonMap(template.getName().replace(".ftl", ""), type), writer);
                }

            }
        }

        LOG.info("DONE");

    }

    private static void deleteFiles(Path path) throws IOException {
        try (var files = Files.list(path)) {
            files
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            LOG.warn("Failed to delete file {}: {}", file, e.getMessage());
                        }
                    });
        }
    }

    private static Path getOutputFile(Path base, CtType<?> type) throws IOException {
        Path dir = base;
        for (String p : type.getPackage().getQualifiedName().split("\\.")) {
            dir = dir.resolve(p);
        }
        Files.createDirectories(dir);

        return dir.resolve(type.getSimpleName() + ".cs");
    }

}
