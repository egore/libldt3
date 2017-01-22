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
 * E022
 */
public enum WOP {

	/** Dummy bei eGK */
	Dummy("00"),
	/** Schleswig-Holstein */
	SchleswigHolstein("01"),
	/** Hamburg */
	Hamburg("02"),
	/** Bremen */
	Bremen("03"),
	/** Niedersachsen */
	Niedersachsen("17"),
	/** Westfalen-Lippe */
	WestfalenLippe("20"),
	/** Nordrhein */
	Nordrhein("38"),
	/** Hessen */
	Hessen("46"),
	@Deprecated
	Koblenz("47"),
	/** Rheinhessen */
	@Deprecated
	Rheinhessen("48"),
	/** Pfalz */
	@Deprecated
	Pfalz("49"),
	/** Trier */
	@Deprecated
	Trier("51"),
	/** Rheinland-Pfalz */
	RheinlandPfalz("51"),
	/** Baden-Württemberg */
	BadenWuerttemberg("52"),
	/** Nordbaden */
	@Deprecated
	Nordbaden("55"),
	/** Südbaden */
	@Deprecated
	Suedbaden("60"),
	@Deprecated
	/** Nordwürttemberg */
	Nordwuerttemberg("61"),
	@Deprecated
	/** Südwürttemberg */
	Suedwuerttemberg("62"),
	/** Bayern */
	Bayern("71"),
	/** Berlin */
	Berlin("72"),
	/** Saarland */
	Saarland("73"),
	/** KBV */
	KBV("74"),
	/** Mecklenburg-Vorpommern */
	MecklenburgVorpommern("78"),
	/** Brandenburg */
	Brandenburg("83"),
	/** Sachsen-Anhalt */
	SachsenAnhalt("88"),
	/** Thüringen */
	Thueringen("93"),
	/** Sachsen */
	Sachsen("98");

	private final String code;

	private WOP(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
