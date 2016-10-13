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

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.enums.Privattarif;
import libldt3.model.enums.StatusRechnungsempfaenger;
import lombok.Getter;
import lombok.Setter;

/**
 * Hier sind alle Angaben zum Rechnungsempfänger enthalten.
 */
@Objekt(value = "0048", name = "RgEmpfaenger")
public @Getter @Setter class Rechnungsempfaenger {

	@Feld(value = "8310", feldart = Feldart.muss, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String auftragsnummerEinsender;
	@Feld(value = "7421", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 2))
	private StatusRechnungsempfaenger statusRechnungsempfaenger;
	@Feld(value = "0600", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String nameEinrichtungAuftraggeber;
	@Feld(value = "7328", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(maxLaenge = 10))
	private String zusaetzlicheNamenszeile;
	@Feld(value = "8108", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 8))
	private Adressat adressat;
	@Feld(value = "8610", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 1))
	private Privattarif privattarif;
	@Feld(value = "8608", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String kommentarAktenzeichen;

}
