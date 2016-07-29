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
 * Déclaration d'un aspect de type Around
 *
 * On le déclare avec l'annotation @Aspect pour définir que c'est un aspect, et avec l'annotation @Component pour rendre
 * l'objet disponible dans le contexte Spring
 */
@Aspect
@Component
public class LogAroundEverything {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAroundEverything.class);

    /**
     * Ici, nous décrivons deux éléments
     * - le point d'entrée grâce à l'annotation @Around qui indique une exécution "autour" d'un point avec une
     * expression aspectJ
     * - le traitement à effectuer
     *
     * La méthode est statique, mais elle pourrait aussi ne pas l'être.
     * La méthode est publique, mais elle pourrait aussi être private.
     *
     * @param joinPoint le point d'entrée inspecté correspondant à l'expression aspectJ
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
     * Définition d'un Pointcut : une définition de règles spécifiques qui peuvent être utilisées seules ou avec
     * d'autres dans un Advice
     *
     * Ici, la définition du pointcut est une méthode non statique, mais elle aurait pu l'être.
     * La méthode est privée, mais elle aurait aussi pu être publique
     */
    @Pointcut("within(fr.norsys..*)")
    private void insideNorsys() {
        // un pointcut est juste une signature de méthode
    }

    /**
     * Dans cette déclaration d'aspect, on utilise le nom du pointcut défini plus haut comme règle pour intercepter les
     * appels.
     * Avec le tag @Before, toutes les méthodes correspondant à l'expression du @PointCut insideNorsys() seront pris.
     *
     * Donc tout ce qui est à l'intérieur du package fr.norsys.* et déclaré dans Spring sera intercepté ici
     *
     * Ici, la méthode est statique et utilise un PointCut qui ne l'est pas sans aucun problème.
     * La méthode est privée
     *
     * @param joinPoint le JoinPoint correspondant à l'expression
     * @throws Throwable
     */
    @Before("insideNorsys()")
    private static void logBeforeWithPointCut(final JoinPoint joinPoint) throws Throwable {
        LOGGER.info("On entre dans {} avec les paramètres {}", joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    /**
     * On déclare un advice de type After basé sur l'expression insideNorsys()
     * Pas de paramètre, donc dans ce cas, on n'aura pas d'information sur la méthode d'où on vient de sortie
     */
    @After("insideNorsys()")
    private static void logAfterWithoutParams() {
        LOGGER.info("On est sorti d'une méthode");
    }

}
