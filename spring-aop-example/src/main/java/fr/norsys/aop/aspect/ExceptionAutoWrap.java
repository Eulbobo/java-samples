package fr.norsys.aop.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.norsys.aop.domain.exception.DomainException;

@Aspect
@Component
public class ExceptionAutoWrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAutoWrap.class);

    /**
     * Définition d'un Pointcut : une définition de rêgles spécifiques qui peuvent être utilisées seules ou avec
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
     * Définition d'un Pointcut : l'exécution de la méthode UserServiceImpl.thisWillFailMiserabily()
     */
    @Pointcut("execution(void fr.norsys.aop.application.UserServiceImpl.thisWillFailMiserabily()))")
    private void failingMethod() {
        // un pointcut est juste une signature de méthode
    }

    /**
     * Toutes les exceptions renvoyées au sein d'un bean Spring sera automatiquement Wrappe en exception de domaine
     * C'EST PAS UNE BONNE IDEE !
     *
     *  C'est juste pour l'exemple
     *
     *
     * Ici, on voit que le point d'entrée est "insideNorsys", donc dans le package fr.norsys.. et qui ne correspond pas
     * au pointcut "failingMethod()"
     * Ca permet de tout de suite voir l'intérét de créer des pointcuts nommés clairements afin que les expressions sur
     * les advices soient clairs.
     */
    @AfterThrowing(pointcut = "insideNorsys() && !failingMethod()", throwing = "exception")
    public void catchLogAndRethrow(final Exception exception) throws Exception {
        if (DomainException.class.isAssignableFrom(exception.getClass())) {
            throw exception;
        }
        LOGGER.info("Wrapping exception {} into {}", exception, "DomainException");
        throw new DomainException(exception);
    }

    /**
     * Toutes les RuntimeException renvoyées au sein d'un bean Spring sera automatiquement encapsulées en Exception standard
     * C'EST PAS UNE BONNE IDEE !
     *
     * Le systéme génére alors automatiquement une {@link java.lang.reflect.UndeclaredThrowableException} de type
     * Runtime pour réussir é respecter le fait qu'une Checked Exception remonte é un endroit oé il ne devrait pas y en
     * avoir sans être explicitement gérée
     *
     * C'est juste pour l'exemple
     */
    @AfterThrowing(pointcut = "failingMethod()", throwing = "exception")
    public void wrapIntoNotRuntimeException(final RuntimeException exception) throws Exception {
        LOGGER.info("Wrapping exception {} into standard other exception {}", exception, "");
        throw new Exception(exception);
    }
}
