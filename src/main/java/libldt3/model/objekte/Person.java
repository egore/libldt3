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
import lombok.Getter;
import lombok.Setter;

/**
 * Mit dem Objekt Person werden alle die natürlichen Personen dargestellt, deren
 * Daten für die Abwicklung, Abrechnung oder Dokumentation von Aufträgen und
 * Befun-den notwendig sind.
 */
@Objekt("0047")
public @Getter @Setter class Person {

	@Feld(value = "7420", feldart = Feldart.bedingt_muss)
	private StatusPerson status;
	@Feld(value = "3100", feldart = Feldart.kann)
	private String namenszusatz;
	@Feld(value = "3120", feldart = Feldart.kann)
	private String vorsatzwort;
	@Feld(value = "3101", feldart = Feldart.muss)
	private String nachname;
	@Feld(value = "3102", feldart = Feldart.muss)
	private List<String> vorname;
	@Feld(value = "3103", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(F003.class))
	private LocalDate geburtsdatum;
	@Feld(value = "3104", feldart = Feldart.kann)
	private String titel;
	@Feld(value = "3110", feldart = Feldart.bedingt_muss)
	private Geschlecht geschlecht;
	@Feld(value = "3628", feldart = Feldart.kann)
	private String muttersprache;
	@Feld(value = "8990", feldart = Feldart.kann)
	private String namenskuerzelNamenszeichen;
	@Feld(value = "8228", name = "Wohnanschrift", feldart = Feldart.bedingt_muss)
	private Anschrift wohnanschrift;
	@Feld(value = "8229", feldart = Feldart.kann)
	private Anschrift anschriftArbeitsstelle;
	@Feld(value = "8230", name = "Rechnungsanschrift", feldart = Feldart.kann)
	private Anschrift rechnungsanschrift;
	@Feld(value = "8232", name = "Private_Kommunikationsdaten", feldart = Feldart.kann)
	private Kommunikationsdaten privateKommunikationsdaten;
	@Feld(value = "8233", name = "Geschaeftliche_Kommunikationsdaten", feldart = Feldart.kann)
	private Kommunikationsdaten geschaeftlicheKommunikationsdaten;

}
