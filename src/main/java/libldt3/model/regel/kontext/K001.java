package libldt3.model.regel.kontext;

import libldt3.annotations.Feld;

import java.lang.reflect.Field;

public class K001 implements Kontextregel {

    @Override
	public boolean isValid(Field field, Object owner) throws IllegalAccessException {
		field.setAccessible(true);
		if (containsString(field, owner)) {
			return true;
		}
		for (Field f : field.getDeclaringClass().getDeclaredFields()) {
			Feld annotation = f.getAnnotation(Feld.class);
			if (annotation != null && (annotation.value().equals("6305") || annotation.value().equals("8242"))) {
				if (containsString(f, owner)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean containsString(Field field, Object owner) throws IllegalAccessException {
		String o = (String) field.get(owner);
		return o != null && !o.isEmpty();
	}
}
