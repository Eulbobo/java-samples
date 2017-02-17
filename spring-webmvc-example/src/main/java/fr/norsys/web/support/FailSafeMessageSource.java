package fr.norsys.web.support;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

/**
 * Fail safe message source
 * <p>
 * Société : CNAMTS
 * </p>
 * @author NORSYS
 */
public class FailSafeMessageSource implements MessageSource {

    private static final String UNKNOWN_MESSAGE_FORMAT = "???%s???";

    private final MessageSource source;

    public FailSafeMessageSource(final MessageSource source){
        this.source = source;
    }

    @Override
    public String getMessage(final String code, final Object[] args, final String defaultMessage, final Locale locale) {
        try {
            return source.getMessage(code, args, defaultMessage, locale);
        } catch (NoSuchMessageException e) {
            return getUnknownMessage(code);
        }
    }

    @Override
    public String getMessage(final String code, final Object[] args, final Locale locale) throws NoSuchMessageException {
        try {
            return source.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            return getUnknownMessage(code);
        }
    }

    @Override
    public String getMessage(final MessageSourceResolvable resolvable, final Locale locale) throws NoSuchMessageException {
        try {
            return source.getMessage(resolvable, locale);
        } catch (NoSuchMessageException e) {
            return getUnknownMessages(resolvable.getCodes());
        }
    }

    private static String getUnknownMessages(final String... codes) {
        StringBuilder sb = new StringBuilder();
        for (String code : codes){
            sb.append(getUnknownMessage(code));
            sb.append('\n');
        }
        return sb.toString();
    }

    private static String getUnknownMessage(final String code) {
        return String.format(UNKNOWN_MESSAGE_FORMAT, code);
    }

}
