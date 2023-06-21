package libldt3.model.regel.test;

import libldt3.model.regel.format.F002;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class F002Test {

	private static final F002 f002 = new F002();

	@Test
	public void testEmpty() {
		Assertions.assertFalse(f002.isValid(""));
	}

	@Test
	public void testNull() {
		Assertions.assertTrue(f002.isValid(null));
	}

	@Test
	public void testOnlyZeros() {
		Assertions.assertFalse(f002.isValid("00000000"));
	}

	@Test
	public void testFirstOfJanuaryEver() {
		Assertions.assertTrue(f002.isValid("00000101"));
	}

	@Test
	public void testLastOfDecemberEver() {
		Assertions.assertTrue(f002.isValid("99991231"));
	}

	@Test
	public void testDateOfTestCreation() {
		Assertions.assertTrue(f002.isValid("20161023"));
	}

	@Test
	public void testToLargeMonth() {
		Assertions.assertFalse(f002.isValid("20161323"));
	}

	@Test
	public void testMaxMonth() {
		Assertions.assertFalse(f002.isValid("20169923"));
	}

	@Test
	public void testToSmallMonth() {
		Assertions.assertFalse(f002.isValid("20160023"));
	}

	@Test
	public void testToLargeDay() {
		Assertions.assertFalse(f002.isValid("20161032"));
	}

	@Test
	public void testMaxDay() {
		Assertions.assertFalse(f002.isValid("20161099"));
	}

	@Test
	public void testToSmallDay() {
		Assertions.assertFalse(f002.isValid("20161000"));
	}

	@Test
	public void testToShort() {
		Assertions.assertFalse(f002.isValid("2016102"));
	}

	@Test
	public void testToLong() {
		Assertions.assertFalse(f002.isValid("201610234"));
	}

	@Test
	public void testAlphaOnly() {
		Assertions.assertFalse(f002.isValid("ABDCEFGH"));
	}

	@Test
	public void testAlphanumeric() {
		Assertions.assertFalse(f002.isValid("2O161O23"));
	}

	@Test
	public void testRandomString() {
		Assertions.assertFalse(f002.isValid(UUID.randomUUID().toString()));
	}

}
