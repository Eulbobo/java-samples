package fr.norsys.web.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect permettant de faire un log autour de l'annotation LogAround
 *
 * Package protected par design pour le rendre invisible
 * <p>
 * Société : CNAMTS
 * </p>
 * @author NORSYS
 */
@Aspect
@Component
class LogAround {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAround.class);

    @Pointcut("@annotation(fr.norsys.web.aop.annotation.LogAround)")
    private void logAnnotation() {
        // log annotation is present
    }

    @Around("logAnnotation()")
    private Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        LOGGER.info("Entering method {} with parameters {}", joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
        try {
            result = joinPoint.proceed();
        } finally {
            LOGGER.info("Exiting method", joinPoint.getSignature().toShortString());
        }
        return result;
    }

}
