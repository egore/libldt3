/*
 * Copyright 2016-2023  Christoph Brill &lt;opensource@christophbrill.de&gt;
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
import libldt3.model.enums.DarstellungErgebniswerte;
import libldt3.model.enums.EinheitMesswert;
import libldt3.model.regel.kontext.K002;
import libldt3.model.regel.kontext.K054;
import libldt3.model.regel.kontext.K100;

/**
 * In diesem Objekt werden die Ergebnisse bakteriologischer Untersuchungen
 * strukturiert abgebildet.
 */
@Objekt(value = "0072", kontextregeln = K100.class)
public class BAK implements Kontext {

    @Objekt
    public static class BAK_DarstellungErgebniswerte implements Kontext {
        @SuppressWarnings("unused")
        public DarstellungErgebniswerte value;
        @Feld(value = "8420", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public List<ErgebnisWert> ergebnisWert;
    }

    @Objekt(kontextregeln = {K002.class, K054.class, K100.class})
    public static class ErgebnisWert implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8419", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public EinheitensystemMesswerteWertes einheitensystemMesswerteWertes;
        @Feld(value = "8142", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 10)
        public List<Normalwert> normalwert;
        @Feld(value = "8237", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 12)
        public Fliesstext ergebnistext;
    }

    @Objekt(kontextregeln = K002.class)
    public static class EinheitensystemMesswerteWertes implements Kontext {
        @SuppressWarnings("unused")
        public EinheitMesswert value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String masseinheitMesswerteWertes;
    }

    @Feld(value = "8245", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 12)
    public Fliesstext bakErgebnis;
    @Feld(value = "7306", feldart = Feldart.kann)
    @Regelsatz(laenge = 2)
    public List<BAK_DarstellungErgebniswerte> darstellungErgebniswerte;
    @Feld(value = "8246", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 29)
    public Fliesstext bakErgebniswertbezogeneHinweise;

}
