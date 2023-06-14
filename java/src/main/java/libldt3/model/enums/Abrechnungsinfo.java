/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
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
 * E050
 */
public enum Abrechnungsinfo {
    /** GKV Laborfacharzt */
    GkvLaborfacharzt("1"),
    /** GKV LG */
    GkvLg("2"),
    /** PKV Laborfacharzt */
    PkvLaborfacharzt("3"),
    /** PKG LG */
    PkvLg("4"),
    /** Selektivvertrag */
    Selektivvertrag("5"),
    /** IGeL */
    IGeL("6"),
    /** Sonstige Kostenübernahme */
    Sonstige_Kostenuebernahme("7"),
    /** ASV */
    Asv("8"),
    /** GKV Laborfacharzt präventiv */
    GkvLaborfacharztPraeventiv("9"),
    /** GKV LG präventiv */
    GkgLgPraeventiv("10"),
    /** keine Zuordnung */
    keine_Zuordnung("11"),
    /** PräOP (Präoperative Laborleistungen**) */
    PraeOP("12"),
    /** GKV Krankenhaus */
    GkvKrankenhaus("13"),
    /** PKV Krankenhaus */
    PkvKrankenhaus("14"),
    /** GKV Muster 6 / 39 */
    GkvMuster639("15"),
    /** GKV Muster 10C */
    GkvMuster10C("16"),
    /** ÖGD */
    OeGD("17"),
    /** storniert (nur in Satzart 8215-Nachforderung zulässig) */
    storniert("99");

    public final String code;

    Abrechnungsinfo(String code) {
        this.code = code;
    }

}
