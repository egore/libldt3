
package libldt3.model.objekte;

import java.time.LocalDate;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.EinschreibestatusSelektivvertraege;
import libldt3.model.enums.Gebuehrenordnung;
import libldt3.model.regel.F002;

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
public class AbrechnungSelektivvertrag implements Kontext {

    @Feld(value = "3130", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public EinschreibestatusSelektivvertraege einschreibestatusSelektivvertraege;
    @Feld(value = "3134", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String bezeichnungSelektivvertrag;
    @Feld(value = "4134", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 28)
    public String kostentraegername;
    @Feld(value = "3131", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate teilnahmeVon;
    @Feld(value = "3132", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate teilnahmeBis;
    @Feld(value = "3133", feldart = Feldart.bedingt_kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate datumAntragstellung;
    @Feld(value = "7430", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 60)
    public String patientenIdSelektivvertrag;
    @Feld(value = "4121", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Gebuehrenordnung gebuehrenordnung;
    @Feld(value = "8148", feldart = Feldart.muss)
    @Regelsatz(laenge = 12)
    public Rechnungsempfaenger rechnungsempfaenger;

}
