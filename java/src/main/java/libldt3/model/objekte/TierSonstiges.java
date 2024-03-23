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
package libldt3.model.objekte;

import java.time.LocalDate;
import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.KastriertSterilisiert;
import libldt3.model.enums.TierGeschlecht;
import libldt3.model.enums.Zeiteinheit;
import libldt3.model.regel.format.F002;
import libldt3.model.regel.kontext.K089;
import libldt3.model.regel.kontext.K117;

/**
 * Enthält ein Auftrag Materialien, die nicht humanen Ursprungs sind, so werden die
 * entsprechenden Informationen zur Materialquelle in diesem Objekt beschrieben.
 */
@Objekt(value = "0053", kontextregeln = {K089.class, K117.class})
public class TierSonstiges implements Kontext {

    @Objekt
    public static class Alter implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7326", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public Zeiteinheit alterIn;
    }

    @Feld(value = "7319", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String identifikationsnummerQuelle;
    @Feld(value = "7313", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String artRasseMaterial;
    @Feld(value = "7314", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String nameKennung;
    @Feld(value = "7315", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 10)
    public Alter alter;
    @Feld(value = "7351", feldart = Feldart.kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate geburtsdatum;
    @Feld(value = "7428", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public TierGeschlecht geschlechtTiere;
    @Feld(value = "7432", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public KastriertSterilisiert kastriertSterilisiert;
    @Feld(value = "8107", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 9)
    public Anschrift anschrift;
    @Feld(value = "8147", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public Person person;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
