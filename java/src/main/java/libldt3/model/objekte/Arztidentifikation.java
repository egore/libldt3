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
import libldt3.model.enums.ArztTypId;
import libldt3.model.regel.format.F011;
import libldt3.model.regel.format.F014;
import libldt3.model.regel.format.F022;
import libldt3.model.regel.kontext.K020;
import libldt3.model.regel.kontext.K057;
import libldt3.model.regel.kontext.K115;
import libldt3.model.regel.kontext.K116;

/**
 * Hier werden alle notwendigen Informationen zum Einsender zusammengefasst.
 */
@Objekt(value = "0014", kontextregeln = {K020.class, K057.class, K115.class, K116.class})
public class Arztidentifikation implements Kontext {

    @Objekt
    public static class ArztIdEinesArztes implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "0308", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public List<ArztTypId> typArztId;
    }

    @Feld(value = "8147", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public Person person;
    @Feld(value = "0212", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F011.class, laenge = 9)
    public List<String> lebenslangeArztnummer;
    @Feld(value = "0223", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F022.class, laenge = 9)
    public List<String> pseudoLanrFuerKrankenhausaerzteRahmenAsvAbrechnung;
    @Feld(value = "0306", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String vertragsIdDesBehandelndenArztes;
    @Feld(value = "0307", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public List<ArztIdEinesArztes> arztIdEinesArztes;
    @Feld(value = "0222", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F014.class, laenge = 9)
    public String asvTeamnummer;

}
