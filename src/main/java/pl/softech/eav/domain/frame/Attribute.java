package pl.softech.eav.domain.frame;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author ssledz
 */
@Target({ ElementType.METHOD })
@Retention(RUNTIME)
public @interface Attribute {
	String name() default "";
}