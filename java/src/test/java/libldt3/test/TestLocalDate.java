package libldt3.test;

import libldt3.LdtConstants;
import libldt3.LdtReader;
import libldt3.LdtWriter;
import libldt3.model.objekte.Befundinformationen;
import libldt3.model.objekte.Timestamp;
import libldt3.model.saetze.Befund;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;

import static libldt3.test.TempFileUtil.getTempFile;

public class TestLocalDate {

    @Test
    public void testWritingLocalDateOnly() throws IOException {
        Befund befund = new Befund();
        befund.befundinformationen = new Befundinformationen();
        befund.befundinformationen.timestampAuftragserteilung = new Timestamp();
        befund.befundinformationen.timestampAuftragserteilung.datum = LocalDate.parse("2018-04-20");

        var writer = new LdtWriter(LdtConstants.Mode.RELAXED);
        Path tempFile = getTempFile("testWithYearAnyMonth");
        writer.write(Collections.singletonList(befund), tempFile);

        Assertions.assertEquals("017727820180420", Files.readAllLines(tempFile).get(5));
    }

    @Test
    public void testWritingLocalDateAndTime() throws IOException {
        Befund befund = new Befund();
        befund.befundinformationen = new Befundinformationen();
        befund.befundinformationen.timestampAuftragserteilung = new Timestamp();
        befund.befundinformationen.timestampAuftragserteilung.datum = LocalDate.parse("2018-04-20");
        befund.befundinformationen.timestampAuftragserteilung.uhrzeit = new Timestamp.Uhrzeit();
        befund.befundinformationen.timestampAuftragserteilung.uhrzeit.value = "080000";

        var writer = new LdtWriter(LdtConstants.Mode.RELAXED);
        Path tempFile = getTempFile("testWithYearAnyMonth");
        writer.write(Collections.singletonList(befund), tempFile);

        Assertions.assertEquals("017727820180420", Files.readAllLines(tempFile).get(5));
        Assertions.assertEquals("0157279080000", Files.readAllLines(tempFile).get(6));
    }

    @Test
    public void testReadingLocalDateOnly() throws IOException {
        var reader = new LdtReader(LdtConstants.Mode.RELAXED);
        Path inFile = Paths.get(getClass().getResource("/befund-localdate.ldt").getPath());
        var read = reader.read(inFile);

        Assertions.assertNotNull(read);
        Assertions.assertEquals(1, read.size());
        Befund befund = (Befund) read.get(0);

        Assertions.assertEquals(LocalDate.parse("2018-04-20"), befund.befundinformationen.timestampAuftragserteilung.datum);
    }

    @Test
    public void testReadingLocalDateAndTime() throws IOException {
        var reader = new LdtReader(LdtConstants.Mode.RELAXED);
        Path inFile = Paths.get(getClass().getResource("/befund-localdatetime.ldt").getPath());
        var read = reader.read(inFile);

        Assertions.assertNotNull(read);
        Assertions.assertEquals(1, read.size());
        Befund befund = (Befund) read.get(0);

        Assertions.assertEquals(LocalDate.parse("2018-04-20"), befund.befundinformationen.timestampAuftragserteilung.datum);
        Assertions.assertEquals("080000", befund.befundinformationen.timestampAuftragserteilung.uhrzeit.value);
    }
}
