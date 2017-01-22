package libldt3.model.regel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.UUID;

import org.junit.Test;

public class F002Test {

	private static final F002 f002 = new F002();

	@Test
	public void testEmpty() {
		assertThat(f002.isValid(""), equalTo(false));
	}

	@Test
	public void testNull() {
		assertThat(f002.isValid(null), equalTo(true));
	}

	@Test
	public void testOnlyZeros() {
		assertThat(f002.isValid("00000000"), equalTo(false));
	}

	@Test
	public void testFirstOfJanuaryEver() {
		assertThat(f002.isValid("00000101"), equalTo(true));
	}

	@Test
	public void testLastOfDecemberEver() {
		assertThat(f002.isValid("99991231"), equalTo(true));
	}

	@Test
	public void testDateOfTestCreation() {
		assertThat(f002.isValid("20161023"), equalTo(true));
	}

	@Test
	public void testToLargeMonth() {
		assertThat(f002.isValid("20161323"), equalTo(false));
	}

	@Test
	public void testMaxMonth() {
		assertThat(f002.isValid("20169923"), equalTo(false));
	}

	@Test
	public void testToSmallMonth() {
		assertThat(f002.isValid("20160023"), equalTo(false));
	}

	@Test
	public void testToLargeDay() {
		assertThat(f002.isValid("20161032"), equalTo(false));
	}

	@Test
	public void testMaxDay() {
		assertThat(f002.isValid("20161099"), equalTo(false));
	}

	@Test
	public void testToSmallDay() {
		assertThat(f002.isValid("20161000"), equalTo(false));
	}

	@Test
	public void testToShort() {
		assertThat(f002.isValid("2016102"), equalTo(false));
	}

	@Test
	public void testToLong() {
		assertThat(f002.isValid("201610234"), equalTo(false));
	}

	@Test
	public void testAlphaOnly() {
		assertThat(f002.isValid("ABDCEFGH"), equalTo(false));
	}

	@Test
	public void testAlphanumeric() {
		assertThat(f002.isValid("2O161O23"), equalTo(false));
	}

	@Test
	public void testRandomString() {
		assertThat(f002.isValid(UUID.randomUUID().toString()), equalTo(false));
	}

}
