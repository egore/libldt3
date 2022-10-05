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
import libldt3.model.enums.Dringlichkeit;
import libldt3.model.enums.StatusDringlichkeit;
import libldt3.model.enums.ZusaetzlicherBefundweg;

/**
 * In diesem Objekt werden die Informationen zur Zuordnung im sendenden System
 * zum Auftrag zusammengefasst sowie zusätzliche Befundwege und die
 * Dringlichkeit des Auftrags definiert.
 */
@Objekt(value = "0013")
public class Auftragsinformation implements Kontext {

    @Objekt
    public static class Befundweg implements Kontext {
        @SuppressWarnings("unused")
        public ZusaetzlicherBefundweg value;
        @Feld(value = "8147", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 6)
        public Person person;
    }

    @Feld(value = "8310", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String auftragsnummerEinsender;
    @Feld(value = "8313", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public List<String> nachforderungId;
    @Feld(value = "8311", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String auftragsnummerLabor;
    @Feld(value = "7268", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String fachrichtungStationskennung;
    @Feld(value = "0080", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String fallakteId;
    @Feld(value = "0081", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public List<String> fallakteBezeichnung;
    @Feld(value = "8501", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public Dringlichkeit dringlichkeit;
    @Feld(value = "7262", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public StatusDringlichkeit statusDringlichkeit;
    @Feld(value = "8118", name = "Abweichender_Befundweg", feldart = Feldart.kann)
    @Regelsatz(laenge = 22)
    public Kommunikationsdaten abweichenderBefundweg;
    @Feld(value = "8611", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public List<Befundweg> zusaetzlicherBefundweg;
    @Feld(value = "8213", name = "Timestamp_Erstellung_Untersuchungsanforderung", feldart = Feldart.muss)
    @Regelsatz(laenge = 45)
    public Timestamp timestampErstellungUntersuchungsanforderung;
    @Feld(value = "8238", name = "Auftragsbezogene_Hinweise", feldart = Feldart.kann)
    @Regelsatz(laenge = 25)
    public Fliesstext auftragsbezogeneHinweise;
    @Feld(value = "8141", feldart = Feldart.kann)
    @Regelsatz(laenge = 13)
    public Namenskennung namenskennung;

}
