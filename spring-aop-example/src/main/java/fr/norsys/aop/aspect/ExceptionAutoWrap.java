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
     * D�finition d'un Pointcut : l'ex�cution de la m�thode UserServiceImpl.thisWillFailMiserabily()
     */
    @Pointcut("execution(void fr.norsys.aop.application.UserServiceImpl.thisWillFailMiserabily()))")
    private void failingMethod() {
        // un pointcut est juste une signature de m�thode
    }

    /**
     * Toutes les exceptions renvoy�es au sein d'un bean Spring sera automatiquement Wrappe en exception de domaine
     * C'EST PAS UNE BONNE IDEE !
     *
     *  C'est juste pour l'exemple
     *
     *
     * Ici, on voit que le point d'entr�e est "insideNorsys", donc dans le package fr.norsys.. et qui ne correspond pas
     * au pointcut "failingMethod()"
     * Ca permet de tout de suite voir l'int�r�t de cr�er des pointcuts nomm�s clairements afin que les expressions sur
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
     * Toutes les RuntimeException renvoy�es au sein d'un bean Spring sera automatiquement encapsul�es en Exception standard
     * C'EST PAS UNE BONNE IDEE !
     *
     * Le syst�me g�n�re alors automatiquement une {@link java.lang.reflect.UndeclaredThrowableException} de type
     * Runtime pour r�ussir � respecter le fait qu'une Checked Exception remonte � un endroit o� il ne devrait pas y en
     * avoir sans �tre explicitement g�r�e
     *
     * C'est juste pour l'exemple
     */
    @AfterThrowing(pointcut = "failingMethod()", throwing = "exception")
    public void wrapIntoNotRuntimeException(final RuntimeException exception) throws Exception {
        LOGGER.info("Wrapping exception {} into standard other exception {}", exception, "");
        throw new Exception(exception);
    }
}
