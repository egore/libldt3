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
import libldt3.model.enums.Adresstyp;
import libldt3.model.regel.kontext.K017;

/**
 * Das Objekt Anschrift definiert die Adresse. Dabei kann es sich entweder um
 * ein Postfach oder um eine physische Adresse handeln.
 */
@Objekt(value = "0007", kontextregeln = K017.class)
public class Anschrift {

	@Feld(value = "3112", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 10)
	public String plz;
	@Feld(value = "3113", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 40)
	public String ort;
	@Feld(value = "3107", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 46)
	public String strasse;
	@Feld(value = "3109", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 9)
	public String hausnummer;
	@Feld(value = "3115", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 40)
	public String anschriftenzusatz;
	@Feld(value = "3114", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 3)
	public String wohnsitzlaendercode;

	@Feld(value = "3121", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 10)
	public String postfachPlz;
	@Feld(value = "3122", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 40)
	public String postfachOrt;
	@Feld(value = "3123", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 8)
	public String postfach;
	@Feld(value = "3124", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 3)
	public String postfachWohnsitzlaendercode;
	@Feld(value = "1202", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	public List<Adresstyp> adresstyp;

}
