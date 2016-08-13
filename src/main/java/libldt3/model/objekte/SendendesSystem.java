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

/**
 * Dieses Objekt enthält die Information zum sendenden Softwaresystem, welches
 * den LDT Datensatz erstellt hat.
 */
@Objekt(value = "0051")
public class SendendesSystem {

	@Feld(value = "0001", feldart = Feldart.muss)
	private String version;
	@Feld(value = "8315", feldart = Feldart.kann)
	private String empfängerId;
	@Feld(value = "8316", feldart = Feldart.kann)
	private String senderId;
	@Feld(value = "0105", feldart = Feldart.bedingt_muss)
	private String kvbPrüfnummer;
	@Feld(value = "8212", feldart = Feldart.kann)
	private Organisation softwareverantwortlicher;
	@Feld(value = "0103", feldart = Feldart.muss)
	private String softwareName;
	@Feld(value = "0132", feldart = Feldart.bedingt_muss)
	private String softwareVersion;

}
