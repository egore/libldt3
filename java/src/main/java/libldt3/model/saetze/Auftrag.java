
package libldt3.model.saetze;

import java.util.List;

import libldt3.annotations.Datenpaket;
import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Satzart;
import libldt3.model.objekte.Abrechnungsinformationen;
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

/**
 * Satzart: Auftrag "8215"
 */
@Datenpaket(Satzart.Auftrag)
public class Auftrag implements Satz, Kontext {

    @Feld(value = "8122", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 23)
    public Einsenderidentifikation einsenderidentifikation;
    @Feld(value = "8145", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 7)
    public Patient patient;
    @Feld(value = "8169", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 19)
    public Koerperkenngroessen koerperkenngroessen;
    @Feld(value = "8150", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 15)
    public Schwangerschaft schwangerschaft;
    @Feld(value = "8140", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 12)
    public Mutterschaft mutterschaft;
    @Feld(value = "8153", name = "Tier_Sonstiges", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 14)
    public Tier tier;
    @Feld(value = "8113", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 19)
    public Auftragsinformation auftragsinformation;
    @Feld(value = "8127", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 18)
    public List<Veranlassungsgrund> veranlassungsgrund;
    @Feld(value = "8101", feldart = Feldart.muss)
    @Regelsatz(laenge = 22)
    public Abrechnungsinformationen abrechnungsinformationen;
    @Feld(value = "8137", feldart = Feldart.bedingt_kann)
    public List<Material> material;
    @Feld(value = "8159", feldart = Feldart.muss)
    @Regelsatz(laenge = 24)
    public List<Untersuchungsanforderung> untersuchungsanforderung;
    @Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaeztlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
