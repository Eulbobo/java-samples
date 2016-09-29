package fr.norsys.aop;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.norsys.aop.domain.exception.DomainException;
import fr.norsys.aop.domain.service.UserService;
import fr.norsys.configuration.ApplicationConfigurationWithAspect;
import fr.norsys.configuration.ApplicationConfigurationWithoutAspect;

/**
 * Point d'entrée de l'application pour tester les aspects
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) {
        LOGGER.info("===================================================================");
        LOGGER.info("====== Lancement sans les aspects branch\u00e9s dans le contexte =======");
        LOGGER.info("===================================================================");

        launchWithContext(new AnnotationConfigApplicationContext(ApplicationConfigurationWithoutAspect.class));

        LOGGER.info("===================================================================");
        LOGGER.info("====== Lancement avec les aspects branch\u00e9s dans le contexte =======");
        LOGGER.info("===================================================================");
        launchWithContext(new AnnotationConfigApplicationContext(ApplicationConfigurationWithAspect.class));
    }

    private static void launchWithContext(final ApplicationContext context) {
        LOGGER.info("===========================================================");
        // notre contexte est chargé
        LOGGER.info("context is loaded : {}", context);

        LOGGER.info("-----------------------------------------------------------");

        UserService service = context.getBean(UserService.class);

        LOGGER.info("get user from ID");
        LOGGER.info("-----------------------------------------------------------");
        service.getFromId(Long.valueOf(12));
        LOGGER.info("-----------------------------------------------------------");
        service.getFromId(15L);
        LOGGER.info("-----------------------------------------------------------");

        LOGGER.info("get user from ID and name");
        LOGGER.info("-----------------------------------------------------------");
        service.getFromIdAndName(22L, "other name");
        LOGGER.info("-----------------------------------------------------------");

        LOGGER.info("find all");
        LOGGER.info("-----------------------------------------------------------");
        service.findAll();
        LOGGER.info("-----------------------------------------------------------");

        try {
            service.delete(null);
        } catch (DomainException e) {
            LOGGER.info("On récupère une exception de type {} ", e.getClass());
        } catch (UnsupportedOperationException e) {
            LOGGER.info("On ne passera jamais par ici avec les aspect branch\u00e9...");
        }

        try {
            service.saveOrUpdate(null);
        } catch (SQLException e) {
            LOGGER.info("On ne passera jamais par ici avec les aspect branch\u00e9...");
        } catch (DomainException e) {
            LOGGER.info("On récupère une exception de type {} ", e.getClass());
        }

        try {
            service.thisWillFailMiserabily();
        } catch (Exception e) {
            LOGGER.info("On récupère une exception de type {} ", e.getClass());
        }
    }
}
