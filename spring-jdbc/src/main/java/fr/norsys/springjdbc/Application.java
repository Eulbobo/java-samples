package fr.norsys.springjdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import fr.norsys.springjdbc.operations.User;
import fr.norsys.springjdbc.operations.Users;

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

        Users userService = context.getBean(Users.class);
        try {
            displayRowCount(userService);

            showAllElements(userService);

            getUniqueUserById(userService);

            showAllElements2(userService);

            getUserById(userService);
        } catch (Exception e) {
            LOGGER.error("Ca a pété, ", e);
        }
        // fermeture de la base embarquée
        dataSource.shutdown();
    }

    private static void showAllElements2(final Users userService) {
        List<User> elements = userService.getAllUsers();
        for (User user : elements) {
            LOGGER.info("User récupéré : {}", user);
        }
    }

    private static void getUniqueUserById(final Users userService) {
        User user = userService.getUniqueUserById(1L);
        LOGGER.info("On récupère un user pas null : {}", user);

        try {
            user = userService.getUniqueUserById(0L);
            throw new IllegalStateException("Ca aurait du péter");
        } catch (EmptyResultDataAccessException erdae) {
            LOGGER.info("Voilà, ça a pété", erdae);
        }
    }

    private static void getUserById(final Users userService) {
        LOGGER.info("On récupère un user pas null : {}", userService.getFirstUser());
        // User userNull = userService.getUserById(0L);
        // LOGGER.info("On récupère un user null : {}", userNull);

        User user = userService.getUserById(1L);
        LOGGER.info("On récupère un user pas null : {}", user);
    }

    private static void showAllElements(final Users userService) {
        List<Map<String, Object>> elements = userService.getAllElements();
        for (Map<String, Object> line : elements) {
            LOGGER.info("Nouvelle ligne");
            displayLine(line);
        }
    }

    private static void displayLine(final Map<String, Object> line) {
        for (Entry<String, Object> entry : line.entrySet()) {
            LOGGER.info("Clé >{}< valeur >{}<", entry.getKey(), entry.getValue());
        }
    }

    private static void displayRowCount(final Users userService) {
        LOGGER.info("Nombre d'éléments en base : {}", userService.rowCountInTable());
    }
}
