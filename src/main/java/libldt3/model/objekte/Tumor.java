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

import java.time.LocalDate;
import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import lombok.Getter;
import lombok.Setter;

/**
 * In diesem Objekt können Information zu einem Tumor sowohl für die
 * Beauftragung und für den Befund transportiert werden.
 */
@Objekt("0056")
public @Getter @Setter class Tumor {

	@Feld(value = "7364", feldart = Feldart.muss)
	private String probengefaessIdent;
	@Feld(value = "7372", feldart = Feldart.kann)
	private String tumorklassifikation;
	@Feld(value = "7373", feldart = Feldart.kann)
	private String grading;
	@Feld(value = "7374", feldart = Feldart.kann)
	private String stadium;
	@Feld(value = "7375", feldart = Feldart.kann)
	private String jahrTumordiagnose;
	@Feld(value = "7376", feldart = Feldart.muss)
	private String lokalisationTumor;
	@Feld(value = "7377", feldart = Feldart.kann)
	private List<String> masse;
	@Feld(value = "7378", feldart = Feldart.kann)
	private String farbe;
	@Feld(value = "7379", feldart = Feldart.kann)
	private String infiltrationstiefe;
	@Feld(value = "3424", feldart = Feldart.kann)
	private LocalDate therapiebeginn;
	@Feld(value = "3425", feldart = Feldart.kann)
	private LocalDate therapieende;
	@Feld(value = "8220", feldart = Feldart.kann)
	private Timestamp timestampEingangserfassungMaterial;
	@Feld(value = "8222", feldart = Feldart.kann)
	private Timestamp timestampBeginnAnalytik;
	@Feld(value = "8223", feldart = Feldart.kann)
	private Timestamp timestampErgebniserstellung;
	@Feld(value = "8224", feldart = Feldart.kann)
	private Timestamp timestampQmErfassung;
	@Feld(value = "8225", feldart = Feldart.kann)
	private Timestamp timestampMessung;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	private List<Fliesstext> zusaetzlicheInformationen;
	@Feld(value = "8110", feldart = Feldart.kann)
	private List<Anhang> anhang;

}
