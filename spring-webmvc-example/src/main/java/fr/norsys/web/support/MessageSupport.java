package fr.norsys.web.support;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

public class MessageSupport {

    private static final String UNKNOWN_MESSAGE_FORMAT = "???%s???";

    private final MessageSource messageSource;

    public MessageSupport(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(final Locale locale, final String code) {
        try {
            return messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e) {
            return getUnknownMessage(code);
        }
    }

    public String getMessage(final Locale locale, final String code, final Object param) {
        try {
            return messageSource.getMessage(code, new Object[] { param }, locale);
        } catch (NoSuchMessageException e) {
            return getUnknownMessage(code);
        }
    }

    public String getMessage(final Locale locale, final String code, final Object... params) {
        try {
            return messageSource.getMessage(code, params, locale);
        } catch (NoSuchMessageException e) {
            return getUnknownMessage(code);
        }
    }

    private static String getUnknownMessage(final String code) {
        return String.format(UNKNOWN_MESSAGE_FORMAT, code);
    }

}
