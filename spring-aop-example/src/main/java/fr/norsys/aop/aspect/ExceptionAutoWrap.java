package fr.norsys.aop.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import fr.norsys.aop.domain.exception.DomainException;

@Aspect
@Component
public class ExceptionAutoWrap {

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
     * Toutes les exceptions renvoyées au sein d'un bean Spring sera automatiquement Wrappe
     * C'EST PAS UNE BONNE IDEE !
     *
     * C'est juste pour l'exemple
     */
    @AfterThrowing(pointcut = "insideNorsys()", throwing = "exception")
    private void catchLogAndRethrow(final Exception exception) throws Exception {
        if (exception.getClass().isAssignableFrom(DomainException.class)) {
            throw exception;
        }
        throw new DomainException(exception);
    }
}
