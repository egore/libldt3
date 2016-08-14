
package libldt3.model.objekte;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.model.enums.EinschreibestatusSelektivvertraege;
import libldt3.model.enums.Gebuehrenordnung;
import lombok.Getter;
import lombok.Setter;

/**
 * Mit diesem Objekt werden die Informationen für die Abrechnung von
 * Untersuchungsanforderungen zusammengefasst, welche im Rahmen von
 * Selektivverträgen und damit außerhalb der budgetären Leistungen erbracht
 * werden. Die Möglichkeit zum Abschluss von Selektivverträgen besteht im
 * Wesentlichen in der hausarztzentrierten Versorgung (§ 73 b SGB V), in der
 * besonderen ambulanten ärztlichen Versorgung (§ 73 c SGB V), bei
 * strukturierten Behandlungsprogrammen für chronische Erkrankungen
 * (Disease-Management-Programme) (§ 137 f SGB V) und in der Integrierten
 * Versorgung (§§ 140ff SGB V).
 */
@Objekt("0006")
public @Getter @Setter class AbrechnungSelektivvertrag {

	@Feld(value = "3130", feldart = Feldart.muss)
	private EinschreibestatusSelektivvertraege einschreibestatusSelektivvertraege;
	@Feld(value = "3134", feldart = Feldart.muss)
	private String bezeichnungSelektivvertrag;
	@Feld(value = "4134", feldart = Feldart.bedingt_muss)
	private String kostentraegername;
	@Feld(value = "3131", feldart = Feldart.bedingt_muss)
	private String teilnahmeVon;
	@Feld(value = "3132", feldart = Feldart.bedingt_muss)
	private String teilnahmeBis;
	@Feld(value = "3133", feldart = Feldart.bedingt_kann)
	private String datumAntragstellung;
	@Feld(value = "4121", feldart = Feldart.kann)
	private Gebuehrenordnung gebuehrenordnung;
	@Feld(value = "8148", feldart = Feldart.muss)
	private Rechnungsempfaenger rechnungsempfaenger;

}
