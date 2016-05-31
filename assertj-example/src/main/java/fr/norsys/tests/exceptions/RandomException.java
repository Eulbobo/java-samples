package fr.norsys.tests.exceptions;

public class RandomException extends RuntimeException {

    public RandomException() {
        super();
    }

    public RandomException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RandomException(final String message) {
        super(message);
    }

    public RandomException(final Throwable cause) {
        super(cause);
    }

}
