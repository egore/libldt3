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
package libldt3.model.objekte;

import java.time.LocalDate;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.EinschreibestatusSelektivvertraege;
import libldt3.model.enums.Gebuehrenordnung;
import libldt3.model.regel.F002;

/**
 * Mit diesem Objekt werden die Informationen für die Abrechnung von
 * Untersuchungsanforderungen zusammengefasst, welche im Rahmen von
 * Selektivverträgen und damit außerhalb der budgetären Leistungen erbracht werden.
 * Die Möglichkeit zum Abschluss von Selektivverträgen besteht im Wesentlichen in
 * der hausarztzentrierten Versorgung (§ 73 b SGB V), bei strukturierten
 * Behandlungsprogrammen für chronische Erkrankungen (Disease-Management-Programme)
 * (§ 137 f SGB V) und in der Integrierten Versorgung (§§ 140ff SGB V).
 */
@Objekt("0006")
public class AbrechnungSelektivvertrag implements Kontext {

    @Feld(value = "3130", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public EinschreibestatusSelektivvertraege einschreibestatusSelektivvertraege;
    @Feld(value = "3134", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String bezeichnungSelektivvertrag;
    @Feld(value = "4134", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 45)
    public String kostentraegername;
    @Feld(value = "3131", feldart = Feldart.kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate teilnahmeVon;
    @Feld(value = "3132", feldart = Feldart.kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate teilnahmeBis;
    @Feld(value = "3133", feldart = Feldart.bedingt_kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate datumAntragstellung;
    @Feld(value = "7430", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public String patientenIdSelektivvertrag;
    @Feld(value = "4121", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Gebuehrenordnung gebuehrenordnung;
    @Feld(value = "8148", feldart = Feldart.muss)
    @Regelsatz(laenge = 12)
    public Rechnungsempfaenger rechnungsempfaenger;

}
