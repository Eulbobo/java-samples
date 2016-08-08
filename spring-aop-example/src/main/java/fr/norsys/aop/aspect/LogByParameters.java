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
     * D�finition d'un Pointcut : une d�finition de r�gles sp�cifiques qui peuvent �tre utilis�es seules ou avec
     * d'autres dans un Advice
     *
     * Ici, la d�finition du pointcut est une m�thode non statique, mais elle aurait pu l'�tre.
     * La m�thode est priv�e, mais elle aurait aussi pu �tre publique
     */
    @Pointcut("within(fr.norsys.aop.application..*)")
    private void insideNorsysServices() {
        // un pointcut est juste une signature de m�thode
    }

    /**
     * Ici, on cr�e une r�gle o� on dit qu'on veut un aspect sur les Bean Spring dans le package
     * fr.norsys.aop.application auquel on passe on a un param�tre unique de type Long
     */
    @Before("insideNorsysServices() && args(longValue)")
    private static void logForLong(final Long longValue) {
        LOGGER.info("Y'a un service qui va bouffer un {}", longValue);
    }

    /**
     * D�finition d'un pointcut pour lequel on a un seul argument de type Long
     */
    @Pointcut("args(longValue)")
    private void withLongParameter(final Long longValue) {
        // un pointcut est juste une signature de m�thode
    }

    /**
     * Ici, on cr�e une r�gle o� on dit qu'on veut un aspect sur les Bean Spring dans le package
     * fr.norsys.aop.application auquel on passe on a un param�tre unique de type Long
     *
     * Et on rajoute en plus des infos sur le join point
     */
    @Before("insideNorsysServices() && withLongParameter(myLongValue)")
    private static void logForLongWithPointcutInfo(final JoinPoint joinPoint, final Long myLongValue) {
        LOGGER.info("Y'a le service {} qui va bouffer un {}", joinPoint.getSignature().toShortString(), myLongValue);
    }
}
