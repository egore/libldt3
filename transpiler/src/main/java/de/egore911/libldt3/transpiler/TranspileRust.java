package de.egore911.libldt3.transpiler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import de.egore911.libldt3.transpiler.directives.rust.ConvertRustTypeDirective;
import de.egore911.libldt3.transpiler.directives.rust.GenUseDirective;
import de.egore911.libldt3.transpiler.directives.rust.SnakeCaseDirective;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spoon.MavenLauncher;
import spoon.reflect.declaration.CtType;

public class TranspileRust {

    private static final Logger LOG = LoggerFactory.getLogger(TranspileRust.class);

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
        config.setTemplateLoader(new ClassTemplateLoader(TranspileCsharp.class, "/rust/"));

        // Add several directives which were simpler to implement in Java than in .ftl files
        config.setSharedVariable("genuse", new GenUseDirective());
        config.setSharedVariable("converttype", new ConvertRustTypeDirective());
        config.setSharedVariable("snakecase", new SnakeCaseDirective());

        Path base = Paths.get("./rust");

        // TODO mod.rs
        Map<String, Set<String>> mods = new HashMap<>();

        for (CtType<?> type : launcher.getModel().getAllTypes()) {
            if (type.getPackage().getQualifiedName().equals("libldt3.model.saetze")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.objekte")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.enums")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.regel")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.regel.erlaubt")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.regel.format")
                    || type.getPackage().getQualifiedName().equals("libldt3.model.regel.kontext")) {
                Set<String> mod = mods.computeIfAbsent(type.getPackage().getQualifiedName().substring("libldt3.model.".length()), x -> new TreeSet<>());
                mod.add(type.getSimpleName());

                Path file = getOutputFile(base, type);
                Template template;
                if (type.isClass()) {
                    template = config.getTemplate("struct.ftl");
                } else if (type.isInterface()) {
                    template = config.getTemplate("trait.ftl");
                } else if (type.isEnum()) {
                    template = config.getTemplate("enum.ftl");
                } else {
                    throw new UnsupportedOperationException(type.getClass().getSimpleName());
                }
                try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                    template.process(Collections.singletonMap(template.getName().replace(".ftl", ""), type), writer);
                }

            }
        }

        for (Map.Entry<String, Set<String>> mod : mods.entrySet()) {
            int lastDot = mod.getKey().lastIndexOf('.');
            if (lastDot < 0) {
                continue;
            }
            String parent = mod.getKey().substring(0, lastDot);
            if (!parent.equals(mod.getKey()) && mods.containsKey(parent)) {
                mods.get(parent).add(mod.getKey().substring(lastDot + 1));
            }
        }

        for (Map.Entry<String, Set<String>> mod : mods.entrySet()) {
            Path dir = base.resolve("src").resolve("model");
            for (String path : mod.getKey().split("\\.")) {
                dir = dir.resolve(path);
            }
            Path file = dir.resolve("mod.rs");
            try (FileWriter fw = new FileWriter(file.toFile());
                    BufferedWriter w = new BufferedWriter(fw)) {
                fw.write("#![allow(non_snake_case)]\n\n");
                for (String m : mod.getValue()) {
                    w.write("pub mod ");
                    w.write(m);
                    w.write(";\n");
                }
            }
        }

        LOG.info("DONE");
    }

    private static Path getOutputFile(Path base, CtType<?> type) throws IOException {
        Path dir = base;
        for (String p : type.getPackage().getQualifiedName().replaceAll("^libldt3", "src").split("\\.")) {
            dir = dir.resolve(p);
        }
        Files.createDirectories(dir);

        return dir.resolve(type.getSimpleName() + ".rs");
    }

}
