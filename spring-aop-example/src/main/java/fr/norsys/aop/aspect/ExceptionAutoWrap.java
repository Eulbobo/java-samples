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
     * Toutes les exceptions renvoy�es au sein d'un bean Spring sera automatiquement Wrappe
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
