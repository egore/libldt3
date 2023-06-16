/*
 * Copyright 2016-2023  Christoph Brill <opensource@christophbrill.de>
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
    Dummy_bei_eGK("00"),
    /** Schleswig-Holstein */
    SchleswigHolstein("01"),
    Hamburg("02"),
    Bremen("03"),
    Niedersachsen("17"),
    /** Westfalen-Lippe */
    WestfalenLippe("20"),
    Nordrhein("38"),
    Hessen("46"),
    @Deprecated
    Koblenz("47"),
    @Deprecated
    Rheinhessen("48"),
    @Deprecated
    Pfalz("49"),
    @Deprecated
    Trier("50"),
    /** Rheinland-Pfalz */
    RheinlandPfalz("51"),
    /** Baden-Württemberg */
    BadenWuerttemberg("52"),
    @Deprecated
    Nordbaden("55"),
    /** Südbaden */
    @Deprecated
    Suedbaden("60"),
    /** Nordwürttemberg */
    @Deprecated
    Nordwuerttemberg("61"),
    /** Südwürttemberg */
    @Deprecated
    Suedwuerttemberg("62"),
    Bayern("71"),
    Berlin("72"),
    Saarland("73"),
    KBV("74"),
    /** Mecklenburg-Vorpommern */
    MecklenburgVorpommern("78"),
    Brandenburg("83"),
    /** Sachsen-Anhalt */
    SachsenAnhalt("88"),
    /** Thüringen */
    Thueringen("93"),
    /** ( ) fusioniert, teilweise aber noch in Gebrauch */
    Sachsen("98");

    public final String code;

    WOP(String code) {
        this.code = code;
    }

}
