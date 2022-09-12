package de.egore911.libldt3.transpiler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;

import org.slf4j.bridge.SLF4JBridgeHandler;

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

	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		 SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)
		 SLF4JBridgeHandler.install();
		
		MavenLauncher launcher = new MavenLauncher("../java", MavenLauncher.SOURCE_TYPE.APP_SOURCE);

		launcher.run();
		
		Configuration config = new Configuration(Configuration.VERSION_2_3_31);
		config.setTemplateLoader(new ClassTemplateLoader(TranspileCsharp.class, "/cs/"));
		
		config.setSharedVariable("namespace", new NamespaceDirective());
		
		for (CtType<?> type : launcher.getModel().getAllTypes()) {
			if (type.isClass() && type.getSimpleName().equals("LaborDatenpaketAbschluss")) {
				Template template = config.getTemplate("class.ftl");
				template.process(Collections.singletonMap("class", type), new OutputStreamWriter(System.out));
			}
		}
		
		
	}

}
