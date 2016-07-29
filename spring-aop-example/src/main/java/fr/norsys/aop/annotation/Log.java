package fr.norsys.aop.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * methode qui indique un besoin de log automatique
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Log {

    String value() default "";

}
