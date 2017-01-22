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
import libldt3.model.enums.AbrechnungsartPkv;
import libldt3.model.enums.Gebuehrenordnung;
import lombok.Getter;
import lombok.Setter;

/**
 * Mit diesem Objekt werden die Informationen für die Abrechnung von
 * Untersuchungsanforderungen zusammengefasst, die über die GOÄ abgerechnet
 * werden. Das Objekt bezieht sich auf Patienten, welche bei privaten
 * Krankenkassen versichert sind. Dabei kann der Rechnungsempfänger aber auch
 * ein anderer sein, als der Versicherte.
 */
@Objekt("0003")
public @Getter @Setter class AbrechnungPkv {

	@Feld(value = "7362", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 1))
	private AbrechnungsartPkv abrechnungsartPkv;
	@Feld(value = "4134", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 28))
	private List<String> kostentraegerName;
	@Feld(value = "4121", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 1))
	private Gebuehrenordnung gebuehrenordnung;
	@Feld(value = "4202", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean unfallFolgen;
	@Feld(value = "8148", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 12))
	private Rechnungsempfaenger rechnungsempfaenger;

}
