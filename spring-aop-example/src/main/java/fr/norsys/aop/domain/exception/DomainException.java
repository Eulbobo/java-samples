package fr.norsys.aop.domain.exception;

public class DomainException extends RuntimeException{

    private static final long serialVersionUID = 5060128140940869198L;

    public DomainException(final String message) {
        super(message);
    }

    public DomainException(final Throwable cause) {
        super(cause);
    }

}
