package fr.norsys.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.norsys.aop.annotation.Log;

@Aspect
@Component
public class AnnotationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationAspect.class);

    @Pointcut("@annotation(fr.norsys.aop.annotation.Log)")
    private void logAnnotation() {
        // log annotation is present
    }

    @Pointcut("@within(fr.norsys.aop.annotation.Loggable)")
    private void logWithinClass() {
        // log annotation is present
    }

    @Around("logAnnotation()")
    private Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        LOGGER.info("Entr\u00e9e dans {} avec l'annotation @Log", joinPoint.getSignature().toShortString());
        try {
            result = joinPoint.proceed();
        } finally {
            LOGGER.info("Sortie de {} avec annotation @Log", joinPoint.getSignature().toShortString());
        }
        return result;
    }

    @AfterReturning(pointcut = "logAnnotation()", returning = "returned")
    private void logAround(final Object returned) throws Throwable {
        LOGGER.info("On renvoie {} avec l'annotation @Log", returned);
    }

    @After("logWithinClass()")
    private void logAfterForAnnotatedClass() {
        LOGGER.info("Sortie de la m\u00e9thode d'une classe annot\u00e9e");
    }

    @Before("@annotation(annotation)")
    private void logBeforeAnnotation(final Log annotation) {
        LOGGER.info("Avant une m\u00e9thode qui possible l'annotation avec la valeur d'annotation : >{}<",
                annotation.value());
    }

}
