/*
 * Copyright 2016  Christoph Brill <egore911@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package libldt3.model.enums;

/**
 * E027
 */
public enum StatusPerson {
	/** Erstveranlasser */
	Erstveranlasser("01"),
	/** Einsender Arzt */
	EinsenderArzt("02"),
	/** Einsender Sonstige */
	EinsenderSonstige("03"),
	/** Versicherter */
	Versicherter("04"),
	/** Rechnungsempfänger */
	Rechnungsempfaenger("05"),
	/** Bevollmächtigter */
	Bevollmaechtigter("06"),
	/** Laborarzt/Befundersteller */
	Laborarzt_Befundersteller("07"),
	/** Leistungserbringer */
	Leistungserbringer("08"),
	/** Softwareverantwortlicher */
	Softwareverantwortlicher("09"),
	/** Zusätzlicher Befundempfänger */
	ZusaetzlicherBefundempfaenger("10"),
	/** Halter (eines Tieres) */
	Tierhalter("11"),
	/** Patient */
	Patient("12"),
	/** Überweiser */
	Ueberweiser("14"),
	/** sonstige juristische Person */
	sonstigeJuristischePerson("16"),
	/** Medizinisch-technische/r Assistent/in (MTA) */
	MTA("17"),
	/** Medizinische/r Fachangestellte/r (MFA) */
	MFA("18");

	private final String code;

	private StatusPerson(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}