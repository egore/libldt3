
package libldt3.model.objekte;

import java.time.LocalDate;
import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.regel.format.F002;
import libldt3.model.regel.format.F015;
import libldt3.model.regel.format.F018;

/**
 * In diesem Objekt wird das Muster 39, Grundlage für die
 * Krebsfrüherkennungsuntersuchung für Frauen, abgebildet.
 */
@Objekt("0034")
public class KrebsfrueherkennungFrauen implements Kontext {

    @Feld(value = "7295", feldart = Feldart.muss)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate untersuchungsTag;
    @Feld(value = "7296", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean wiederholungsuntersuchung;
    @Feld(value = "7297", feldart = Feldart.kann)
    @Regelsatz(value = F018.class, laenge = 8)
    public LocalDate letzteUntersuchung;
    @Feld(value = "7298", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String nrLetzterZytologischerBefund;
    @Feld(value = "7299", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 6)
    public String gruppeLetzterBefund;
    @Feld(value = "7336", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public String gynaekologischeOpStrahlenOderChemotherapie;
    @Feld(value = "7337", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public List<String> artGynaekologischeOpStrahlenOderChemotherapie;
    @Feld(value = "7338", feldart = Feldart.kann)
    @Regelsatz(value = F018.class, laenge = 8)
    public LocalDate datumGynaekologischeOp;
    @Feld(value = "3668", feldart = Feldart.kann)
    @Regelsatz(laenge = 2)
    public Integer anzahlSchwangerschaften;
    @Feld(value = "8512", feldart = Feldart.kann)
    @Regelsatz(value = F018.class, laenge = 8)
    public LocalDate ersterTagLetzterZyklus;
    @Feld(value = "7339", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean graviditaet;
    @Feld(value = "7380", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean pathologischeGynaekologischeBlutungen;
    @Feld(value = "7381", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean sonstigerAusfluss;
    @Feld(value = "7382", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean iup;
    @Feld(value = "7383", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean ovulationshemmer;
    @Feld(value = "7384", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean sonstigeHormonanwendung;
    @Feld(value = "7385", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean vulvaInspektionAuffaellig;
    @Feld(value = "7386", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean portioUndVaginaAuffaellig;
    @Feld(value = "7387", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean innereGenitaleAuffaellig;
    @Feld(value = "7388", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean inguinaleLymphknotenAuffaellig;
    @Feld(value = "7389", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean behandlungsbeduerftigeNebenbefunde;
    @Feld(value = "7390", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean haut;
    @Feld(value = "7391", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean mammaAuffaellig;
    @Feld(value = "7392", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean axillaereLymphknotenAuffaellig;
    @Feld(value = "7393", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean rektumKolonBlutOderSchleim;
    @Feld(value = "7394", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean neuAufgetreteneUnregelmaessigkeiten;
    @Feld(value = "7395", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean rektumKolonTastbefundAuffaellig;
    @Feld(value = "7396", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean stuhltestZurueckgegeben;
    @Feld(value = "7397", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean stuhltestPositiv;
    @Feld(value = "7423", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 990)
    public Boolean gynaekologischeDiagnose;
    @Feld(value = "7398", feldart = Feldart.kann)
    @Regelsatz(value = F015.class, laenge = 7)
    public String rrBlutdruck;
    @Feld(value = "7399", feldart = Feldart.kann)
    @Regelsatz(value = F015.class, laenge = 7)
    public String rrZweiteMessung;
    @Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;

}
