/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
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
import libldt3.model.enums.GrenzwertindikatorErweitert;
import libldt3.model.enums.NachkontrollGrund;
import libldt3.model.enums.TestStatus;
import libldt3.model.regel.kontext.K076;
import libldt3.model.regel.kontext.K099;

/**
 * In diesem Objekt werden die Ergebnisse aus dem Bereich Zytologie
 * Krebsvorsorge transportiert. Die Inhalte richten sich nach dem Muster 39b.
 */
@Objekt(value = "0062", kontextregeln = K076.class)
public class UntersuchungsergebnisZytologieKrebsvorsorge {

	@Objekt(kontextregeln = K099.class)
	public static class TestIdent {
		@SuppressWarnings("unused")
		public String value;
		@Feld(value = "8411", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
		public String testbezeichnung;
		@Feld(value = "8422", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 2)
		public List<GrenzwertindikatorErweitert> grenzwertindikator;
		@Feld(value = "8237", name = "Ergebnistext", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 12)
		public Fliesstext ergebnistextVerweis;
	}

	@Feld(value = "7304", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 60)
	public String ergebnisId;
	@Feld(value = "7364", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 60)
	public List<String> probengefaessIdent;
	@Feld(value = "8410", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 60)
	public TestIdent testIdent;
	@Feld(value = "8418", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public TestStatus teststatus;
	@Feld(value = "7368", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	public Boolean zellmaterialNichtVerwertbar;
	@Feld(value = "7405", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public EndozervikaleZellen endozervikaleZellen;
	@Feld(value = "7406", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 10)
	public String proliferationsgrad;
	@Feld(value = "7407", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public Boolean doederleinflora;
	@Feld(value = "7408", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public Boolean mischflora;
	@Feld(value = "7409", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public Boolean kokkenflora;
	@Feld(value = "7410", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public Boolean trichomonaden;
	@Feld(value = "7411", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public Boolean candida;
	@Feld(value = "7412", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public Boolean gardnerella;
	@Feld(value = "7414", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 5)
	public String gruppe;
	@Feld(value = "7413", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 4)
	public String codierungGruppe;
	@Feld(value = "7415", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public Boolean zytologischeKontrolle;
	@Feld(value = "7416", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public NachkontrollGrund nachkontrollGrund;
	@Feld(value = "7417", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 1)
	public List<Boolean> histologischeKlaerung;
	@Feld(value = "8237", feldart = Feldart.kann)
	@Regelsatz(laenge = 10 /* XXX 12 according to spec */)
	public Fliesstext ergebnistext;
	@Feld(value = "8134", name = "Krebsfrueherkennung_Frauen", feldart = Feldart.kann)
	@Regelsatz(laenge = 26)
	public KrebsfrueherkennungFrauen krebsfrueherkennungFrauen;
	@Feld(value = "8126", name = "Fehlermeldung_Aufmerksamkeit", feldart = Feldart.kann)
	@Regelsatz(laenge = 28)
	public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
	@Feld(value = "8220", name = "Timestamp_Eingangserfassung_Material", feldart = Feldart.kann)
	@Regelsatz(laenge = 36)
	public Timestamp timestampEingangserfassungMaterial;
	@Feld(value = "8222", name = "Timestamp_Beginn_Analytik", feldart = Feldart.kann)
	@Regelsatz(laenge = 25)
	public Timestamp timestampBeginnAnalytik;
	@Feld(value = "8223", name = "Timestamp_Ergebniserstellung", feldart = Feldart.kann)
	@Regelsatz(laenge = 28)
	public Timestamp timestampErgebniserstellung;
	@Feld(value = "8224", name = "Timestamp_QM_Erfassung", feldart = Feldart.kann)
	@Regelsatz(laenge = 22)
	public Timestamp timestampQmErfassung;
	@Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 17)
	public Timestamp timestampMessung;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	@Regelsatz(laenge = 26)
	public List<Fliesstext> zusaetzlicheInformationen;
	@Feld(value = "8110", feldart = Feldart.kann)
	@Regelsatz(laenge = 6)
	public List<Anhang> anhang;
	@Feld(value = "8141", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 13)
	public Namenskennung namenskennung;
	@Feld(value = "8158", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 23)
	public Untersuchungsabrechnung untersuchungsabrechnung;

}
