package fr.norsys.springexample.bytype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {

    private final BeanInterface<Integer> someService;

    private final BeanInterface<String> anotherService;

    /**
     * Les noms des paramètres ne correspondent à rien de connu dans la configuration
     * L'autowiring se fait donc par type : Spring recherche spécifiquement des classes finales implémentant
     * respectivement BeanInterface<Integer> et BeanInterface<String>
     */
    @Autowired
    public ServiceUser(final BeanInterface<Integer> someService, final BeanInterface<String> anotherService) {
        this.someService = someService;
        this.anotherService = anotherService;
    }

    /**
     * @return the someService
     */
    public BeanInterface<Integer> getSomeService() {
        return someService;
    }

    /**
     * @return the anotherService
     */
    public BeanInterface<String> getAnotherService() {
        return anotherService;
    }
}
