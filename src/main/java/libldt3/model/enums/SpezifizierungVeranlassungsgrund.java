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
 * E147
 */
public enum SpezifizierungVeranlassungsgrund {

	/** Eingriff */
	Eingriff("01"),
	/** Medikamentengabe */
	Medikamentengabe("02"),
	/** unklares Fieber */
	UnklaresFieber("03"),
	/** Infektion */
	Infektion("04"),
	/** Rheuma */
	Rheuma("05"),
	/** Allergie */
	Allergie("06"),
	/** Herz/Kreislauf */
	HerzKreislauf("07"),
	/** Tumor */
	Tumor("08"),
	/** Impfungen */
	Impfungen("09"),
	/** Reisen */
	Reisen("10"),
	/** Immunität nach Infektion */
	ImmunitaetNachInfektion("11"),
	/** Sonstiges */
	Sonstiges("12");

	private final String code;

	SpezifizierungVeranlassungsgrund(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
