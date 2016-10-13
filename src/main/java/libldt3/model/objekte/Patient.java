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
import libldt3.model.enums.GeschlechtNormbereich;
import libldt3.model.regel.F002;
import libldt3.model.regel.F013;
import lombok.Getter;
import lombok.Setter;

/**
 * In diesem Objekt werden die Informationen über einen Patienten aufgeführt.
 */
@Objekt("0045")
public @Getter @Setter class Patient {

	@Feld(value = "8147", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 6))
	private Person person;
	@Feld(value = "3119", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = F013.class, laenge = 10))
	private String versichertenId;
	@Feld(value = "3105", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(minLaenge = 6, maxLaenge = 12))
	private String versichertennummer;
	@Feld(value = "7329", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 1))
	private GeschlechtNormbereich geschlecht;
	@Feld(value = "7922", feldart = Feldart.kann, regelsaetze = @Regelsatz(value = F002.class, laenge = 8))
	private LocalDate sterbedatum;
	@Feld(value = "3000", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String patientNumber;
	@Feld(value = "3620", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private List<String> profession;
	@Feld(value = "3621", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String currentProfession;

}
