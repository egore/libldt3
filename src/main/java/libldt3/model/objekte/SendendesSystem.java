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
import libldt3.model.regel.F007;
import libldt3.model.regel.F012;
import lombok.Getter;
import lombok.Setter;

/**
 * Dieses Objekt enthält die Information zum sendenden Softwaresystem, welches
 * den LDT Datensatz erstellt hat.
 */
@Objekt(value = "0051")
public @Getter @Setter class SendendesSystem {

	@Feld(value = "0001", feldart = Feldart.muss, regelsaetze = @Regelsatz(value = F007.class, maxLaenge = 12))
	private String version;
	@Feld(value = "8315", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String empfaengerId;
	@Feld(value = "8316", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String senderId;
	@Feld(value = "0105", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = F012.class, laenge = 16))
	private String kvbPruefnummer;
	@Feld(value = "8212", name = "Softwareverantwortlicher", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 24))
	private Organisation softwareverantwortlicher;
	@Feld(value = "0103", feldart = Feldart.muss, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String softwareName;
	@Feld(value = "0132", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String softwareVersion;

}
