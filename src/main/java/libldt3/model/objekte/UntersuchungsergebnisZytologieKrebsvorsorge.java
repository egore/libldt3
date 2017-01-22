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
import libldt3.model.enums.EndozervikaleZellen;
import libldt3.model.enums.NachkontrollGrund;
import libldt3.model.enums.TestStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * In diesem Objekt werden die Ergebnisse aus dem Bereich Zytologie
 * Krebsvorsorge transportiert. Die Inhalte richten sich nach dem Muster 39b.
 */
@Objekt("0062")
public @Getter @Setter class UntersuchungsergebnisZytologieKrebsvorsorge {

	@Objekt
	public static @Getter @Setter class GrenzwertindikatorLaborwert {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8126", name = "Fehlermeldung_Aufmerksamkeit", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 28))
		private FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
	}

	@Objekt
	public static @Getter @Setter class TestIdent {
		@SuppressWarnings("unused")
		private String value;
		@Feld(value = "8411", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 60))
		private String testbezeichnung;
		@Feld(value = "8422", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 2))
		private List<GrenzwertindikatorLaborwert> grenzwertindikatorLaborwert;
		@Feld(value = "8237", name = "Ergebnistext", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 12))
		private Fliesstext ergebnistextVerweis;
	}

	@Feld(value = "7304", feldart = Feldart.muss, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String ergebnisId;
	@Feld(value = "7364", feldart = Feldart.muss, regelsaetze = @Regelsatz(maxLaenge = 60))
	private List<String> probengefaessIdent;
	@Feld(value = "8410", feldart = Feldart.muss, regelsaetze = @Regelsatz(maxLaenge = 60))
	private TestIdent testIdent;
	@Feld(value = "8418", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private TestStatus teststatus;
	@Feld(value = "7368", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean zellmaterialNichtVerwertbar;
	@Feld(value = "7405", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private EndozervikaleZellen endozervikaleZellen;
	@Feld(value = "7406", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 10))
	private String proliferationsgrad;
	@Feld(value = "7407", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean doederleinflora;
	@Feld(value = "7408", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean mischflora;
	@Feld(value = "7409", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean kokkenflora;
	@Feld(value = "7410", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean trichomonaden;
	@Feld(value = "7411", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean candida;
	@Feld(value = "7412", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean gardnerella;
	@Feld(value = "7414", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 5))
	private String gruppe;
	@Feld(value = "7413", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(maxLaenge = 4))
	private String codierungGruppe;
	@Feld(value = "7415", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean zytologischeKontrolle;
	@Feld(value = "7416", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private NachkontrollGrund nachkontrollGrund;
	@Feld(value = "7417", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private List<Boolean> histologischeKlaerung;
	@Feld(value = "8237", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 10 /* XXX 12 according to spec */))
	private Fliesstext ergebnistext;
	@Feld(value = "8134", name = "Krebsfrueherkennung_Frauen", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 26))
	private KrebsfrueherkennungFrauen krebsfrueherkennungFrauen;
	@Feld(value = "8126", name = "Fehlermeldung_Aufmerksamkeit", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 28))
	private FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
	@Feld(value = "8220", name = "Timestamp_Eingangserfassung_Material", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 36))
	private Timestamp timestampEingangserfassungMaterial;
	@Feld(value = "8222", name = "Timestamp_Beginn_Analytik", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 25))
	private Timestamp timestampBeginnAnalytik;
	@Feld(value = "8223", name = "Timestamp_Ergebniserstellung", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 28))
	private Timestamp timestampErgebniserstellung;
	@Feld(value = "8224", name = "Timestamp_QM_Erfassung", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 22))
	private Timestamp timestampQmErfassung;
	@Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 17))
	private Timestamp timestampMessung;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 26))
	private List<Fliesstext> zusaetzlicheInformationen;
	@Feld(value = "8110", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 6))
	private List<Anhang> anhang;
	@Feld(value = "8141", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 13))
	private Namenskennung namenskennung;
	@Feld(value = "8158", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 23))
	private Untersuchungsabrechnung untersuchungsabrechnung;

}
