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
 * E052
 */
public enum Normwertspezifikation {

	/** Methodenspezifische Standards nach WHO */
	MethodenspezifischeStandardsWHO("10"),
	/** Methodenspezifische Standards nach IFCC (u.a. serologische Verfahren) */
	MethodenspezifischeStandardsIFCC("11"),
	/** Methodenspezifische Standards nach DGKL */
	MethodenspezifischeStandardsDGKL("12"),
	/** Sonstige Standards */
	SonstigeStandards("13"),
	/** Patientenspezifische Einflussgröße Alter betreffend */
	PatientenspezifischeEinflussgroesseAlter("20"),
	/** Patientenspezifische Einflussgröße Geschlecht betreffend */
	PatientenspezifischeEinflussgroesseGeschlecht("21"),
	/** Patientenspezifische Einflussgröße Alter und Geschlecht betreffend */
	PatientenspezifischeEinflussgroesseAlterGeschlecht("22"),
	/** Patientenspezifische Einflussgröße SSW betreffend */
	PatientenspezifischeEinflussgroesseSSW("23"),
	/** Patientenspezifische Einflussgröße Alter und SSW betreffend */
	PatientenspezifischeEinflussgroesseAlterSSW("24"),
	/** weitere patientenspezifische Einflussgrößen (z.B. Mediaktion) */
	WeiterePatientenspezifischeEinflussgroessen("25"),
	/** Information zu Patientenspezifischer Einflussgröße Alter fehlte */
	InformationPatientenspezifischerEinflussgroesseAlterFehlte("26"),
	/** Information zu Patientenspezifischer Einflussgröße Geschlecht fehlte */
	InformationPatientenspezifischerEinflussgroesseGeschlechtFehlte("27"),
	/** Information zu Patientenspezifischer Einflussgröße Alter und Geschlecht fehlte */
	InformationPatientenspezifischerEinflussgroesseAlterGeschlechtFehlte("28"),
	/** Funktionsprofile */
	Funktionsprofile("30");

	public final String code;

	Normwertspezifikation(String code) {
		this.code = code;
	}

}
