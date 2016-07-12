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
        // r�cup�ration du contexte via les annotations � partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext("fr.norsys.springexample.complex");
        LOGGER.info("===========================================================");
        // notre contexte est charg�
        LOGGER.info("context is loaded : {}", context);

        LOGGER.info("-----------------------------------------------------------");
        BeanSimpleRepositoryInterface repository = null;
        try {
            LOGGER.info("tentative de r�cup�ration d'un repository sans pr�ciser le nom");
            repository = context.getBean(BeanSimpleRepositoryInterface.class);
            isTrue(false, "Ca aurait du planter");
        } catch (Exception e) {
            LOGGER.error("Ca a foir�", e);
        }

        LOGGER.info("-----------------------------------------------------------");
        // r�cup�ration par le nom : celui de la m�thode dans la configuration
        repository = context.getBean("aSimpleImplementation", BeanSimpleRepositoryInterface.class);
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        // r�cup�ration par le nom : un autre dans la configuration
        repository = context.getBean("anotherSimpleImplementation", BeanSimpleRepositoryInterface.class);
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        // r�cup�ration par le nom : encore une fois, en castant, parce que �a marche aussi
        repository = (BeanSimpleRepositoryInterface) context.getBean("method_name_is_the_key_to_identify_bean");
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        // r�cup�ration par le nom : configur� sur l'annotation
        repository = context.getBean("fffff", BeanSimpleRepositoryInterface.class);
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        try {
            LOGGER.info("tentative de r�cup�ration d'un repository dont le nom n'existe pas");
            repository = context.getBean("SimpleRepository", BeanSimpleRepositoryInterface.class);
            isTrue(false, "Ca aurait du planter");
        } catch (Exception e) {
            LOGGER.error("Ca a foir�", e);
        }

        LOGGER.info("-----------------------------------------------------------");
        // vous savez quoi? Il existe une cinqui�me configuration implicite : simpleRepository
        repository = context.getBean("simpleRepository", BeanSimpleRepositoryInterface.class);
        LOGGER.info("On a le repository : {}", repository);
        testRepo(repository);

        LOGGER.info("-----------------------------------------------------------");
        // et un sixi�me !
        repository = context.getBean("anotherSimpleRepository", BeanSimpleRepositoryInterface.class);
        LOGGER.info("On a le repository : {}", repository);
        testRepo(repository);
    }

    /**
     * Quelques op�rations avec le repositoryu charg�
     * @param repository
     */
    private static void testRepo(final BeanSimpleRepositoryInterface repository) {
        LOGGER.info("On a le repository : {}", repository);
        // utilisation du repo
        BeanSimple bean = repository.getById(0L);
        LOGGER.info("Bean re�u du repository : {}", bean);

        bean.setId(5L);
        bean.setName("le nouveau");

        // autre utilisation du repo
        repository.save(bean);
    }

}
