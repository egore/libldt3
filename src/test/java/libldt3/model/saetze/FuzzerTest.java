package libldt3.model.saetze;

import de.egore911.fuzz.Fuzzer;
import libldt3.LdtConstants;
import libldt3.LdtReader;
import libldt3.LdtWriter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class FuzzerTest {

	@Test
	public void auftragFuzzerTest() throws IOException {
		Auftrag auftrag = new Fuzzer(new LdtCustomHandler()).fuzz(Auftrag.class, "libldt3.model");
		testSatz(auftrag);
	}

	@Test
	public void befundFuzzerTest() throws IOException {
		Befund befund = new Fuzzer(new LdtCustomHandler()).fuzz(Befund.class, "libldt3.model");
		testSatz(befund);
	}

	private void testSatz(Satz satz) throws IOException {
		Path file = File.createTempFile("auftrag_fuzzer_test", ".ldt").toPath();
		new LdtWriter(LdtConstants.Mode.RELAXED).write(Collections.singletonList(satz), file);
		List<Satz> saetze = new LdtReader(LdtConstants.Mode.STRICT).read(file);
		assertThat(saetze, hasSize(1));
	}
}
