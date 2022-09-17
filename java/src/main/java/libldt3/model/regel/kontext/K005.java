package libldt3.model.regel.kontext;

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import libldt3.model.enums.Auftragsstatus;
import libldt3.model.enums.Satzart;

/**
 * In Befunden mit dem Status "Auftrag nicht abgeschlossen" dürfen keine
 * Abrechnungsinformationen übertragen werden.
 * 
 * Nur in Befunden mit dem Status "Auftrag abgeschlossen" können
 * Abrechnungsinformationen übertragen werden.
 */
public class K005 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K005.class);

    private static final Set<String> FIELDTYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("8000", "8401", "4121")));

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

        Satzart satzart = (Satzart) fields.get("8000").get(owner);
        if (satzart == Satzart.Befund) {

            // Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 1, darf FK 4121 nicht vorhanden sein.
            Auftragsstatus auftragsstatus = (Auftragsstatus) fields.get("8401").get(owner);
            if (auftragsstatus == Auftragsstatus.Auftrag_nicht_abgeschlossen) {
                if (containsAnyString(fields.get("8410"), owner)) {
                    return false;
                }
            }

            // Wenn Feldinhalt von FK 8000 = 8205 und der Inhalt FK 8401 = 2, kann FK 4121 vorhanden sein
        }

        return true;

    }

}
