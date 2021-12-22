package libldt3.model.regel.kontext;

import libldt3.model.enums.Abrechnungsinfo;
import libldt3.model.objekte.Arztidentifikation;
import libldt3.model.objekte.Untersuchungsanforderung;
import libldt3.model.saetze.Auftrag;

/**
 * FK 0222 muss vorhanden sein, wenn in mindestens einem Obj_0059 (Obj_Untersuchungsanforderung) die FK 7303 mit dem
 * Inhalt 8 vorhanden ist.
 */
public class K057 implements Kontextregel {

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {

        Auftrag auftrag = (Auftrag) owner;

        // Valid, as no Obj_0059 present
        if (auftrag.getUntersuchungsanforderung() == null || auftrag.getUntersuchungsanforderung().isEmpty()) {
            return true;
        }

        for (Untersuchungsanforderung untersuchungsanforderung : auftrag.getUntersuchungsanforderung()) {
            if (untersuchungsanforderung.getAbrechnungsinfo() == Abrechnungsinfo.Asv) {

                // No identification at all, not valid
                if (auftrag.getEinsenderidentifikation() == null) {
                    return false;
                }

                // If any FK 0222 is present, it's valid
                return isNotEmpty(auftrag.getEinsenderidentifikation().getArztidentifikation()) ||
                        isNotEmpty(auftrag.getEinsenderidentifikation().getUeberweisungAn()) ||
                        isNotEmpty(auftrag.getEinsenderidentifikation().getUeberweisungVon());
            }
        }

        return true;
    }

    private boolean isNotEmpty(Arztidentifikation arztidentifikation) {
        return arztidentifikation.getAsvTeamnummer() != null &&
                !arztidentifikation.getAsvTeamnummer().isEmpty();
    }

}
