package fr.norsys.mockito.domain;

/**
 * RuntimeException utilisée par le domaine de service
 */
public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 5475935154267142617L;

    public DomainException() {
        super();
    }

    public DomainException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DomainException(final String message) {
        super(message);
    }

    public DomainException(final Throwable cause) {
        super(cause);
    }

}
