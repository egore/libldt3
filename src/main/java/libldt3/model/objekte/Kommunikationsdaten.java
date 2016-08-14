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
import lombok.Getter;
import lombok.Setter;

/**
 * Hier werden alle Informationen zusammengefasst, die eine Kommunikation bspw.
 * mit einer Einrichtung, Firma, Arzt, einem Patienten ermöglichen.
 */
@Objekt("0031")
public @Getter @Setter class Kommunikationsdaten {

	@Objekt
	public static @Getter @Setter class ElektronischePostadresse {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "7340", feldart = Feldart.bedingt_muss)
		private String spezifizierung;
	}

	@Feld(value = "7330", feldart = Feldart.bedingt_muss)
	private List<String> phone;
	@Feld(value = "7331", feldart = Feldart.bedingt_muss)
	private List<String> mobile;
	@Feld(value = "7332", feldart = Feldart.bedingt_muss)
	private List<ElektronischePostadresse> elektronischePostadresse;
	@Feld(value = "7333", feldart = Feldart.bedingt_muss)
	private List<String> fax;
	@Feld(value = "7335", feldart = Feldart.bedingt_muss)
	private List<String> email;
	@Feld(value = "7334", feldart = Feldart.bedingt_muss)
	private List<String> website;

}
