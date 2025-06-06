package libldt3.test;

import libldt3.LdtConstants;
import libldt3.LdtReader;
import libldt3.model.saetze.Auftrag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class TestF003CornerCases {

    @Test
    public void testWithYearOnly() throws IOException {
        var reader = new LdtReader(LdtConstants.Mode.RELAXED);

        var read = reader.read(getClass().getResource("/person-19900000.ldt").getPath());

        Assertions.assertNotNull(read);
        Assertions.assertEquals(1, read.size());
        Assertions.assertNotNull(read.get(0));
        Assertions.assertInstanceOf(Auftrag.class, read.get(0));
        Assertions.assertNotNull(((Auftrag)read.get(0)).patient);
        Assertions.assertNotNull(((Auftrag)read.get(0)).patient.person);
        Assertions.assertNotNull(((Auftrag)read.get(0)).patient.person.geburtsdatum);
        Assertions.assertInstanceOf(Year.class, ((Auftrag)read.get(0)).patient.person.geburtsdatum);
        Assertions.assertEquals(Year.of(1900), ((Auftrag)read.get(0)).patient.person.geburtsdatum);


    }

    @Test
    public void testWithYearAnyMonth() throws IOException {
        var reader = new LdtReader(LdtConstants.Mode.RELAXED);

        var read = reader.read(getClass().getResource("/person-19900200.ldt").getPath());

        Assertions.assertNotNull(read);
        Assertions.assertEquals(1, read.size());
        Assertions.assertNotNull(read.get(0));
        Assertions.assertInstanceOf(Auftrag.class, read.get(0));
        Assertions.assertNotNull(((Auftrag) read.get(0)).patient);
        Assertions.assertNotNull(((Auftrag) read.get(0)).patient.person);
        Assertions.assertNotNull(((Auftrag) read.get(0)).patient.person.geburtsdatum);
        Assertions.assertInstanceOf(YearMonth.class, ((Auftrag) read.get(0)).patient.person.geburtsdatum);
        Assertions.assertEquals(YearMonth.of(1900, 2), ((Auftrag) read.get(0)).patient.person.geburtsdatum);
    }

    @Test
    public void testWithFullDate() throws IOException {
        var reader = new LdtReader(LdtConstants.Mode.RELAXED);

        var read = reader.read(getClass().getResource("/person-19900204.ldt").getPath());

        Assertions.assertNotNull(read);
        Assertions.assertEquals(1, read.size());
        Assertions.assertNotNull(read.get(0));
        Assertions.assertInstanceOf(Auftrag.class, read.get(0));
        Assertions.assertNotNull(((Auftrag) read.get(0)).patient);
        Assertions.assertNotNull(((Auftrag) read.get(0)).patient.person);
        Assertions.assertNotNull(((Auftrag) read.get(0)).patient.person.geburtsdatum);
        Assertions.assertInstanceOf(LocalDate.class, ((Auftrag) read.get(0)).patient.person.geburtsdatum);
        Assertions.assertEquals(LocalDate.of(1900, 2, 4), ((Auftrag) read.get(0)).patient.person.geburtsdatum);
    }
}
