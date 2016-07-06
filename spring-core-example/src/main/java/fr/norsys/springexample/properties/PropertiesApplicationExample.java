package fr.norsys.springexample.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.norsys.springexample.properties.elements.service.ServiceWithProperties;

public class PropertiesApplicationExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesApplicationExample.class);

    public static void main(final String[] args) {
        LOGGER.info("===========================================================");
        // r�cup�ration du contexte via les annotations � partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext("fr.norsys.springexample.properties");
        LOGGER.info("===========================================================");
        // notre contexte est charg�
        LOGGER.info("context is loaded : {}", context);

        LOGGER.info("-----------------------------------------------------------");
        // r�cup�ration d'une classe annot�e unique
        ServiceWithProperties service = context.getBean(ServiceWithProperties.class);
        LOGGER.info("On a le service : {}", service);

        LOGGER.info("-----------------------------------------------------------");
        LOGGER.info("Value du bean : {}", service.getValue());
        LOGGER.info("Long line du bean : {}", service.getLongLine());
        LOGGER.info("Custom line du bean : {}", service.getCustomLine());
        LOGGER.info("PropertyWithDefaultValue du bean : {}", service.getPropertyWithDefaultValue());
    }

}
