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

import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Einsenderstatus;

/**
 * Hier werden alle notwendigen Informationen zum Einsender zusammengefasst.
 */
@Objekt("0022")
public class Einsenderidentifikation implements Kontext {

    @Feld(value = "7321", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public List<Einsenderstatus> status;
    @Feld(value = "8312", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 20)
    public String kundenNummer;
    @Feld(value = "7267", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String auftraggeberId;
    @Feld(value = "8114", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 18)
    public Arztidentifikation arztidentifikation;
    @Feld(value = "8240", name = "Ueberweisung_von_anderen_Aerzten", feldart = Feldart.kann)
    @Regelsatz(laenge = 32)
    public Arztidentifikation ueberweisungVon;
    @Feld(value = "8241", name = "Ueberweisung_an", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 15)
    public Arztidentifikation ueberweisungAn;
    @Feld(value = "8147", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public Person person;
    @Feld(value = "7268", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String fachrichtung;
    @Feld(value = "8119", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 15)
    public Betriebsstaette permanentEstablishment;
    @Feld(value = "8143", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 12)
    public Organisation organisation;

}
