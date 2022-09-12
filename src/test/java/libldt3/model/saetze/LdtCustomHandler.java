package libldt3.model.saetze;

import de.egore911.fuzz.Fuzzer;
import libldt3.annotations.Feld;
import libldt3.annotations.Regelsatz;
import libldt3.model.enums.LdtVersion;
import libldt3.model.objekte.Untersuchungsabrechnung;
import libldt3.model.regel.F001;
import libldt3.model.regel.F004;
import libldt3.model.regel.F005;
import libldt3.model.regel.F006;
import libldt3.model.regel.F007;
import libldt3.model.regel.F009;
import libldt3.model.regel.F010;
import libldt3.model.regel.F011;
import libldt3.model.regel.F012;
import libldt3.model.regel.F013;
import libldt3.model.regel.F014;
import libldt3.model.regel.F015;
import libldt3.model.regel.F017;
import libldt3.model.regel.F020;
import libldt3.model.regel.Regel;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class LdtCustomHandler implements Fuzzer.CustomHandler {

    private final Random random = new Random();

    @Override
    public Object randomValue(Field field) {
        Feld annotation = field.getAnnotation(Feld.class);
        if (annotation != null) {
            for (Regelsatz regelsatz : field.getAnnotationsByType(Regelsatz.class)) {
                for (Class<? extends Regel> klass : regelsatz.value()) {
                    if (klass == F014.class) {
                        return "001234561";
                    }
                    if (klass == F011.class) {
                        if (field.getType().isAssignableFrom(List.class)) {
                            return Collections.singletonList("123456112");
                        }
                        if (field.getType().isAssignableFrom(String.class)) {
                            return "123456112";
                        }
                    }
                    if (klass == F010.class) {
                        return "0123456[]";
                    }
                    if (klass == F013.class) {
                        return "X012345671";
                    }
                    if (klass == F005.class) {
                        return "896";
                    }
                    if (klass == F001.class) {
                        return "12345";
                    }
                    if (klass == F006.class) {
                        return "9959";
                    }
                    if (klass == F015.class) {
                        return "012/987";
                    }
                    if (klass == F017.class) {
                        return "0123";
                    }
                    if (klass == F004.class) {
                        return "C12";
                    }
                    if (klass == F007.class) {
                        return LdtVersion.LDT3_1_0;
                    }
                    if (klass == F012.class) {
                        return "X/31/0101/01/[]}";
                    }
                    if (klass == F020.class) {
                        return "01234567";
                    }
                    if (klass == F009.class) {
                        Untersuchungsabrechnung.Gebuehrennummer gebuehrennummer = new Untersuchungsabrechnung.Gebuehrennummer();
                        gebuehrennummer.setValue("01234");
                        return Collections.singletonList(gebuehrennummer);
                    }
                }
                if (regelsatz.laenge() >= 0) {
                    Object x = getRandomAtLength(field, regelsatz.laenge());
                    if (x != null) return x;
                } else if (regelsatz.maxLaenge() >= 0) {
                    int min = 0;
                    int max = regelsatz.maxLaenge();
                    if (regelsatz.minLaenge() >= 0) {
                        min = regelsatz.minLaenge();
                        max -= min;
                    }
                    Object x = getRandomAtLength(field, min + random.nextInt(max));
                    if (x != null) return x;
                }
            }
        }
        return null;
    }

    private Object getRandomAtLength(Field field, int laenge) {
        if (field.getType().isAssignableFrom(Integer.class)) {
            if (laenge == 1) {
                // chosen by fair dice roll
                return 4;
            }
            return (int) Math.pow(10, laenge - 1);
        }
        if (field.getType().isAssignableFrom(String.class)) {
            return RandomStringUtils.randomAlphanumeric(laenge);
        }
        if (field.getType().isAssignableFrom(List.class) && (((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]).getClass().isAssignableFrom(String.class)) {
            return Collections.singletonList(RandomStringUtils.randomAlphanumeric(laenge));
        }
        return null;
    }
}
