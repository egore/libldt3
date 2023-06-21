package libldt3.model.regel.test;

import libldt3.model.regel.format.F001;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class F001Test {

	private static final F001 f001 = new F001();

	@Test
	public void testEmpty() {
		Assertions.assertFalse(f001.isValid(""));
	}

	@Test
	public void testNull() {
		Assertions.assertTrue(f001.isValid(null));
	}

	@Test
	public void testFiveZeros() {
		Assertions.assertTrue(f001.isValid("00000"));
	}

	@Test
	public void testFiveNines() {
		Assertions.assertTrue(f001.isValid("99999"));
	}

	@Test
	public void testOneTwoThreeFourFive() {
		Assertions.assertTrue(f001.isValid("12345"));
	}

	@Test
	public void testOneTwoThreeFour() {
		Assertions.assertFalse(f001.isValid("1234"));
	}

	@Test
	public void testOneTwoThreeFourFiveSix() {
		Assertions.assertFalse(f001.isValid("123456"));
	}

	@Test
	public void testABCDE() {
		Assertions.assertFalse(f001.isValid("ABCDE"));
	}

	@Test
	public void testFiveSymbols() {
		Assertions.assertFalse(f001.isValid("/=()["));
	}

	@Test
	public void testOneTwoThreeFourA() {
		Assertions.assertFalse(f001.isValid("1234A"));
	}

	@Test
	public void testOneTwoThreeFourLowerA() {
		Assertions.assertFalse(f001.isValid("1234a"));
	}

}
