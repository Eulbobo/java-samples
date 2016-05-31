package fr.norsys.exceptions.runtime;

public class LocalRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 7175939045377327343L;

    public LocalRuntimeException() {
        super();
    }

    public LocalRuntimeException(final String message) {
        super(message);
    }

    public LocalRuntimeException(final Throwable cause) {
        super(cause);
    }

    public LocalRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
