package fr.norsys.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogByParameters {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogByParameters.class);

    /**
     * Définition d'un Pointcut : une définition de règles spécifiques qui peuvent être utilisées seules ou avec
     * d'autres dans un Advice
     *
     * Ici, la définition du pointcut est une méthode non statique, mais elle aurait pu l'être.
     * La méthode est privée, mais elle aurait aussi pu être publique
     */
    @Pointcut("within(fr.norsys.aop.application..*)")
    private void insideNorsysServices() {
        // un pointcut est juste une signature de méthode
    }

    /**
     * Ici, on crée une règle où on dit qu'on veut un aspect sur les Bean Spring dans le package
     * fr.norsys.aop.application auquel on passe on a un paramètre unique de type Long
     */
    @Before("insideNorsysServices() && args(longValue)")
    private static void logForLong(final Long longValue) {
        LOGGER.info("Y'a un service qui va bouffer un {}", longValue);
    }

    /**
     * Définition d'un pointcut pour lequel on a un seul argument de type Long
     */
    @Pointcut("args(longValue)")
    private void withLongParameter(final Long longValue) {
        // un pointcut est juste une signature de méthode
    }

    /**
     * Ici, on crée une règle où on dit qu'on veut un aspect sur les Bean Spring dans le package
     * fr.norsys.aop.application auquel on passe on a un paramètre unique de type Long
     *
     * Et on rajoute en plus des infos sur le join point
     */
    @Before("insideNorsysServices() && withLongParameter(myLongValue)")
    private static void logForLongWithPointcutInfo(final JoinPoint joinPoint, final Long myLongValue) {
        LOGGER.info("Y'a le service {} qui va bouffer un {}", joinPoint.getSignature().toShortString(), myLongValue);
    }
}
