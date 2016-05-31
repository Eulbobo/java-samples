package fr.norsys.exceptions.checked;


public class LocalException extends Exception {

    private static final long serialVersionUID = -6355819581126237272L;

    public LocalException() {
        super();
    }

    public LocalException(final String message) {
        super(message);
    }

    public LocalException(final Throwable cause) {
        super(cause);
    }

    public LocalException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
