
package libldt3.model.enums;

/**
 * E054
 * 
 * Antikörpersuchtest (gegen Erythrozytenantigene)
 */
public enum Antikoerpersuchtest {

	positiv("1"),
	negativ("2"),
	unspezifisch("3"),
	/** in Abklärung */
	InAbklaerung("4"),
	/** Abklärung empfohlen */
	AbklaerungEmpfohlen("5");

	private final String code;

	Antikoerpersuchtest(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
