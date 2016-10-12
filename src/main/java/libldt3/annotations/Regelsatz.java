package libldt3.annotations;

import libldt3.model.regel.Regel;

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

}
