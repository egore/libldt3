
package libldt3.model.objekte;

import java.time.LocalDate;
import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import lombok.Getter;
import lombok.Setter;

/**
 * In diesem Objekt wird das Muster 39, Grundlage für die
 * Krebsfrüherkennungsuntersuchung für Frauen, abgebildet.
 */
@Objekt("0034")
public @Getter @Setter class KrebsfrueherkennungFrauen {

	@Feld(value = "7295", feldart = Feldart.muss)
	private LocalDate untersuchungsTag;
	@Feld(value = "7296", feldart = Feldart.kann)
	private Boolean wiederholungsuntersuchung;
	@Feld(value = "7297", feldart = Feldart.kann)
	private LocalDate letzteUntersuchung;
	@Feld(value = "7298", feldart = Feldart.kann)
	private String nrLetzterZytologischerBefund;
	@Feld(value = "7299", feldart = Feldart.kann)
	private String gruppeLetzterBefund;
	@Feld(value = "7336", feldart = Feldart.kann)
	private String gynaekologischeOpStrahlenOderChemotherapie;
	@Feld(value = "7337", feldart = Feldart.bedingt_kann)
	private List<String> artGynaekologischeOpStrahlenOderChemotherapie;
	@Feld(value = "7338", feldart = Feldart.kann)
	private LocalDate datumGynaekologischeOp;
	@Feld(value = "3668", feldart = Feldart.kann)
	private Integer anzahlSchwangerschaften;
	@Feld(value = "8512", feldart = Feldart.kann)
	private LocalDate ersterTagLetzterZyklus;
	@Feld(value = "7339", feldart = Feldart.kann)
	private Boolean graviditaet;
	@Feld(value = "7380", feldart = Feldart.kann)
	private Boolean pathologischeGynaekologischeBlutungen;
	@Feld(value = "7381", feldart = Feldart.kann)
	private Boolean sonstigerAusfluss;
	@Feld(value = "7382", feldart = Feldart.kann)
	private Boolean iup;
	@Feld(value = "7383", feldart = Feldart.kann)
	private Boolean ovulationshemmer;
	@Feld(value = "7384", feldart = Feldart.kann)
	private Boolean sonstigeHormonanwendung;
	@Feld(value = "7385", feldart = Feldart.kann)
	private Boolean vulvaInspektionAuffaellig;
	@Feld(value = "7386", feldart = Feldart.kann)
	private Boolean portioUndVaginaAuffaellig;
	@Feld(value = "7387", feldart = Feldart.kann)
	private Boolean innereGenitaleAuffaellig;
	@Feld(value = "7388", feldart = Feldart.kann)
	private Boolean inguinaleLymphknotenAuffaellig;
	@Feld(value = "7389", feldart = Feldart.kann)
	private Boolean behandlungsbeduerftigeNebenbefunde;
	@Feld(value = "7390", feldart = Feldart.kann)
	private Boolean haut;
	@Feld(value = "7391", feldart = Feldart.kann)
	private Boolean mammaAuffaellig;
	@Feld(value = "7392", feldart = Feldart.kann)
	private Boolean axilläreLymphknotenAuffällig;
	@Feld(value = "7393", feldart = Feldart.kann)
	private Boolean rektumKolonBlutOderSchleim;
	@Feld(value = "7394", feldart = Feldart.kann)
	private Boolean neuAufgetreteneUnregelmaessigkeiten;
	@Feld(value = "7395", feldart = Feldart.kann)
	private Boolean rektumKolonTastbefundAuffaellig;
	@Feld(value = "7396", feldart = Feldart.kann)
	private Boolean stuhltestZurueckgegeben;
	@Feld(value = "7397", feldart = Feldart.kann)
	private Boolean stuhltestPositiv;
	@Feld(value = "7423", feldart = Feldart.kann)
	private Boolean gynaekologischeDiagnose;
	@Feld(value = "7398", feldart = Feldart.kann)
	private Boolean rrBlutdruck;
	@Feld(value = "7399", feldart = Feldart.kann)
	private Boolean rrZweiteMessung;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	private List<Fliesstext> zusaetzlicheInformationen;

}
