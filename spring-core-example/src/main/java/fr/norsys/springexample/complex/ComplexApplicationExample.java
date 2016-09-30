package fr.norsys.springexample.complex;

import static org.springframework.util.Assert.isTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

public class ComplexApplicationExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComplexApplicationExample.class);

    public static void main(final String[] args) {
        LOGGER.info("===========================================================");
        // récupération du contexte via les annotations à partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext("fr.norsys.springexample.complex");
        LOGGER.info("===========================================================");
        // notre contexte est chargé
        LOGGER.info("context is loaded : {}", context);

        LOGGER.info("-----------------------------------------------------------");
        BeanSimpleRepositoryInterface repository = null;
        try {
            LOGGER.info("tentative de r\u00e9cup\u00e9ration d'un repository sans pr\u00e9ciser le nom");
            repository = context.getBean(BeanSimpleRepositoryInterface.class);
            isTrue(false, "Ca aurait du planter");
        } catch (Exception e) {
            LOGGER.error("Ca a foir\u00e9", e);
        }

        LOGGER.info("-----------------------------------------------------------");
        // récupération par le nom : celui de la méthode dans la configuration
        repository = context.getBean("aSimpleImplementation", BeanSimpleRepositoryInterface.class);
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        // récupération par le nom : un autre dans la configuration
        repository = context.getBean("anotherSimpleImplementation", BeanSimpleRepositoryInterface.class);
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        // récupération par le nom : encore une fois, en castant, parce que ça marche aussi
        repository = (BeanSimpleRepositoryInterface) context.getBean("method_name_is_the_key_to_identify_bean");
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        // récupération par le nom : configuré sur l'annotation
        repository = context.getBean("fffff", BeanSimpleRepositoryInterface.class);
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        try {
            LOGGER.info("tentative de r\u00e9cup\u00e9ration d'un repository dont le nom n'existe pas");
            repository = context.getBean("SimpleRepository", BeanSimpleRepositoryInterface.class);
            isTrue(false, "Ca aurait du planter");
        } catch (Exception e) {
            LOGGER.error("Ca a foir\u00e9", e);
        }

        LOGGER.info("-----------------------------------------------------------");
        // vous savez quoi? Il existe une cinquiéme configuration implicite : simpleRepository
        repository = context.getBean("simpleRepository", BeanSimpleRepositoryInterface.class);
        LOGGER.info("On a le repository : {}", repository);
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        // et un sixiéme !
        repository = context.getBean("anotherSimpleRepository", BeanSimpleRepositoryInterface.class);
        LOGGER.info("On a le repository : {}", repository);
        testRepo(repository);
    }

    /**
     * Quelques opérations avec le repositoryu chargé
     * @param repository
     */
    private static void testRepo(final BeanSimpleRepositoryInterface repository) {
        LOGGER.info("On a le repository : {}", repository);
        // utilisation du repo
        BeanSimple bean = repository.getById(0L);
        LOGGER.info("Bean re\u00e7u du repository : {}", bean);

        bean.setId(5L);
        bean.setName("le nouveau");

        // autre utilisation du repo
        repository.save(bean);
    }

}
