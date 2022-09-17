/*
 * Copyright 2021  Christoph Brill <opensource@christophbrill.de>
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

public enum Dokumententyp {
    Muster6("006"),
    Muster10("010"),
    Muster10A("10A"),
    Muster39("039"),
    AuftragsdokumentPKV_FA("090"),
    AuftragsdokumentPKV_LG("091"),
    Auftragsdokument_IGeL("092"),
    Auftragsdokument_Sonstige_Kostenuebernahme("093"),
    Auftragsdokument_Selektivvertrag("094"),
    Laborbefund("100"),
    Mutterpass("101"),
    Impfpass("102"),
    Notfallausweis("103"),
    Patientenbefund("110"),
    Medikationsplan("120"),
    Verlaufsbericht("150"),
    Behandlungsbericht("160"),
    Einverstaendniserklärung_lt_GenDG_Gen_Diagnostik_Gesetz("200"),
    weitere_laborspezifische_Dokumente("250"),
    Allergie_RAST("251"),
    Molekulardiagnostik("252"),
    Endokrinologie("253"),
    Virologie("254"),
    Mikrobiologie("255"),
    Funktionsdiagnostik("256"),
    Infektionsserologie("257"),
    Kinderwunsch("258"),
    Meldung_gemaess_IfSG_Infektionsschutz_Gesetz("300"),
    Meldung_Krebsregister("301"),
    Normbereichsgrafik("400"),
    Rechnung("500"),
    LDT_Daten("900"),
    sonstige("999");

    public final String code;

    Dokumententyp(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
