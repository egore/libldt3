
package libldt3.model.saetze;

import java.util.List;

import libldt3.annotations.Datenpaket;
import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.enums.Satzart;
import libldt3.model.objekte.Abrechnungsinformation;
import libldt3.model.objekte.Anhang;
import libldt3.model.objekte.Auftragsinformation;
import libldt3.model.objekte.Einsenderidentifikation;
import libldt3.model.objekte.Fliesstext;
import libldt3.model.objekte.Koerperkenngroessen;
import libldt3.model.objekte.Material;
import libldt3.model.objekte.Mutterschaft;
import libldt3.model.objekte.Patient;
import libldt3.model.objekte.Schwangerschaft;
import libldt3.model.objekte.Tier;
import libldt3.model.objekte.Untersuchungsanforderung;
import libldt3.model.objekte.Veranlassungsgrund;
import lombok.Getter;
import lombok.Setter;

/**
 * Satzart: Auftrag "8215"
 */
@Datenpaket(Satzart.Auftrag)
public @Getter @Setter class Auftrag implements Satz {

	@Objekt
	public static class PatientErweitert {
		@SuppressWarnings("unused")
		private Patient value;
		@Feld(value = "8169", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(laenge = 19))
		private Koerperkenngroessen koerperkenngroessen;
		@Feld(value = "8150", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(laenge = 15))
		private Schwangerschaft schwangerschaft;
		@Feld(value = "8140", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(laenge = 12))
		private Mutterschaft mutterschaft;
	}

	@Feld(value = "8122", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 23))
	private Einsenderidentifikation einsenderidentifikation;
	@Feld(value = "8145", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 7))
	private PatientErweitert patient;
	@Feld(value = "8153", name = "Tier_Sonstiges", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 14))
	private Tier tier;
	@Feld(value = "8113", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 19))
	private Auftragsinformation auftragsinformation;
	@Feld(value = "8127", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 18))
	private List<Veranlassungsgrund> veranlassungsgrund;
	@Feld(value = "8101", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 22))
	private Abrechnungsinformation abrechnungsinformationen;
	@Feld(value = "8137", feldart = Feldart.bedingt_kann)
	private List<Material> material;
	@Feld(value = "8159", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 24))
	private List<Untersuchungsanforderung> untersuchungsanforderung;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 26))
	private List<Fliesstext> zusaeztlicheInformationen;
	@Feld(value = "8110", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 6))
	private List<Anhang> anhang;

}
