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

import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Einsenderstatus;
import libldt3.model.regel.kontext.K016;
import libldt3.model.regel.kontext.K041;
import libldt3.model.regel.kontext.K045;
import libldt3.model.regel.kontext.K046;
import libldt3.model.regel.kontext.K047;
import libldt3.model.regel.kontext.K048;
import libldt3.model.regel.kontext.K107;

/**
 * Hier werden alle notwendigen Informationen zum Einsender zusammengefasst.
 */
@Objekt(value = "0022", kontextregeln = {K016.class, K041.class, K045.class, K046.class, K047.class, K048.class, K107.class})
public class Einsenderidentifikation implements Kontext {

    @Objekt
    public static class KundenNummer implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7267", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public String desAuftraggebersId;
    }

    @Feld(value = "7321", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public List<Einsenderstatus> statusEinsender;
    @Feld(value = "8312", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 20)
    public KundenNummer kundenNummer;
    @Feld(value = "8114", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 18)
    public Arztidentifikation arztidentifikation;
    @Feld(value = "8240", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 32)
    public Arztidentifikation ueberweisungVonAnderenAerzten;
    @Feld(value = "8241", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 15)
    public Fliesstext ueberweisungAn;
    @Feld(value = "8147", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public Person person;
    @Feld(value = "7268", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String fachrichtungOderStationskennung;
    @Feld(value = "8119", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 15)
    public Betriebsstaette betriebsstaette;
    @Feld(value = "8143", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 12)
    public Organisation organisation;

}
