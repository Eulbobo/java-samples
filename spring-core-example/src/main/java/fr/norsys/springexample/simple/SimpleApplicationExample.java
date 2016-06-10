package fr.norsys.springexample.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleService;
import fr.norsys.springexample.simple.elements.service.BeanService;

public class SimpleApplicationExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleApplicationExample.class);

    public static void main(final String[] args) {

        // récupération du contexte via les annotations à partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext("fr.norsys.springexample.simple");

        // notre contexte est chargé
        LOGGER.info("context is loaded : {}", context);

        // récupération d'une classe annotée unique
        BeanSimpleService service = context.getBean(BeanService.class);
        LOGGER.info("On a le service : {}", service);

        // utilisation du service
        BeanSimple bean = service.getBean(0L);
        LOGGER.info("Bean reçu du service : {}", bean);

        // autre utilisation du service
        service.createOrUpdate(5L, "le nouveau");
    }

}
