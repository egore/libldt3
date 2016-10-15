package libldt3.model.saetze;

import de.egore911.fuzz.Fuzzer;
import libldt3.LdtConstants;
import libldt3.LdtReader;
import libldt3.LdtWriter;
import libldt3.annotations.Feld;
import libldt3.annotations.Regelsatz;
import libldt3.model.regel.F001;
import libldt3.model.regel.F005;
import libldt3.model.regel.F006;
import libldt3.model.regel.F010;
import libldt3.model.regel.F011;
import libldt3.model.regel.F013;
import libldt3.model.regel.F014;
import libldt3.model.regel.F015;
import libldt3.model.regel.F017;
import libldt3.model.regel.Regel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class AuftragFuzzerTest {

	@Test
	public void fuzzerTest() throws IOException {
		Fuzzer.customHandler = field -> {
			Feld annotation = field.getAnnotation(Feld.class);
			if (annotation != null) {
				for (Regelsatz regelsatz : annotation.regelsaetze()) {
					for (Class<? extends Regel> klass : regelsatz.value()) {
						if (klass == F014.class) {
							return "001234561";
						} else if (klass == F011.class) {
							if (field.getType().isAssignableFrom(List.class)) {
								return Collections.singletonList("123456112");
							} else if (field.getType().isAssignableFrom(String.class)) {
								return "123456112";
							}
						} else if (klass == F010.class) {
							return "0123456[]";
						} else if (klass == F013.class) {
							return "X012345671";
						} else if (klass == F005.class) {
							return "896";
						} else if (klass == F001.class) {
							return "12345";
						} else if (klass == F006.class) {
							return "9959";
						} else if (klass == F015.class) {
							return "012/987";
						} else if (klass == F017.class) {
							return "0123";
						}
					}
					if (regelsatz.laenge() >= 0) {
						if (field.getType().isAssignableFrom(Integer.class)) {
							return (int) Math.pow(10, regelsatz.laenge()-1);
						} else if (field.getType().isAssignableFrom(String.class)) {
							return RandomStringUtils.randomAlphanumeric(regelsatz.laenge());
						} else if (field.getType().isAssignableFrom(List.class) && (((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]).getClass().isAssignableFrom(String.class)) {
							return Collections.singletonList(RandomStringUtils.randomAlphanumeric(regelsatz.laenge()));
						}
					} else if (regelsatz.maxLaenge() >= 0) {
						int min = 0;
						int max = regelsatz.maxLaenge();
						if (regelsatz.minLaenge() >= 0) {
							min = regelsatz.minLaenge();
							max -= min;
						}
						String s = RandomStringUtils.randomAlphanumeric(min + Fuzzer.RANDOM.nextInt(max));
						if (field.getType().isAssignableFrom(List.class)) {
							return Collections.singletonList(s);
						} else if (field.getType().isAssignableFrom(String.class)) {
							return s;
						}
					}
				}
			}
			return null;
		};
		Auftrag auftrag = Fuzzer.fuzz(Auftrag.class, "libldt3.model");
		Path file = File.createTempFile("auftrag_fuzzer_test", "ldt").toPath();
		new LdtWriter(LdtConstants.Mode.RELAXED).write(Collections.singletonList(auftrag), file);
		List<Satz> saetze = new LdtReader(LdtConstants.Mode.STRICT).read(file);

		assertThat(saetze, hasSize(1));
	}
}
