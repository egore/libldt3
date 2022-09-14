package de.egore911.libldt3.transpiler;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;

import org.slf4j.bridge.SLF4JBridgeHandler;

import de.egore911.libldt3.transpiler.directives.ConvertTypeDirective;
import de.egore911.libldt3.transpiler.directives.ExpressionDirective;
import de.egore911.libldt3.transpiler.directives.GenUsingDirective;
import de.egore911.libldt3.transpiler.directives.NamespaceDirective;
import freemarker.cache.ClassTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import spoon.MavenLauncher;
import spoon.reflect.declaration.CtType;

public class TranspileCsharp {

	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		SLF4JBridgeHandler.removeHandlersForRootLogger(); // (since SLF4J 1.6.5)
		SLF4JBridgeHandler.install();

		MavenLauncher launcher = new MavenLauncher("../java", MavenLauncher.SOURCE_TYPE.APP_SOURCE);

		launcher.run();

		Configuration config = new Configuration(Configuration.VERSION_2_3_31);
		config.setTemplateLoader(new ClassTemplateLoader(TranspileCsharp.class, "/cs/"));

		config.setSharedVariable("namespace", new NamespaceDirective());
		config.setSharedVariable("genusing", new GenUsingDirective());
		config.setSharedVariable("converttype", new ConvertTypeDirective());
		config.setSharedVariable("expression", new ExpressionDirective());

		config.setSharedVariable("year", Integer.toString(LocalDate.now().getYear()));

		Path base = Paths.get("../cs");

		for (CtType<?> type : launcher.getModel().getAllTypes()) {
			if (type.getPackage().getQualifiedName().equals("libldt3.model.saetze")
					|| type.getPackage().getQualifiedName().equals("libldt3.model.objekte")) {
				if (type.isClass()) {

					Path file = getOutputFile(base, type);

					Template template = config.getTemplate("class.ftl");

					try (Writer writer = Files.newBufferedWriter(file, Charset.forName("UTF-8"))) {
						template.process(Collections.singletonMap("class", type), writer);
					}

					System.err.println(file.toAbsolutePath());
				}
			}
		}

	}

	private static Path getOutputFile(Path base, CtType<?> type) throws IOException {
		Path dir = base;
		for (String p : type.getPackage().getQualifiedName().split("\\.")) {
			dir = dir.resolve(p);
		}
		Files.createDirectories(dir);

		Path file = dir.resolve(type.getSimpleName() + ".cs");
		return file;
	}

}
