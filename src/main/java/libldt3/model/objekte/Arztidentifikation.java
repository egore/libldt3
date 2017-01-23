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
import libldt3.model.enums.ArztTypId;
import libldt3.model.regel.F011;
import libldt3.model.regel.F014;
import libldt3.model.regel.F022;
import lombok.Getter;
import lombok.Setter;

/**
 * Hier werden alle notwendigen Informationen zum Einsender zusammengefasst.
 */
@Objekt("0014")
public @Getter @Setter class Arztidentifikation {

	@Objekt
	public static @Getter @Setter class ArztId {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "0308", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
		private ArztTypId arztTypId;
	}

	@Feld(value = "8147", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 6))
	private Person person;
	@Feld(value = "0212", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = {F011.class, F022.class}, laenge = 9))
	private List<String> lanr;
	@Feld(value = "0223", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = {F011.class, F022.class}, laenge = 9))
	private List<String> pseudoLanr;
	@Feld(value = "0306", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String vertragsId;
	@Feld(value = "0307", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private List<ArztId> arztIds;
	@Feld(value = "0222", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = F014.class, laenge = 9))
	private String asvTeamnummer;

}
