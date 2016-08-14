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

/**
 * Im Objekt werden die Untersuchungsergebnisse zusammengefasst.
 */
@Objekt("0035")
public class Laborergebnisbericht {

	@Feld(value = "8160", name = "UE_Klinische_Chemie", feldart = Feldart.bedingt_muss)
	private List<UntersuchungsergebnisKlinischeChemie> klinischeChemie;
	@Feld(value = "8161", name = "UE_Mikrobiologie", feldart = Feldart.bedingt_muss)
	private List<UntersuchungsergebnisMikrobiologie> mikrobiologie;
	@Feld(value = "8162", name = "UE_Zytologie_Krebsvorsorge", feldart = Feldart.bedingt_muss)
	private List<UntersuchungsergebnisZytologieKrebsvorsorge> zytologieKrebsvorsorge;
	@Feld(value = "8163", name = "UE_Zytologie", feldart = Feldart.bedingt_muss)
	private List<UntersuchungsergebnisZytologie> zytologie;
	@Feld(value = "8155", name = "UE_Transfusionsmedizin/Mutterschaftsvorsorge", feldart = Feldart.bedingt_muss)
	private List<TransfusionsmedizinMutterschaftsvorsorge> transfusionsmedizinMutterschaftsvorsorge;
	@Feld(value = "8156", feldart = Feldart.kann)
	private List<Tumor> tumor;
	@Feld(value = "8221", name = "Timestamp_Erstellung_Laborergebnisbericht", feldart = Feldart.muss)
	private Timestamp timestampErstellungLaborergebnisbericht;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	private List<Fliesstext> text;
	@Feld(value = "8110", feldart = Feldart.kann)
	private List<Anhang> anhang;
	@Feld(value = "8141", feldart = Feldart.kann)
	private Namenskennung namenskennung;

}
