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
import libldt3.model.enums.Betriebsstaettenstatus;
import libldt3.model.regel.format.F010;
import libldt3.model.regel.format.F021;
import libldt3.model.regel.kontext.K020;
import libldt3.model.regel.kontext.K043;
import libldt3.model.regel.kontext.K044;

/**
 * Dieses Objekt fasst die notwendigen Informationen zur Betriebsstätte von
 * medizinischen Einrichtungen zusammen.
 */
@Objekt(value = "0019", kontextregeln = K043.class)
public class Betriebsstaette implements Kontext {

    @Objekt(kontextregeln = {K020.class, K044.class})
    public static class BsnrBezeichnung implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "0200", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String betriebsstaettenId;
        @Feld(value = "0201", feldart = Feldart.bedingt_muss)
        @Regelsatz(value = {F010.class, F021.class}, laenge = 9)
        public String betriebsoderNebenbetriebsstaettennummer;
        @Feld(value = "0213", feldart = Feldart.kann)
        @Regelsatz(laenge = 9)
        public String institutskennzeichen;
        @Feld(value = "8143", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public Organisation organisation;
    }

    @Feld(value = "0204", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public List<Betriebsstaettenstatus> statusBetriebsstaette;
    @Feld(value = "0203", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public BsnrBezeichnung bsnrBezeichnung;

}
