package fr.norsys.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.norsys.aop.domain.service.UserService;

/**
 * Point d'entr�e de l'application pour tester les aspects
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) {
        LOGGER.info("===========================================================");
        // r�cup�ration du contexte via les annotations � partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        LOGGER.info("===========================================================");
        // notre contexte est charg�
        LOGGER.info("context is loaded : {}", context);

        LOGGER.info("-----------------------------------------------------------");

        UserService service = context.getBean(UserService.class);

        LOGGER.info("get user from ID");
        service.getFromId(Long.valueOf(12));

        LOGGER.info("find all");
        service.findAll();
    }
}