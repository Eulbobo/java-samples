package fr.norsys.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Déclaration d'un aspect de type Around
 */
@Aspect
@Component
public class LogAroundEverything {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAroundEverything.class);

    @Around("execution(* *(..))")
    public void logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        LOGGER.info("About to enter {} with parameters {}", joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
        try {
            joinPoint.proceed();
        } finally {
            LOGGER.info("Exiting {}, elapsed time : {}ms", joinPoint.getSignature().toShortString(),
                    System.currentTimeMillis() - begin);
        }
    }

    @Before("insideNorsys()")
    public void logBeforeWithPointCut(final JoinPoint joinPoint) throws Throwable {
        LOGGER.info("About to enter {} with parameters {}", joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    /**
     * Définition d'un Pointcut : une définition de règles spécifiques qui peuvent être utilisées seules ou avec
     * d'autres dans un Advice
     */
    @Pointcut("within(fr.norsys..*)")
    private void insideNorsys() {
    }

}
