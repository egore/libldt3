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
import libldt3.model.enums.Abrechnungsinfo;
import libldt3.model.enums.Dringlichkeit;
import libldt3.model.enums.KatalogIdAnforderbareLeistungen;
import libldt3.model.enums.StatusDringlichkeit;
import lombok.Getter;
import lombok.Setter;

/**
 * In diesem Objekt werden alle Informationen zur Untersuchungsanforderung zusammengefasst.
 */
@Objekt(value = "0059")
public @Getter @Setter class Untersuchungsanforderung {

	@Objekt
	public static @Getter @Setter class KatalogReferenz {
		@SuppressWarnings("unused")
		private KatalogIdAnforderbareLeistungen value;
		@Feld(value = "7352", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
		private String katalogUrl;
		@Feld(value = "7251", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
		private String katalogBezeichnung;
		@Feld(value = "7365", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 20)
		private String analysenId;
		@Feld(value = "7366", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
		private String leistungsbezeichnung;
	}

	@Objekt
	public static @Getter @Setter class Test {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8411", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
		private String testbezeichnung;
	}

	@Objekt
	public static @Getter @Setter class ProbengefaessIdent {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8428", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
		private String probenmaterialIdent;
		@Feld(value = "8429", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 4)
		private String probenmaterialIndex;
	}

	@Objekt
	public static @Getter @Setter class Einwilligungserklaerung {
		@SuppressWarnings("unused")
		private Boolean value;
		@Feld(value = "8110", feldart = Feldart.bedingt_kann)
	@Regelsatz(laenge = 6)
		private Anhang anhang;
	}

	@Feld(value = "7260", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	private KatalogReferenz anforderbareLeistungenKatalogId;
	@Feld(value = "7276", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private String nummernpoolId;
	@Feld(value = "8410", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
	private Test testIdent;
	@Feld(value = "7303", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 2)
	private Abrechnungsinfo abrechnungsinfo;
	@Feld(value = "8501", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	private Dringlichkeit dringlichkeit;
	@Feld(value = "7262", feldart = Feldart.bedingt_kann)
	@Regelsatz(laenge = 1)
	private StatusDringlichkeit statusDringlichkeit;
	@Feld(value = "8423", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	private Boolean pathologischBekannt;
	@Feld(value = "7364", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 60)
	private List<ProbengefaessIdent> probengefaessIdent;
	@Feld(value = "8434", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
	private String anforderungen;
	@Feld(value = "8134", name = "Krebsfrueherkennung_Frauen", feldart = Feldart.kann)
	@Regelsatz(laenge = 26)
	private KrebsfrueherkennungFrauen krebsfrueherkennungFrauen;
	@Feld(value = "8156", feldart = Feldart.kann)
	@Regelsatz(laenge = 5)
	private Tumor tumor;
	@Feld(value = "8110", feldart = Feldart.kann)
	@Regelsatz(laenge = 6)
	private List<Anhang> anhang;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	@Regelsatz(laenge = 26)
	private List<Fliesstext> zusaetzlicheInformationen;
	@Feld(value = "8238", name = "Auftragsbezogene_Hinweise", feldart = Feldart.kann)
	@Regelsatz(laenge = 25)
	private Fliesstext auftragsbezogeneHinweise;
	@Feld(value = "8491", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	private Einwilligungserklaerung einwilligungserklaerungLiegtVor;
	@Feld(value = "8213", name = "Timestamp_Erstellung_Untersuchungsanforderung", feldart = Feldart.muss)
	@Regelsatz(laenge = 45)
	private Timestamp timestampErstellungUntersuchungsanforderung;
	@Feld(value = "8141", feldart = Feldart.kann)
	@Regelsatz(laenge = 13)
	private Namenskennung namenskennung;

}
