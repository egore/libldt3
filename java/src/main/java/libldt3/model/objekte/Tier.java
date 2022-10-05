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
package libldt3.model.objekte;

import java.time.LocalDate;
import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Zeiteinheit;
import libldt3.model.regel.F002;

/**
 * Enthält ein Auftrag Materialien die nicht Humanen Ursprungs sind, so werden
 * die entsprechenden Informationen zur Materialquelle in diesem Objekt
 * beschrieben.
 */
@Objekt("0053")
public class Tier implements Kontext {

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
    public String alter;
    @Feld(value = "7326", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Zeiteinheit alterIn;
    @Feld(value = "7351", feldart = Feldart.kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate geburtsdatum;
    @Feld(value = "8107", feldart = Feldart.kann)
    @Regelsatz(laenge = 9)
    public Anschrift anschrift;
    @Feld(value = "8147", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public Person person;
    @Feld(value = "8110", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
