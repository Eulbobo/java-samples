package fr.norsys.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * D�claration d'un aspect de type Around
 *
 * On le d�clare avec l'annotation @Aspect pour d�finir que c'est un aspect, et avec l'annotation @Component pour rendre
 * l'objet disponible dans le contexte Spring
 */
@Aspect
@Component
public class LogAroundEverything {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAroundEverything.class);

    /**
     * Ici, nous d�crivons deux �l�ments
     * - le point d'entr�e gr�ce � l'annotation @Around qui indique une ex�cution "autour" d'un point avec une
     * expression aspectJ
     * - le traitement � effectuer
     *
     * La m�thode est statique, mais elle pourrait aussi ne pas l'�tre.
     * La m�thode est publique, mais elle pourrait aussi �tre private.
     *
     * @param joinPoint le point d'entr�e inspect� correspondant � l'expression aspectJ
     * @throws Throwable pour renvoyer tout de suite les exceptions qui ont lieu
     */
    @Around("execution(* *(..))")
    public static void logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
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

    /**
     * D�finition d'un Pointcut : une d�finition de r�gles sp�cifiques qui peuvent �tre utilis�es seules ou avec
     * d'autres dans un Advice
     *
     * Ici, la d�finition du pointcut est une m�thode non statique, mais elle aurait pu l'�tre.
     * La m�thode est priv�e, mais elle aurait aussi pu �tre publique
     */
    @Pointcut("within(fr.norsys..*)")
    private void insideNorsys() {
        // un pointcut est juste une signature de m�thode
    }

    /**
     * Dans cette d�claration d'aspect, on utilise le nom du pointcut d�fini plus haut comme r�gle pour intercepter les
     * appels.
     * Avec le tag @Before, toutes les m�thodes correspondant � l'expression du @PointCut insideNorsys() seront pris.
     *
     * Donc tout ce qui est � l'int�rieur du package fr.norsys.* et d�clar� dans Spring sera intercept� ici
     *
     * Ici, la m�thode est statique et utilise un PointCut qui ne l'est pas sans aucun probl�me.
     * La m�thode est priv�e
     *
     * @param joinPoint le JoinPoint correspondant � l'expression
     * @throws Throwable
     */
    @Before("insideNorsys()")
    private static void logBeforeWithPointCut(final JoinPoint joinPoint) throws Throwable {
        LOGGER.info("On entre dans {} avec les param�tres {}", joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    /**
     * On d�clare un advice de type After bas� sur l'expression insideNorsys()
     * Pas de param�tre, donc dans ce cas, on n'aura pas d'information sur la m�thode d'o� on vient de sortie
     */
    @After("insideNorsys()")
    private static void logAfterWithoutParams() {
        LOGGER.info("On est sorti d'une m�thode");
    }

}
