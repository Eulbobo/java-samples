package fr.norsys.web.aop.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Méthode qui indique un besoin de log automatique
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface LogAround {

    String value() default "";

}
