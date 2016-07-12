package fr.norsys.mockito.service;

/**
 * Exception générée spécifiquement par les classes de service
 */
public class ServiceException extends Exception {

    /** serial version UID for class */
    private static final long serialVersionUID = 5760768024903007519L;

    public ServiceException() {
        super();
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final Throwable cause) {
        super(cause);
    }

}
