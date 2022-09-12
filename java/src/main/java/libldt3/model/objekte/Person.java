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

import java.time.LocalDate;
import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.enums.Geschlecht;
import libldt3.model.enums.StatusPerson;
import libldt3.model.regel.F003;

/**
 * Mit dem Objekt Person werden alle die natürlichen Personen dargestellt, deren
 * Daten für die Abwicklung, Abrechnung oder Dokumentation von Aufträgen und
 * Befun-den notwendig sind.
 */
@Objekt("0047")
public class Person {

	@Feld(value = "7420", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 2)
	public StatusPerson status;
	@Feld(value = "3100", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 20)
	public String namenszusatz;
	@Feld(value = "3120", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 20)
	public String vorsatzwort;
	@Feld(value = "3101", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 45)
	public String nachname;
	@Feld(value = "3102", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 45)
	public List<String> vorname;
	@Feld(value = "3103", feldart = Feldart.bedingt_muss)
	@Regelsatz(value = F003.class, laenge = 8)
	public LocalDate geburtsdatum;
	@Feld(value = "3104", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 20)
	public String titel;
	@Feld(value = "3110", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public Geschlecht geschlecht;
	@Feld(value = "3628", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public String muttersprache;
	@Feld(value = "8990", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public String namenskuerzelNamenszeichen;
	@Feld(value = "8228", name = "Wohnanschrift", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 13)
	public Anschrift wohnanschrift;
	@Feld(value = "8229", name = "Anschrift_Arbeitsstelle", feldart = Feldart.kann)
	@Regelsatz(laenge = 23)
	public Anschrift anschriftArbeitsstelle;
	@Feld(value = "8230", name = "Rechnungsanschrift", feldart = Feldart.kann)
	@Regelsatz(laenge = 18)
	public Anschrift rechnungsanschrift;
	@Feld(value = "8232", name = "Private_Kommunikationsdaten", feldart = Feldart.kann)
	@Regelsatz(laenge = 27)
	public Kommunikationsdaten privateKommunikationsdaten;
	@Feld(value = "8233", name = "Geschaeftliche_Kommunikationsdaten", feldart = Feldart.kann)
	@Regelsatz(laenge = 34)
	public Kommunikationsdaten geschaeftlicheKommunikationsdaten;

}
