package libldt3.model.enums;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.objekte.FehlermeldungAufmerksamkeit;

@Objekt
public class GrenzwertindikatorErweitert implements Kontext {
    @SuppressWarnings("unused")
    public Grenzwertindikator value;
    @Feld(value = "8126", name = "Fehlermeldung_Aufmerksamkeit", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
}