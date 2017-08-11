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
import libldt3.model.enums.Abrechnungsinfo;
import libldt3.model.enums.Gebuehrenordnung;
import libldt3.model.regel.F008;
import libldt3.model.regel.F009;
import libldt3.model.regel.kontext.K004;
import lombok.Getter;
import lombok.Setter;

/**
 * Jeder Untersuchung wird direkt eine Abrechnung zugeordnet. Hier werden alle
 * Werte transportiert, die für die ordnungsgemäße Abrechnung des Auftrages
 * notwendig sind.
 */
@Objekt(value = "0058", kontextregeln = K004.class)
public @Getter @Setter class Untersuchungsabrechnung {

	@Objekt
	public static @Getter @Setter class Gebuehrennummer {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8406", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
		private String kosten;
		@Feld(value = "5005", feldart = Feldart.bedingt_kann)
	@Regelsatz(laenge = 3)
		private Integer multiplikator;
		@Feld(value = "5009", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
		private List<String> begruendungstext;
		@Feld(value = "8614", feldart = Feldart.muss)
	@Regelsatz(laenge = 1)
		private Boolean abgerechnet;
	}

	@Feld(value = "7303", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 2)
	private Abrechnungsinfo abrechnungsinfo;
	@Feld(value = "4121", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	private Gebuehrenordnung gebuehrenordnung;
	@Feld(value = "5001", feldart = Feldart.bedingt_muss)
	@Regelsatz(value = {F008.class, F009.class}, maxLaenge = 9)
	private List<Gebuehrennummer> gebuehrennummer;
	@Feld(value = "7259", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private String abrechenbareLeistungenKatalogId;
	@Feld(value = "7251", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private String katalog;

}
