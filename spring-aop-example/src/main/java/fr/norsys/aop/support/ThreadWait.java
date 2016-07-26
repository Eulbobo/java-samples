package fr.norsys.aop.support;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadWait {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadWait.class);

    private static final Random RNG = new Random();

    public static void sleep(final long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOGGER.error("Error while waiting", e);
        }
    }

    public static void sleep(){
        sleep(RNG.nextInt(100));
    }
}
