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
 * E050
 */
public enum Abrechnungsinfo {
    /** GKV Laborfacharzt */
    GKV_Laborfacharzt("1"),
    /** GKV LG */
    GKV_LG("2"),
    /** PKV Laborfacharzt */
    PKV_Laborfacharzt("3"),
    /** PKV LG */
    PKV_LG("4"),
    Selektivvertrag("5"),
    IgeL("6"),
    /** Sonstige Kostenübernahme */
    SonstigeKostenuebernahme("7"),
    ASV("8"),
    /** GKV Laborfacharzt präventiv */
    GKV_Laborfacharzt_praeventiv("9"),
    /** GKV LG präventiv */
    GKV_LG_praeventiv("10"),
    /** keine Zuordnung (nur zulässig im Obj_0027) */
    keineZuordnung("11"),
    /** PräOP (Präoperative Laborleistungen**) */
    PraeOP("12"),
    /** GKV Krankenhaus */
    GKV_Krankenhaus("13"),
    /** PKV Krankenhaus */
    PKV_Krankenhaus("14"),
    /** GKV Muster 6 / 39 */
    GKV_Muster6_39("15"),
    /** GKV Muster 10C */
    GKV_Muster10C("16"),
    /** ÖGD */
    OeGD("17"),
    /** storniert (nur in Satzart 8215-Nachforderung zulässig)*
     * *   der Workflow einer Stornierung muss zwischen Einsender und Labor definiert
     * werden ** Laborleistungen, die dazu dienen, den Patienten auf eine ambulante
     * oder belegärztliche Operation vorzubereiten, werden dem einsendenden Arzt in
     * Rechnung gestellt und können nicht über die Kassenärztliche Vereinigung
     * abgerechnet werden (vgl. Abschnitt 31.1 des EBM) */
    storniert("99");

    public final String code;

    Abrechnungsinfo(String code) {
        this.code = code;
    }

}
