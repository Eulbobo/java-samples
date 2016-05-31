package fr.norsys.tests.exceptions;

public class DiceException extends RandomException {

    public DiceException() {
        super();
    }

    public DiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DiceException(final String message) {
        super(message);
    }

    public DiceException(final Throwable cause) {
        super(cause);
    }

}
