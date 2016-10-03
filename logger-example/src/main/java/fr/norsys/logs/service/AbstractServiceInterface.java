package fr.norsys.logs.service;

import fr.norsys.logs.InterfaceAvecDesMethodes;

public abstract class AbstractServiceInterface implements ServiceInterface {

    protected InterfaceAvecDesMethodes repository;

    protected AbstractServiceInterface(final InterfaceAvecDesMethodes interfaceAvecDesMethodes){
        this.repository = interfaceAvecDesMethodes;
    }
}
