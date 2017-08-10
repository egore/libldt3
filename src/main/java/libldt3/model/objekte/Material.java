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
import libldt3.model.enums.AnorganischesMaterial;
import libldt3.model.enums.Materialart;
import libldt3.model.enums.OrganischesMaterial;
import libldt3.model.regel.F006;
import libldt3.model.regel.kontext.K006;
import lombok.Getter;
import lombok.Setter;

/**
 * Im Objekt werden die Informationen zur Identifikation des zu untersuchenden
 * Materials übermittelt sowie Angaben zum Material selbst.
 */
@Objekt(value = "0037",kontextregeln = K006.class)
public @Getter @Setter class Material {

	@Objekt
	public static @Getter @Setter class AnorganischesMaterialErweitert {
		@SuppressWarnings("unused")
		private AnorganischesMaterial value;
		@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.bedingt_kann)
		@Regelsatz(laenge = 26)
		private Fliesstext zusaetzlicheInformatioen;
	}

	@Objekt
	public static @Getter @Setter class Medikamenteneinnahme {
		@SuppressWarnings("unused")
		private Boolean value;
		@Feld(value = "8170", feldart = Feldart.bedingt_kann)
		@Regelsatz(laenge = 10)
		private Medikament medication;
	}

	@Feld(value = "7364", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 60)
	private String probengefaessIdent;
	@Feld(value = "8429", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 4)
	private String probenmaterialIndex;
	@Feld(value = "8428", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private String probenmaterialIdent;
	@Feld(value = "8430", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
	private String probenmaterialBezeichnung;
	@Feld(value = "8431", feldart = Feldart.bedingt_kann)
	@Regelsatz(maxLaenge = 60)
	private String probenmaterialSpezifikation;
	@Feld(value = "7292", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private String lokalisationProbenmaterial;
	@Feld(value = "7310", feldart = Feldart.bedingt_kann)
	@Regelsatz(laenge = 1)
	private Materialart materialart;
	@Feld(value = "7311", feldart = Feldart.bedingt_kann)
	@Regelsatz(laenge = 1)
	private OrganischesMaterial organischesMaterial;
	@Feld(value = "7312", feldart = Feldart.bedingt_kann)
	@Regelsatz(laenge = 1)
	private AnorganischesMaterialErweitert anorganischesMaterial;
	@Feld(value = "8504", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	private List<Medikamenteneinnahme> medikamenteneinnahme;
	@Feld(value = "7318", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private List<String> nahrungsaufnahmeZeitpunktMaterialentnahme;
	@Feld(value = "8520", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private String menge;
	@Feld(value = "8421", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 20)
	private String einheit;
	@Feld(value = "8522", feldart = Feldart.bedingt_kann)
	@Regelsatz(value = F006.class, laenge = 4)
	private String sammelzeit;
	@Feld(value = "8219", name = "Timestamp_Materialabnahme_entnahme", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 34 /* XXX 33 according to the spec */)
	private Timestamp timestampMaterialabnahmeEntnahme;
	@Feld(value = "8220", name = "Timestamp_Eingangserfassung_Material", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 36)
	private Timestamp timestampEingangserfassungMaterial;
	@Feld(value = "8126", name = "Fehlermeldung_Aufmerksamkeit", feldart = Feldart.kann)
	@Regelsatz(laenge = 28)
	private FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	@Regelsatz(laenge = 26)
	private List<Fliesstext> zusaetzlicheInformationen;
	@Feld(value = "8110", feldart = Feldart.kann)
	@Regelsatz(laenge = 6)
	private List<Anhang> anhang;

}
