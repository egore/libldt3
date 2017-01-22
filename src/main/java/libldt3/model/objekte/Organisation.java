/*
 * Copyright 2016  Christoph Brill <egore911@gmail.com>
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
import lombok.Getter;
import lombok.Setter;

/**
 * Mit diesem Objekt werden Organisationsstrukturen abgebildet.
 */
@Objekt("0043")
public @Getter @Setter class Organisation {

	@Objekt
	public static @Getter @Setter class Funktionsbezeichnung {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8147", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 6))
		private List<Person> person;
	}

	@Feld(value = "1250", feldart = Feldart.muss, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String organisationFirma;
	@Feld(value = "1251", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String rechtsformOrganisation;
	@Feld(value = "1252", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private List<Funktionsbezeichnung> funktionsbezeichnung;
	@Feld(value = "8228", name = "Wohnanschrift", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 13))
	private List<Anschrift> wohnanschrift;
	@Feld(value = "8229", name = "Anschrift_Arbeitsstelle", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 23))
	private List<Anschrift> anschriftArbeitsstelle;
	@Feld(value = "8230", name = "Rechnungsanschrift", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 18))
	private List<Anschrift> rechnungsanschrift;
	@Feld(value = "8231", name = "Temporaere_Anschrift", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 20))
	private List<Anschrift> temporaereanschrift;
	@Feld(value = "8131", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 19))
	private Kommunikationsdaten kommunikationsdaten;

}
