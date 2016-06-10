package fr.norsys.springexample.evenmoreComplex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EvenMoreComplexApplicationExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvenMoreComplexApplicationExample.class);

    public static void main(final String[] args) {

        // récupération du contexte via les annotations à partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext("fr.norsys.springexample.evenmoreComplex");

        // notre contexte est chargé
        LOGGER.info("context is loaded : {}", context);


        // TODO exemples services/repositories

    }



}
