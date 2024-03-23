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
import libldt3.model.regel.kontext.K092;

/**
 * Mit diesem Objekt werden Organisationsstrukturen abgebildet.
 */
@Objekt("0043")
public class Organisation implements Kontext {

    @Objekt
    public static class OrganisationFirma implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "1251", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public String rechtsform;
        @Feld(value = "1252", feldart = Feldart.kann)
        @Regelsatz(maxLaenge = 60)
        public List<FunktionsbezeichnungPersonInnerhalbOrganisation> funktionsbezeichnungPersonInnerhalbOrganisation;
        @Feld(value = "8229", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 23)
        public List<Anschrift> anschriftArbeitsstelle;
        @Feld(value = "8230", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 18)
        public Anschrift rechnungsanschrift;
        @Feld(value = "8131", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 19)
        public Kommunikationsdaten kommunikationsdaten;
    }

    @Objekt(kontextregeln = K092.class)
    public static class FunktionsbezeichnungPersonInnerhalbOrganisation implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "8147", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 6)
        public List<Person> person;
    }

    @Feld(value = "1250", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public OrganisationFirma organisationFirma;

}
