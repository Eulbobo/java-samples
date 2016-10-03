package fr.norsys.springexample.services.provider;

import fr.norsys.springexample.complex.elements.repository.SimpleRepository;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

public class BeanSimpleRepositoryFactory {

    public static BeanSimpleRepositoryInterface getInstance(final Class<BeanSimpleRepositoryInterface> clazz) {
        if ("SimpleRepository".equals(clazz.getSimpleName())) {
            return new SimpleRepository(Integer.valueOf(2));
        }
        return getImplementation(clazz);
    }

    private static BeanSimpleRepositoryInterface getImplementation(final Class<BeanSimpleRepositoryInterface> clazz) {
        BeanSimpleRepositoryInterface returnService = null;
        try {
            returnService = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
            throw new RuntimeException(e);
        }
        return returnService;
    }
}
