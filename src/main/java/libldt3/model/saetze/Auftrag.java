
package libldt3.model.saetze;

import java.util.List;

import libldt3.annotations.Datenpaket;
import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.model.enums.Satzart;
import libldt3.model.objekte.Abrechnungsinformationen;
import libldt3.model.objekte.Anhang;
import libldt3.model.objekte.Auftragsinformation;
import libldt3.model.objekte.Einsenderidentifikation;
import libldt3.model.objekte.Fließtext;
import libldt3.model.objekte.Koerperkenngroessen;
import libldt3.model.objekte.Material;
import libldt3.model.objekte.Mutterschaft;
import libldt3.model.objekte.Patient;
import libldt3.model.objekte.Schwangerschaft;
import libldt3.model.objekte.Tier;
import libldt3.model.objekte.Untersuchungsanforderung;
import libldt3.model.objekte.Veranlassungsgrund;

/**
 * Satzart: Auftrag "8215"
 */
@Datenpaket(Satzart.Auftrag)
public class Auftrag implements Satz {

	@Objekt
	public static class PatientErweitert {
		@SuppressWarnings("unused")
		private Patient value;
		@Feld(value = "8169", feldart = Feldart.bedingt_kann)
		private Koerperkenngroessen koerperkenngroessen;
		@Feld(value = "8150", feldart = Feldart.bedingt_kann)
		private Schwangerschaft schwangerschaft;
		@Feld(value = "8140", feldart = Feldart.bedingt_kann)
		private Mutterschaft mutterschaft;
	}

	@Feld(value = "8122", feldart = Feldart.bedingt_muss)
	private Einsenderidentifikation einsenderidentifikation;
	@Feld(value = "8145", feldart = Feldart.bedingt_muss)
	private PatientErweitert patient;
	@Feld(value = "8153", feldart = Feldart.bedingt_muss)
	private Tier tier;
	@Feld(value = "8113", feldart = Feldart.bedingt_muss)
	private Auftragsinformation auftragsinformation;
	@Feld(value = "8127", feldart = Feldart.bedingt_muss)
	private List<Veranlassungsgrund> veranlassungsgrund;
	@Feld(value = "8101", feldart = Feldart.muss)
	private Abrechnungsinformationen abrechnungsinformationen;
	@Feld(value = "8137", feldart = Feldart.bedingt_kann)
	private List<Material> material;
	@Feld(value = "8159", feldart = Feldart.muss)
	private List<Untersuchungsanforderung> untersuchungsanforderung;
	@Feld(value = "8167", feldart = Feldart.kann)
	private List<Fließtext> zusaeztlicheInformationen;
	@Feld(value = "8110", feldart = Feldart.kann)
	private List<Anhang> anhang;

}
