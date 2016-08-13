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
import libldt3.model.enums.Befundtyp;
import libldt3.model.enums.ZusaetzlicherBefundweg;

/**
 * Dieses Objekt bündelt alle Daten zum Befund inklusive aller Kennungen, welche
 * eine eineindeutige Zuordnung von Auftrag und Befund sicherstellen.
 */
@Objekt("0017")
public class Befundinformationen {

	@Objekt
	public static class OrderNumber {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8313", feldart = Feldart.bedingt_kann)
		private List<String> nachforderungId;
		@Feld(value = "8214", name = "Timestamp_Auftragserteilung", feldart = Feldart.bedingt_kann)
		private Timestamp orderRequestTimestamp;
		@Feld(value = "8215", name = "Timestamp_Auftragseingang", feldart = Feldart.bedingt_kann)
		private Timestamp timestampAuftragseingang;
	}
	
	@Objekt
	public static class Befundweg {
		@SuppressWarnings("unused")
		private ZusaetzlicherBefundweg value;
		@Feld(value = "8147", feldart = Feldart.bedingt_muss)
		private Person person;
	}

	@Feld(value = "8310", feldart = Feldart.muss)
	private OrderNumber orderNumber;
	@Feld(value = "8311", feldart = Feldart.muss)
	private String orderId;
	@Feld(value = "7305", feldart = Feldart.bedingt_muss)
	private String findingId;
	@Feld(value = "8401", feldart = Feldart.bedingt_muss)
	private Befundtyp type;
	@Feld(value = "0080", feldart = Feldart.kann)
	private String fallakteId;
	@Feld(value = "0081", feldart = Feldart.bedingt_kann)
	private List<String> fallakteBezeichnung;
	@Feld(value = "7258", feldart = Feldart.kann)
	private String katalogId;
	@Feld(value = "7251", feldart = Feldart.bedingt_kann)
	private String katalogBezeichnung;
	@Feld(value = "4229", feldart = Feldart.kann)
	private List<String> ausnahmeindikation;
	@Feld(value = "7308", feldart = Feldart.bedingt_muss)
	private Integer anzahlLaborergebnisberichte;
	@Feld(value = "8118", name = "Abweichender_Befundweg", feldart = Feldart.kann)
	private Kommunikationsdaten abweichenderBefundweg;
	@Feld(value = "8611", feldart = Feldart.bedingt_kann)
	private List<Befundweg> zusaetzlicherBefundweg;
	@Feld(value = "7320", feldart = Feldart.kann)
	private Boolean recallEmpfohlen;
	@Feld(value = "8154", feldart = Feldart.bedingt_kann)
	private Timestamp recallEmpfohlenTimestamp;
	@Feld(value = "8216", name = "Timestamp_Befunderstellung", feldart = Feldart.muss)
	private Timestamp timestampBefunderstellung;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	private List<Fließtext> zusaetzlicheInformationen;
	@Feld(value = "8110", feldart = Feldart.kann)
	private List<Anhang> anhang;
	@Feld(value = "8126", name = "Fehlermeldung_Aufmerksamkeit", feldart = Feldart.bedingt_muss)
	private FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
	@Feld(value = "8141", feldart = Feldart.kann)
	private Namenskennung namenskennung;

}
