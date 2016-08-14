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
import libldt3.model.enums.ErgebnisStatus;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.Nachweisverfahren;
import libldt3.model.enums.ResistenzMethode;
import libldt3.model.enums.TestStatus;
import libldt3.model.enums.Wachstum;
import lombok.Getter;
import lombok.Setter;

/**
 * In diesem Objekt werden die Ergebnisse aus dem Bereich Mikrobiologie
 * transportiert. Um diese Daten strukturiert zu übertragen wird eine in der
 * Mikrobiologie übliche hierarchische Vorgehensweise definiert: Ausgangspunkt
 * ist immer das Material und die dazugehörige Anforderung. Aus diesen
 * Anforderungen erfolgt über verschiedene Nachweisverfahren eine
 * Stufendiagnostik zur Keimbestimmung, optional die Bestimmung der Breakpunkte
 * bzw. MHK´s (Minimale Hemm Konzentration) für einzelne Antibiotika. Die
 * Erregermenge wird als semiquantitatives Ergebnis abhängig des
 * Untersuchungsmaterials dargestellt.
 */
@Objekt("0061")
public @Getter @Setter class UntersuchungsergebnisMikrobiologie {

	@Objekt
	public static @Getter @Setter class Keim {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "7355", feldart = Feldart.bedingt_muss)
		private String keimName;
		@Feld(value = "7301", feldart = Feldart.bedingt_muss)
		private ErgebnisStatus ergebnisStatus;
		@Feld(value = "7357", feldart = Feldart.bedingt_kann)
		private Wachstum wachstum;
		@Feld(value = "7293", feldart = Feldart.bedingt_kann)
		private List<String> einheit;
		@Feld(value = "7356", feldart = Feldart.bedingt_kann)
		private String keimOid;
		@Feld(value = "7285", feldart = Feldart.bedingt_kann)
		private String keimNummer;
		@Feld(value = "7361", feldart = Feldart.bedingt_kann)
		private String katalogId;
		@Feld(value = "7251", feldart = Feldart.bedingt_muss)
		private String katalogBezeichnung;
		@Feld(value = "8236", name = "Testbezogene_Hinweise", feldart = Feldart.bedingt_kann)
		private Fliesstext testbezogeneHinweise;
	}

	@Objekt
	public static @Getter @Setter class KatalogReferenz {
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
	public static @Getter @Setter class NachweisverfahrenErweitert {
		@SuppressWarnings("unused")
		private Nachweisverfahren value;
		@Feld(value = "7302", feldart = Feldart.bedingt_muss)
		private String testmethode;
	}

	@Objekt
	public static @Getter @Setter class ResistenzMethodeErweitert {
		@SuppressWarnings("unused")
		private ResistenzMethode value;
		@Feld(value = "8111", feldart = Feldart.bedingt_muss)
		private Antibiogramm antibiogramm;
	}

	@Feld(value = "7304", feldart = Feldart.muss)
	private String ergebnisId;
	@Feld(value = "7364", feldart = Feldart.muss)
	private List<String> probengefäßIdent;
	@Feld(value = "7260", feldart = Feldart.bedingt_muss)
	private KatalogReferenz anforderbareLeistungenKatalogId;
	@Feld(value = "8410", feldart = Feldart.bedingt_muss)
	private String testIdent;
	@Feld(value = "8411", feldart = Feldart.bedingt_muss)
	private String testbezeichnung;
	@Feld(value = "8434", feldart = Feldart.bedingt_muss)
	private String anforderung;
	@Feld(value = "7281", feldart = Feldart.muss)
	private List<NachweisverfahrenErweitert> nachweisverfahren;
	@Feld(value = "8418", feldart = Feldart.muss)
	private TestStatus teststatus;
	@Feld(value = "7354", feldart = Feldart.bedingt_muss)
	private List<Keim> keime;
	@Feld(value = "7286", feldart = Feldart.muss)
	private List<ResistenzMethodeErweitert> resistenzMethode;
	@Feld(value = "8142", feldart = Feldart.kann)
	private List<Normalwert> normalValue;
	@Feld(value = "8237", name = "Ergebnistext", feldart = Feldart.kann)
	private Fliesstext ergebnistext;
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
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	private List<Fliesstext> zusätzlicheInformationen;
	@Feld(value = "8141", feldart = Feldart.muss)
	private Namenskennung namenskennung;
	@Feld(value = "8158", feldart = Feldart.bedingt_muss)
	private Untersuchungsabrechnung untersuchungsabrechnung;
	@Feld(value = "8110", feldart = Feldart.kann)
	private List<Anhang> anhang;

}
