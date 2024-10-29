/*
 * Copyright 2016-2024  Christoph Brill &lt;opensource@christophbrill.de&gt;
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
 * E020
 */
public enum DmpKennzeichnung {
    /** keine Angabe */
    keineAngabe("00"),
    /** Diabetes mellitus Typ 2 */
    Diabetes_mellitusTyp2("01"),
    Brustkrebs("02"),
    /** Koronare Herzkrankheit */
    KoronareHerzkrankheit("03"),
    /** Diabetes mellitus Typ 1 */
    Diabetes_mellitusTyp1("04"),
    /** Asthma bronchiale */
    Asthma_bronchiale("05"),
    /** COPD (chronic obstructive pulmo-nary disease) */
    COPD("06"),
    /** Chronische Herzinsuffizienz */
    ChronischeHerzinsuffizienz("07"),
    Depression("08"),
    /** Rückenschmerz */
    Rueckenschmerz("09"),
    Rheuma("10"),
    Osteoporose("11"),
    Adipositas("12"),
    /** Diabetes Typ 2 und KHK */
    DiabetesTyp2_undKHK("30"),
    /** Asthma und Diabetes Typ 2 */
    Asthma_undDiabetesTyp2("31"),
    /** COPD und Diabetes Typ 2 */
    COPD_undDiabetesTyp2("32"),
    /** COPD und KHK */
    COPD_undKHK("33"),
    /** COPD, Diabetes Typ 2 und KHK */
    COPD_DiabetesTyp2_undKHK("34"),
    /** Asthma und KHK */
    Asthma_undKHK("35"),
    /** Asthma, Diabetes Typ 2 und KHK */
    AsthmaDiabetesTyp2_undKHK("36"),
    /** Brustkrebs und Diabetes Typ 2 */
    Brustkrebs_undDiabetesTyp2("37"),
    /** Diabetes Typ 1 und KHK */
    DiabetesTyp1_undKHK("38"),
    /** Asthma und Diabetes Typ 1 */
    Asthma_undDiabetesTyp1("39"),
    /** Asthma und Brustkrebs */
    Asthma_undBrustkrebs("40"),
    /** Brustkrebs und KHK */
    Brustkrebs_undKHK("41"),
    /** Brustkrebs und COPD */
    Brustkrebs_undCOPD("42"),
    /** COPD und Diabetes Typ 1 */
    COPD_undDiabetesTyp1("43"),
    /** Brustkrebs, Diabetes Typ 2 und KHK */
    BrustkrebsDiabetesTyp2_undKHK("44"),
    /** Asthma, Brustkrebs und Diabetes Typ 2 */
    AsthmaBrustkrebs_undDiabetesTyp2("45"),
    /** Brustkrebs und Diabetes Typ 1 */
    Brustkrebs_undDiabetesTyp1("46"),
    /** COPD, Diabetes Typ 1 und KHK */
    COPD_DiabetesTyp1_undKHK("47"),
    /** Brustkrebs, COPD und Diabetes Typ 2 */
    BrustkrebsCOPD_undDiabetesTyp2("48"),
    /** Asthma, Diabetes Typ 1 und KHK */
    AsthmaDiabetesTyp1_undKHK("49"),
    /** Asthma, Brustkrebs und KHK */
    AsthmaBrustkrebs_undKHK("50"),
    /** Brustkrebs, COPD und KHK */
    BrustkrebsCOPD_undKHK("51"),
    /** Brustkrebs, COPD, Diabetes Typ 2 und KHK */
    BrustkrebsCOPD_DiabetesTyp2_undKHK("52"),
    /** Asthma, Brustkrebs, Diabetes Typ 2 und KHK */
    AsthmaBrustkrebsDiabetesTyp2_undKHK("53"),
    /** Brustkrebs, Diabetes Typ 1 und KHK */
    BrustkrebsDiabetesTyp1_undKHK("54"),
    /** Asthma, Brustkrebs und Diabetes Typ 1 */
    AsthmaBrustkrebs_undDiabetesTyp1("55"),
    /** Asthma, Brustkrebs, Diabetes Typ 1 und KHK */
    AsthmaBrustkrebsDiabetesTyp1_undKHK("56"),
    /** Brustkrebs, COPD und Diabetes Typ 1 */
    BrustkrebsCOPD_undDiabetesTyp1("57"),
    /** Brustkrebs, COPD, Diabetes Typ 1 und KHK */
    BrustkrebsCOPD_DiabetesTyp1_undKHK("58");

    public final String code;

    DmpKennzeichnung(String code) {
        this.code = code;
    }

}
