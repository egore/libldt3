package libldt3.model.regel;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class F001Test {

	private static final F001 f001 = new F001();

	@Test
	public void testEmpty() {
		assertThat(f001.isValid(""), equalTo(false));
	}

	@Test
	public void testNull() {
		assertThat(f001.isValid(null), equalTo(true));
	}

	@Test
	public void testFiveZeros() {
		assertThat(f001.isValid("00000"), equalTo(true));
	}

	@Test
	public void testFiveNines() {
		assertThat(f001.isValid("99999"), equalTo(true));
	}

	@Test
	public void testOneTwoThreeFourFive() {
		assertThat(f001.isValid("12345"), equalTo(true));
	}

	@Test
	public void testOneTwoThreeFour() {
		assertThat(f001.isValid("1234"), equalTo(false));
	}

	@Test
	public void testOneTwoThreeFourFiveSix() {
		assertThat(f001.isValid("123456"), equalTo(false));
	}

	@Test
	public void testABCDE() {
		assertThat(f001.isValid("ABCDE"), equalTo(false));
	}

	@Test
	public void testFiveSymbols() {
		assertThat(f001.isValid("/=()["), equalTo(false));
	}

	@Test
	public void testOneTwoThreeFourA() {
		assertThat(f001.isValid("1234A"), equalTo(false));
	}

	@Test
	public void testOneTwoThreeFourLowerA() {
		assertThat(f001.isValid("1234a"), equalTo(false));
	}

}
