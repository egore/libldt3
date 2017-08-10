package libldt3.model.enums;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.objekte.FehlermeldungAufmerksamkeit;
import lombok.Getter;
import lombok.Setter;

@Objekt
public @Getter  @Setter  class GrenzwertindikatorErweitert {
    @SuppressWarnings("unused")
    private Grenzwertindikator value;
    @Feld(value = "8126", name = "Fehlermeldung_Aufmerksamkeit", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    private FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
}