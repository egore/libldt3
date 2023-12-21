/*
 * Copyright 2016-2023  Christoph Brill &lt;opensource@christophbrill.de&gt;
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
    MethodenspezifischeStandards_nachWHO("10"),
    /** Methodenspezifische Standards nach IFCC (u.a. serologische Verfahren) */
    MethodenspezifischeStandards_nachIFCC("11"),
    /** Methodenspezifische Standards nach DGKL */
    MethodenspezifischeStandards_nachDGKL("12"),
    /** Sonstige Standards 1) */
    SonstigeStandards("13"),
    /** Patientenspezifische Einflussgröße "Alter" betreffend */
    PatientenspezifischeEinflussgroesseAlter_betreffend("20"),
    /** Patientenspezifische Einflussgröße "Geschlecht" betreffend */
    PatientenspezifischeEinflussgroesseGeschlecht_betreffend("21"),
    /** Patientenspezifische Einflussgröße "Alter + Geschlecht" betreffend */
    PatientenspezifischeEinflussgroesseAlter_und_Geschlecht_betreffend("22"),
    /** Patientenspezifische Einflussgröße "SSW" betreffend */
    PatientenspezifischeEinflussgroesseSSW_betreffend("23"),
    /** Patientenspezifische Einflussgröße "Alter + SSW" betreffend */
    PatientenspezifischeEinflussgroesseAlter_und_SSW_betreffend("24"),
    /** weitere patientenspezifische Einflussgrößen (z.B. Medikation) 1) */
    weitere_patientenspezifischeEinflussgroessen("25"),
    /** Information zu Patientenspezifischer Einflussgröße "Alter" fehlte */
    Information_zuPatientenspezifischerEinflussgroesseAlter_fehlte("26"),
    /** Information zu Patientenspezifischer Einflussgröße "Geschlecht" fehlte */
    Information_zuPatientenspezifischerEinflussgroesseGeschlecht_fehlte("27"),
    /** Information zu Patientenspezifischer Einflussgröße Alter und Geschlecht fehlte */
    InformationPatientenspezifischerEinflussgroesseAlterGeschlechtFehlte("28"),
    /** Funktionsprofile 1)
     * 1) Zur weiteren Spezifikation FK 8167 verwenden. */
    Funktionsprofile("30");

    public final String code;

    Normwertspezifikation(String code) {
        this.code = code;
    }

}
