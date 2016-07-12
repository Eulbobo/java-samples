package fr.norsys.springexample.bytype;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.norsys.springexample.bytype.service.BeanInterface;
import fr.norsys.springexample.bytype.service.ServiceUser;

public class ByTypeApplicationExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ByTypeApplicationExample.class);

    public static void main(final String[] args) {
        LOGGER.info("===========================================================");
        // récupération du contexte via les annotations à partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        LOGGER.info("===========================================================");
        // notre contexte est chargé
        LOGGER.info("context is loaded : {}", context);

        LOGGER.info("-----------------------------------------------------------");
        // exemple de récupération de tous les services
        Map<String, BeanInterface> beanInterfaces = context.getBeansOfType(BeanInterface.class);
        for (Entry<String, BeanInterface> entry : beanInterfaces.entrySet()){
            testRepo(entry.getValue());
        }

        LOGGER.info("-----------------------------------------------------------");
        // pruve de l'autowiring par type
        ServiceUser service = context.getBean(ServiceUser.class);
        testRepo(service.getSomeService());
        testRepo(service.getAnotherService());
    }

    /**
     * Quelques opérations avec le repository chargé
     * @param repository
     */
    private static <T> void testRepo(final BeanInterface<T> repository) {
        LOGGER.info("On a le repository : {}", repository);
        // utilisation du repo
        T bean = repository.getBean();
        LOGGER.info("Bean reçu du repository : {}", bean);
        // autre utilisation du repo
        repository.saveBean(bean);
    }

}
