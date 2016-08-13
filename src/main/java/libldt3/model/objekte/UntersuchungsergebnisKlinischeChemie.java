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
import libldt3.model.enums.DarstellungSemiquantitativerErgebniswerte;
import libldt3.model.enums.EinheitMesswert;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.TestStatus;

/**
 * In diesem Objekt werden die Ergebnisse aus dem Bereich Klinische Chemie
 * übermittelt.
 */
@Objekt("0060")
public class UntersuchungsergebnisKlinischeChemie {

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
		@Feld(value = "8418", feldart = Feldart.bedingt_muss)
		private TestStatus teststatus;
	}

	@Objekt
	public static class Test {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8411", feldart = Feldart.bedingt_muss)
		private String testbezeichnung;
		@Feld(value = "7263", feldart = Feldart.kann)
		private String testId;
		@Feld(value = "7264", feldart = Feldart.kann)
		private String testGeraetUid;
		@Feld(value = "8418", feldart = Feldart.bedingt_muss)
		private TestStatus teststatus;
		@Feld(value = "7302", feldart = Feldart.kann)
		private List<String> testmethode;
	}

	@Objekt
	public static class ErgebnisWert {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8421", feldart = Feldart.bedingt_muss)
		private String einheit;
		@Feld(value = "8419", feldart = Feldart.bedingt_muss)
		private EinheitMesswert einheitNorm;
	}

	@Feld(value = "7304", feldart = Feldart.muss)
	private String ergebnisId;
	@Feld(value = "7364", feldart = Feldart.muss)
	private List<String> probengefäßIdent;
	@Feld(value = "7260", feldart = Feldart.bedingt_muss)
	private KatalogReferenz anforderbareLeistungenKatalogId;
	@Feld(value = "8410", feldart = Feldart.bedingt_muss)
	private Test testIdent;
	@Feld(value = "7282", feldart = Feldart.kann)
	private List<String> ermittelterParameter;
	@Feld(value = "7306", feldart = Feldart.kann)
	private DarstellungSemiquantitativerErgebniswerte darstellungSemiquantitativerErgebniswerte;
	@Feld(value = "8420", feldart = Feldart.bedingt_kann)
	private List<ErgebnisWert> ergebnisWert;
	@Feld(value = "8423", feldart = Feldart.kann)
	private String pathologischBekannt;
	@Feld(value = "8236", feldart = Feldart.bedingt_kann)
	private Fließtext testbezogeneHinweise;
	@Feld(value = "8237", name = "Ergebnistext", feldart = Feldart.kann)
	private Fließtext ergebnistext;
	@Feld(value = "8167", feldart = Feldart.kann)
	private List<Fließtext> zusaetzlicheInformationen;
	@Feld(value = "8220", name = "Timestamp_Eingangserfassung_Material", feldart = Feldart.kann)
	private Timestamp materialDeliveryTimestamp;
	@Feld(value = "8222", name = "Timestamp_Beginn_Analytik", feldart = Feldart.kann)
	private Timestamp startAnalyticsTimestamp;
	@Feld(value = "8223", name = "Timestamp_Ergebniserstellung", feldart = Feldart.kann)
	private Timestamp resultTimestamp;
	@Feld(value = "8224", name = "Timestamp_QM_Erfassung", feldart = Feldart.kann)
	private Timestamp qmTimestamp;
	@Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss)
	private Timestamp timestamp;
	@Feld(value = "8142", feldart = Feldart.kann)
	private List<Normalwert> normalValue;
	@Feld(value = "8141", feldart = Feldart.muss)
	private Namenskennung namenskennung;
	@Feld(value = "8158", feldart = Feldart.bedingt_muss)
	private Untersuchungsabrechnung untersuchungsabrechnung;
	@Feld(value = "8110", feldart = Feldart.kann)
	private List<Anhang> anhang;

}
