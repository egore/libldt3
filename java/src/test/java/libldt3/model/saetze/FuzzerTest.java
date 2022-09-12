package libldt3.model.saetze;

import de.egore911.fuzz.Fuzzer;
import libldt3.LdtConstants;
import libldt3.LdtReader;
import libldt3.LdtWriter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FuzzerTest {

	private final Fuzzer fuzzer = new Fuzzer(new LdtCustomHandler());

	@Test
	public void auftragFuzzerTest() throws IOException {
		Auftrag auftrag = fuzzer.fuzz(Auftrag.class, "libldt3.model");
		testSatz(auftrag);
	}

	@Test
	public void befundFuzzerTest() throws IOException {
		Befund befund = fuzzer.fuzz(Befund.class, "libldt3.model");
		testSatz(befund);
	}

	@Test
	public void laborDatenpaketAbschlussFuzzerTest() throws IOException {
		LaborDatenpaketAbschluss laborDatenpaketAbschluss = fuzzer.fuzz(LaborDatenpaketAbschluss.class, "libldt3.model");
		testSatz(laborDatenpaketAbschluss);
	}

	@Test
	public void laborDatenpaketHeaderFuzzerTest() throws IOException {
		LaborDatenpaketHeader laborDatenpaketHeader = fuzzer.fuzz(LaborDatenpaketHeader.class, "libldt3.model");
		testSatz(laborDatenpaketHeader);
	}

	@Test
	public void praxisDatenpaketAbschlussFuzzerTest() throws IOException {
		PraxisDatenpaketAbschluss praxisDatenpaketAbschluss = fuzzer.fuzz(PraxisDatenpaketAbschluss.class, "libldt3.model");
		testSatz(praxisDatenpaketAbschluss);
	}

	@Test
	public void PraxisDatenpaketHeaderFuzzerTest() throws IOException {
		PraxisDatenpaketHeader praxisDatenpaketHeader = fuzzer.fuzz(PraxisDatenpaketHeader.class, "libldt3.model");
		testSatz(praxisDatenpaketHeader);
	}

	private void testSatz(Satz satz) throws IOException {
		Path file = File.createTempFile("auftrag_fuzzer_test", ".ldt").toPath();
		new LdtWriter(LdtConstants.Mode.RELAXED).write(Collections.singletonList(satz), file);
		List<Satz> saetze = new LdtReader(LdtConstants.Mode.RELAXED).read(file);
		Assertions.assertEquals(saetze.size(), 1);
	}
}
