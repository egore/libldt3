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
package libldt3.model.saetze;

import java.util.List;

import libldt3.annotations.Datenpaket;
import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Satzart;
import libldt3.model.objekte.Abrechnungsinformationen;
import libldt3.model.objekte.Anhang;
import libldt3.model.objekte.Auftragsinformation;
import libldt3.model.objekte.Einsenderidentifikation;
import libldt3.model.objekte.Fliesstext;
import libldt3.model.objekte.Koerperkenngroessen;
import libldt3.model.objekte.Material;
import libldt3.model.objekte.Mutterschaft;
import libldt3.model.objekte.Patient;
import libldt3.model.objekte.Schwangerschaft;
import libldt3.model.objekte.TierSonstiges;
import libldt3.model.objekte.Untersuchungsanforderung;
import libldt3.model.objekte.Veranlassungsgrund;
import libldt3.model.regel.kontext.K057;

/**
 * Satzart: Auftrag "8215"
 */
@Datenpaket(value = Satzart.Auftrag, kontextregeln = K057.class)
public class Auftrag implements Satz, Kontext {

    @Feld(value = "8122", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 23)
    public Einsenderidentifikation einsenderidentifikation;
    @Feld(value = "8145", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 7)
    public Patient patient;
    @Feld(value = "8169", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 19)
    public Koerperkenngroessen koerperkenngroessen;
    @Feld(value = "8150", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 15)
    public Schwangerschaft schwangerschaft;
    @Feld(value = "8140", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 12)
    public Mutterschaft mutterschaft;
    @Feld(value = "8153", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 14)
    public TierSonstiges tierSonstiges;
    @Feld(value = "8113", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 19)
    public Auftragsinformation auftragsinformation;
    @Feld(value = "8127", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 18)
    public List<Veranlassungsgrund> veranlassungsgrund;
    @Feld(value = "8101", feldart = Feldart.muss)
    @Regelsatz(laenge = 22)
    public Abrechnungsinformationen abrechnungsinformation;
    @Feld(value = "8137", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 8)
    public List<Material> material;
    @Feld(value = "8159", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 24)
    public List<Untersuchungsanforderung> untersuchungsanforderung;
    @Feld(value = "8167", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
