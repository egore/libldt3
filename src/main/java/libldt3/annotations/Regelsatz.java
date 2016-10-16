package libldt3.annotations;

import libldt3.model.regel.Regel;
import libldt3.model.regel.kontext.Kontextregel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * List of rules to be applied using a boolean 'OR'
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Regelsatz {

	Class<? extends Regel>[] value() default {};

	int laenge() default -1;

	int minLaenge() default -1;

	int maxLaenge() default -1;

	Class<? extends Kontextregel>[] kontextregeln() default {};

}
