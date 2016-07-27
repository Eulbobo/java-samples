package fr.norsys.aop.support;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe qui permet d'attendre simplement
 */
public class ThreadWait {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadWait.class);

    public static void sleep(final long millis){
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            LOGGER.error("Error while waiting", e);
        }
    }

    public static void sleep(){
        sleep(ThreadLocalRandom.current().nextInt(100));
    }
}
