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
 * E053
 */
public enum Dokumententyp {
    /** Muster 6 */
    Muster6("006"),
    /** Muster 10 */
    Muster10("010"),
    /** Muster 10A */
    Muster10A("10A"),
    /** Muster 39 */
    Muster39("039"),
    /** Auftragsdokument PKV-FA */
    AuftragsdokumentPKV_FA("090"),
    /** Auftragsdokument PKV-LG */
    AuftragsdokumentPKV_LG("091"),
    /** Auftragsdokument IGeL */
    AuftragsdokumentIGeL("092"),
    /** Auftragsdokument Sonstige Kostenübernahme */
    AuftragsdokumentSonstigeKostenuebernahme("093"),
    /** Auftragsdokument Selektivvertrag */
    AuftragsdokumentSelektivvertrag("094"),
    Laborbefund("100"),
    Mutterpass("101"),
    Impfpass("102"),
    Notfallausweis("103"),
    Patientenbefund("110"),
    Medikationsplan("120"),
    Verlaufsbericht("150"),
    Behandlungsbericht("160"),
    /** Einverständniserklärung lt. GenDG (Gen-Diagnostik-Gesetz) */
    Einverstaendniserklaerung_lt_GenDG("200"),
    /** weitere laborspezifische Dokumente */
    weitere_laborspezifischeDokumente("250"),
    /** Allergie/RAST */
    Allergie_RAST("251"),
    Molekulardiagnostik("252"),
    Endokrinologie("253"),
    Virologie("254"),
    Mikrobiologie("255"),
    Funktionsdiagnostik("256"),
    Infektionsserologie("257"),
    Kinderwunsch("258"),
    /** Meldung gemäß IfSG (Infektionsschutz-Gesetz) */
    Meldung_gemaessIfSG("300"),
    /** Meldung Krebsregister */
    MeldungKrebsregister("301"),
    Normbereichsgrafik("400"),
    Rechnung("500"),
    /** LDT-Datensatz */
    LDT_Datensatz("900"),
    /** Hinweis: Werte 001 bis 089 reserviert für Muster der vertragsärztlichen
     * Versorgung */
    sonstige("999");

    public final String code;

    Dokumententyp(String code) {
        this.code = code;
    }

}
