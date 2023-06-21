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
import libldt3.model.enums.Beauftragungsgrund;
import libldt3.model.enums.Bestaetigungsdiagnostik;
import libldt3.model.enums.BetroffeneEinrichtung;
import libldt3.model.enums.TestungRechtsgrundlage;
import libldt3.model.enums.Virusvariantendiagnostik;
import libldt3.model.regel.format.F002;
import libldt3.model.regel.kontext.K130;
import libldt3.model.regel.kontext.K131;
import libldt3.model.regel.kontext.K132;
import libldt3.model.regel.kontext.K135;

/**
 * In diesem Objekt werden die Informationen des Musters OEGD abgebildet.
 */
@Objekt(value = "0009", kontextregeln = {K130.class, K131.class, K132.class, K135.class})
public class AbrechnungOEGD implements Kontext {

    @Feld(value = "4110", feldart = Feldart.kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate versicherungsschutzEnde;
    @Feld(value = "8626", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public TestungRechtsgrundlage rechtsgrundlageTestung;
    @Feld(value = "8627", feldart = Feldart.bedingt_muss)
    @Regelsatz(minLaenge = 1, maxLaenge = 5)
    public String kvSonderziffer;
    @Feld(value = "8617", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Beauftragungsgrund beauftragungsgrund;
    @Feld(value = "4111", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 9)
    public String kostentraegerkennung;
    @Feld(value = "8631", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Bestaetigungsdiagnostik bestaetigungsdiagnostik;
    @Feld(value = "8632", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public Virusvariantendiagnostik virusvariantendiagnostik;
    @Feld(value = "8618", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public Boolean betreutUntergebrachtIn;
    @Feld(value = "8619", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public Boolean taetigkeitInEinrichtung;
    @Feld(value = "8620", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public BetroffeneEinrichtung betroffeneEinrichtung;
    @Feld(value = "8621", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean einverstaendnis;
    @Feld(value = "8622", feldart = Feldart.kann)
    @Regelsatz(laenge = 43)
    public String coronaGuid;
    @Feld(value = "8625", feldart = Feldart.kann)
    @Regelsatz(laenge = 5)
    public String plzOeGd;
    @Feld(value = "8623", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 22)
    public String identifikationAktenzeichenOeGd;

}
