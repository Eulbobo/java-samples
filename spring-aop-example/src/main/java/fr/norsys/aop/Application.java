package fr.norsys.aop;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import fr.norsys.aop.domain.exception.DomainException;
import fr.norsys.aop.domain.service.UserService;

/**
 * Point d'entrée de l'application pour tester les aspects
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) {
        LOGGER.info("===========================================================");
        // récupération du contexte via les annotations à partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
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
        }

        try {
            service.saveOrUpdate(null);
        } catch (SQLException e) {
            LOGGER.info("On ne passera jamais par ici...");
            Assert.state(false);
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
