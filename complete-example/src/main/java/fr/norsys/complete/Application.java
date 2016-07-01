package fr.norsys.complete;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) throws SQLException {
        LOGGER.info("===========================================================");
        // récupération du contexte via les annotations à partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        LOGGER.info("===========================================================");
        // notre contexte est chargé
        LOGGER.info("context is loaded : {}", context);

        LOGGER.info("-----------------------------------------------------------");

        DataSource dataSource = context.getBean(DataSource.class);

        LOGGER.info("Datasource : {}, connection : {}", dataSource, dataSource.getConnection().isClosed());
    }
}
