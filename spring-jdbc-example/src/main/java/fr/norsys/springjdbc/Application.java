package fr.norsys.springjdbc;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

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

        EmbeddedDatabase dataSource = context.getBean(EmbeddedDatabase.class);

        LOGGER.info("Datasource : {}, connection : {}", dataSource, dataSource.getConnection().isClosed());


        // fermeture de la base embarquée
        dataSource.shutdown();
    }


}
