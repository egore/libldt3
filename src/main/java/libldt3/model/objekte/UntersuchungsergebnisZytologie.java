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
import libldt3.model.enums.Ergebnis;
import libldt3.model.enums.Ergebnis2;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.TestStatus;

/**
 * In diesem Objekt können weitere Ergebnisse aus dem Bereich Zytologie transportiert werden.
 */
@Objekt("0063")
public class UntersuchungsergebnisZytologie {

	@Objekt
	public static class RecallEmpfohlen {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8154", feldart = Feldart.bedingt_kann)
	    private Timestamp timestamp;
	}

	@Objekt
	public static class KatalogReferenz {
		@SuppressWarnings("unused")
		private KatalogIdAnforderbareLeistungen value;
		@Feld(value = "7352", feldart = Feldart.bedingt_muss)
		private String katalogUrl;
		@Feld(value = "7251", feldart = Feldart.bedingt_kann)
		private String katalogBezeichnung;
		@Feld(value = "7365", feldart = Feldart.bedingt_muss)
		private String leistungskuerzel;
		@Feld(value = "7366", feldart = Feldart.bedingt_kann)
		private String leistungsbezeichnung;
	}
	
	@Objekt
	public static class GrenzwertindikatorLaborwert {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8126", feldart = Feldart.bedingt_muss)
		private FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
	}

	@Objekt
	public static class Test {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8411", feldart = Feldart.bedingt_muss)
		private String testbezeichnung;
		@Feld(value = "8418", feldart = Feldart.bedingt_muss)
		private TestStatus teststatus;
		@Feld(value = "8422", feldart = Feldart.bedingt_muss)
		private List<GrenzwertindikatorLaborwert> grenzwertindikatorLaborwert;
	}

	@Feld(value = "7304", feldart = Feldart.muss)
	private String ergebnisId;
	@Feld(value = "7320", feldart = Feldart.bedingt_kann)
	private List<RecallEmpfohlen> recallEmpfohlen;
	@Feld(value = "7364", feldart = Feldart.muss)
	private List<String> probengefaessIdent;
	@Feld(value = "7260", feldart = Feldart.bedingt_muss)
	private KatalogReferenz katalogIdAnforderbareLeistungen;
	@Feld(value = "8410", feldart = Feldart.muss)
	private List<Test> testIdent;
	@Feld(value = "8237", feldart = Feldart.bedingt_muss)
	private Fließtext Ergebnistext;
	@Feld(value = "7368", feldart = Feldart.kann)
	private Boolean zellmaterialNichtVerwertbar;
	@Feld(value = "7400", feldart = Feldart.kann)
	private String hpvBefund;
	@Feld(value = "7401", feldart = Feldart.bedingt_muss)
	private Ergebnis highRisk;
	@Feld(value = "7402", feldart = Feldart.bedingt_muss)
	private List<String> highRiskTyp;
	@Feld(value = "7403", feldart = Feldart.bedingt_muss)
	private Ergebnis lowRisk;
	@Feld(value = "7404", feldart = Feldart.bedingt_muss)
	private List<String> lowRiskTyp;
	@Feld(value = "7418", feldart = Feldart.kann)
	private Ergebnis p16Ki67;
	@Feld(value = "7419", feldart = Feldart.kann)
	private Ergebnis l1;
	@Feld(value = "7422", feldart = Feldart.kann)
	private Ergebnis chlamydien;
	@Feld(value = "7425", feldart = Feldart.kann)
	private Ergebnis2 extragynäkologischeZytologie;
	@Feld(value = "7426", feldart = Feldart.kann)
	private Ergebnis neisseriaGonorrhoeae;
	@Feld(value = "8126", feldart = Feldart.kann)
	private FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
	@Feld(value = "8220", name = "Timestamp_Eingangserfassung_Material", feldart = Feldart.kann)
	private Timestamp timestampEingangserfassungMaterial;
	@Feld(value = "8222", name = "Timestamp_Beginn_Analytik", feldart = Feldart.kann)
	private Timestamp timestampBeginnAnalytik;
	@Feld(value = "8223", name = "Timestamp_Ergebniserstellung", feldart = Feldart.kann)
	private Timestamp timestampErgebniserstellung;
	@Feld(value = "8224", name = "Timestamp_QM_Erfassung", feldart = Feldart.kann)
	private Timestamp timestampQmErfassung;
	@Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss)
	private Timestamp timestampMessung;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	private List<Fließtext> zusaetzlicheInformationen;
	@Feld(value = "8110", feldart = Feldart.kann)
	private List<Anhang> anhang;
	@Feld(value = "8141", feldart = Feldart.muss)
	private Namenskennung namenskennung;
	@Feld(value = "8158", feldart = Feldart.bedingt_muss)
	private Untersuchungsabrechnung untersuchungsabrechnung;

}
