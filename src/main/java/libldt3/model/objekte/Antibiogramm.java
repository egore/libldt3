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
import libldt3.model.enums.ResistenzInterpretation;
import libldt3.model.enums.ResistenzNach;
import libldt3.model.enums.Sensitivitaet;
import lombok.Getter;
import lombok.Setter;

/**
 * In diesem Objekt wird ein Antibiogramm (Matrix) aus dem Bereich Mikrobiologie
 * transportiert. Die Darstellung des Antibiogramms erfolgt als mehrdimensionale
 * Matrix.
 */
@Objekt("0011")
public @Getter @Setter class Antibiogramm {

	@Objekt
	public static @Getter @Setter class WirkstoffIdent {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "7288", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
		private List<String> wirkstoffGenericNummer;
		@Feld(value = "7359", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
		private List<String> wirkstoffOid;
		@Feld(value = "7370", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
		private List<String> wirkstoffname;
		@Feld(value = "7354", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
		private List<KeimIdentifizierung> keimIdentifizierung;
	}

	@Objekt
	public static @Getter @Setter class KeimIdentifizierung {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "7367", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
		private Sensitivitaet sensitivitaet;
		@Feld(value = "7289", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
		private String mhk;
		@Feld(value = "7369", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
		private String mhkEinheit;
		@Feld(value = "7290", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
		private List<ResistenzInterpretationErweitert> resistenzInterpretation;
	}
	
	@Objekt
	public static @Getter @Setter class ResistenzInterpretationErweitert {
		@SuppressWarnings("unused")
		private ResistenzInterpretation value;
		@Feld(value = "7424", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
		private ResistenzNach resistenzNach;
	}

	@Feld(value = "7287", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 60)
	private List<WirkstoffIdent> wirkstoffIdent;
	@Feld(value = "8237", name = "Ergebnistext", feldart = Feldart.bedingt_kann)
	@Regelsatz(laenge = 12)
	private Fliesstext ergebnistext;

}
