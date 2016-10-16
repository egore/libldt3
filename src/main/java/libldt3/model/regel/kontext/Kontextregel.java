package libldt3.model.regel.kontext;

import java.lang.reflect.Field;

public interface Kontextregel {

	boolean isValid(Field field, Object owner) throws IllegalAccessException;

}
