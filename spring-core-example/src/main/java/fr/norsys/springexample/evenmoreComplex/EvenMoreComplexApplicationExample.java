package fr.norsys.springexample.evenmoreComplex;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.domain.BeanSimpleService;
import fr.norsys.springexample.evenmoreComplex.elements.service.BasicService;
import fr.norsys.springexample.evenmoreComplex.elements.service.ComplexService;

public class EvenMoreComplexApplicationExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvenMoreComplexApplicationExample.class);

    public static void main(final String[] args) {
        LOGGER.info("===========================================================");
        // récupération du contexte via les annotations à partir d'un package
        ApplicationContext context = new AnnotationConfigApplicationContext("fr.norsys.springexample.evenmoreComplex");
        LOGGER.info("===========================================================");
        // notre contexte est chargé
        LOGGER.info("context is loaded : {}", context);

        // utilisation d'un repository (appel avec le nom)
        BeanSimpleRepositoryInterface firstRepo = context.getBean("firstRepo", BeanSimpleRepositoryInterface.class);
        firstRepo.getById(0l);
        LOGGER.info("-----------------------------------------------------------");

        // utilisation d'un autre repository (appel avec le nom)
        BeanSimpleRepositoryInterface secondRepo = context.getBean("secondRepo", BeanSimpleRepositoryInterface.class);
        secondRepo.getById(0l);

        LOGGER.info("-----------------------------------------------------------");
        // utilisation d'un autre repository (appel avec le nom)
        Map<String, BeanSimpleRepositoryInterface> repositories = context
                .getBeansOfType(BeanSimpleRepositoryInterface.class);
        for (Entry<String, BeanSimpleRepositoryInterface> entry : repositories.entrySet()) {
            LOGGER.info("Repository de nom {} avec l'implémentation {}", entry.getKey(), entry.getValue());
        }

        LOGGER.info("-----------------------------------------------------------");
        // Utilisation d'un service simple via classe
        BeanSimpleService simpleService = context.getBean(BasicService.class);
        doStuffWithService(simpleService);

        LOGGER.info("-----------------------------------------------------------");
        BeanSimpleService complexService = context.getBean(ComplexService.class);
        doStuffWithService(complexService);

        LOGGER.info("-----------------------------------------------------------");
        BeanSimpleService allImplService = context.getBean("allImplementationService", BeanSimpleService.class);
        doStuffWithService(allImplService);
    }

    private static void doStuffWithService(final BeanSimpleService service) {
        service.getBean(0l);
        service.createOrUpdate(5l, "simpleBeanSave");
    }

}
