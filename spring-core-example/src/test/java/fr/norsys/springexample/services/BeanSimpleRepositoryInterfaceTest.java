package fr.norsys.springexample.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Arrays;
import java.util.Collection;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.norsys.springexample.complex.elements.repository.AnotherSimpleRepository;
import fr.norsys.springexample.complex.elements.repository.FakeRepository;
import fr.norsys.springexample.complex.elements.repository.SimpleRepository;
import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.evenmoreComplex.elements.repository.FirstRepo;
import fr.norsys.springexample.evenmoreComplex.elements.repository.SecondRepo;
import fr.norsys.springexample.evenmoreComplex.elements.repository.ThirdRepo;
import fr.norsys.springexample.simple.elements.repository.DummyRepository;

/**
 * Ceci est un test paramétré qui va tester toutes les implémentations d'un service
 */
@RunWith(Parameterized.class)
public class BeanSimpleRepositoryInterfaceTest {

    private final Class<BeanSimpleRepositoryInterface> testedService;

    public BeanSimpleRepositoryInterfaceTest(final Class<BeanSimpleRepositoryInterface> testedService) {
        this.testedService = testedService;
    }

    /**
     * Paramètres indiquant les implémentations à tester
     */
    @Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[] { AnotherSimpleRepository.class },
                new Object[] { DummyRepository.class },
                new Object[] { FakeRepository.class },
                new Object[] { FirstRepo.class },
                new Object[] { SecondRepo.class },
                new Object[] { ThirdRepo.class },
                new Object[] { SimpleRepository.class }
                );
    }

    /**
     * On passe des classes, donc on doit récupérer une instance => reflexion
     */
    private BeanSimpleRepositoryInterface getInterface() {
        BeanSimpleRepositoryInterface returnService = null;
        try {
            returnService = testedService.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
            throw new RuntimeException(e);
        }
        return returnService;
    }

    @Test
    public void should_get_bean_when_asking_for_id_8() {
        BeanSimpleRepositoryInterface repo = new DummyRepository();
        BeanSimple bean = repo.getById(Long.valueOf(8l));

        assertThat(bean).isNotNull()
                .hasFieldOrPropertyWithValue("id", Long.valueOf(8l))
                .hasFieldOrPropertyWithValue("name", "8");
    }

    @Test
    public void should_save_bean_properly() {
        final BeanSimpleRepositoryInterface repo = new DummyRepository();

        final BeanSimple bean = new BeanSimple(Long.valueOf(1l), "one");

        Throwable thrownByMethod = catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                repo.save(bean);
            }
        });

        assertThat(thrownByMethod).isNull();
    }

}
