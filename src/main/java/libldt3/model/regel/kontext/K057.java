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
        if (auftrag.untersuchungsanforderung == null || auftrag.untersuchungsanforderung.isEmpty()) {
            return true;
        }

        for (Untersuchungsanforderung untersuchungsanforderung : auftrag.untersuchungsanforderung) {
            if (untersuchungsanforderung.abrechnungsinfo == Abrechnungsinfo.Asv) {

                // No identification at all, not valid
                if (auftrag.einsenderidentifikation == null) {
                    return false;
                }

                // If any FK 0222 is present, it's valid
                return isNotEmpty(auftrag.einsenderidentifikation.arztidentifikation) ||
                        isNotEmpty(auftrag.einsenderidentifikation.ueberweisungAn) ||
                        isNotEmpty(auftrag.einsenderidentifikation.ueberweisungVon);
            }
        }

        return true;
    }

    public boolean isNotEmpty(Arztidentifikation arztidentifikation) {
        return arztidentifikation.asvTeamnummer != null &&
                !arztidentifikation.asvTeamnummer.isEmpty();
    }

}
