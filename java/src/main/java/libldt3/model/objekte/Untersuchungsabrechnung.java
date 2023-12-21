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
import libldt3.model.enums.Abrechnungsinfo;
import libldt3.model.enums.Gebuehrenordnung;
import libldt3.model.regel.erlaubt.E003;
import libldt3.model.regel.format.F009;
import libldt3.model.regel.kontext.K005;
import libldt3.model.regel.kontext.K008;
import libldt3.model.regel.kontext.K019;

/**
 * Jeder Untersuchung wird direkt eine Abrechnung zugeordnet. Hier werden alle
 * Werte transportiert, die für die ordnungsgemäße Abrechnung des Auftrages
 * notwendig sind.
 */
@Objekt(value = "0058", kontextregeln = {K005.class, K008.class, K019.class})
public class Untersuchungsabrechnung implements Kontext {

    @Objekt(kontextregeln = K019.class)
    public static class Untersuchungsabrechnung_Gebuehrenordnung implements Kontext {
        @SuppressWarnings("unused")
        public Gebuehrenordnung value;
        @Feld(value = "5001", feldart = Feldart.bedingt_muss)
        @Regelsatz(value = F009.class, minLaenge = 5, maxLaenge = 6)
        public List<Gebuehrennummer> gebuehrennummer;
    }

    @Objekt
    public static class Gebuehrennummer implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8406", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String kostenInEURCent;
        @Feld(value = "5005", feldart = Feldart.bedingt_kann)
        @Regelsatz(value = E003.class, laenge = 3)
        public String multiplikator;
        @Feld(value = "5009", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public List<String> freierBegruendungstext;
        @Feld(value = "8614", feldart = Feldart.muss)
        @Regelsatz(laenge = 1)
        public Boolean bereitsAbgerechnet;
    }

    @Objekt
    public static class KatalogAbrechenbareLeistungenId implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7251", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public String bezeichnungDesVerwendetenKataloges;
    }

    @Feld(value = "7303", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 2)
    public Abrechnungsinfo abrechnungsinfoZurUntersuchung;
    @Feld(value = "4121", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Untersuchungsabrechnung_Gebuehrenordnung gebuehrenordnung;
    @Feld(value = "7259", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public KatalogAbrechenbareLeistungenId katalogAbrechenbareLeistungenId;

}
