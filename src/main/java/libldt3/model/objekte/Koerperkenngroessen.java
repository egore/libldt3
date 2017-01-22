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
import lombok.Getter;
import lombok.Setter;

/**
 * In diesem Objekt können Körperkenngrößen zum Patienten (Größe, Gewicht) übertragen werden.
 */
@Objekt("0069")
public @Getter @Setter class Koerperkenngroessen {

	@Objekt
	public static @Getter @Setter class Messwert {
		@SuppressWarnings("unused")
		private Float value;
		@Feld(value = "8421", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 20))
		private String einheit;
		@Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 17))
		private Timestamp timestamp;
	}
	
	@Feld(value = "3622", feldart = Feldart.kann)
	private Messwert groesse;
	@Feld(value = "3623", feldart = Feldart.kann)
	private Messwert gewicht;

}
