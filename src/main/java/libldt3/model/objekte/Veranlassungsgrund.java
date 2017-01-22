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
import libldt3.model.enums.SpezifizierungVeranlassungsgrund;
import libldt3.model.enums.Untersuchungsanlass;
import lombok.Getter;
import lombok.Setter;

/**
 * Mit diesem Objekt können Angaben zum Grund der Veranlassung der
 * laboratoriumsmedizinischen Untersuchung übertragen werden.
 */
@Objekt("0027")
public @Getter @Setter class Veranlassungsgrund {

	@Objekt
	public static @Getter @Setter class AbrechnungsinfoErweitert {
		@SuppressWarnings("unused")
		private Abrechnungsinfo value;
		@Feld(value = "8417", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 2))
		private Untersuchungsanlass anlass;
		@Feld(value = "8427", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(laenge = 2))
		private SpezifizierungVeranlassungsgrund spezifizierung;
		@Feld(value = "8217", name = "Praezisierung_Veranlassungsgrund", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(laenge = 32))
		private Fliesstext praezisierung;
		@Feld(value = "8200", name = "Akutdiagnose", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(laenge = 12))
		private List<Diagnose> akutDiagnose;
		@Feld(value = "4208", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
		private List<Medikation> vorbefundMedikation;
	}
	
	@Objekt
	public static @Getter @Setter class Medikation {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "6212", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(maxLaenge = 60))
		private List<Arzneimittelwirkstoff> arzneimittelwirkstoff;
	}
	
	@Objekt
	public static @Getter @Setter class Arzneimittelwirkstoff {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "6214", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 60))
		private String wirkstoffKlassifikation;
	}

	@Feld(value = "7303", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(minLaenge = 1, maxLaenge = 2))
	private List<AbrechnungsinfoErweitert> abrechnungsinfo;
	@Feld(value = "8110", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 6))
	private List<Anhang> anhang;

}
