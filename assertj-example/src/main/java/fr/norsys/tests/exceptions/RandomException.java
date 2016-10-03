package fr.norsys.tests.exceptions;

public class RandomException extends RuntimeException {

    public RandomException(final String message) {
        super(message);
    }

    public RandomException(final Throwable cause) {
        super(cause);
    }

}
