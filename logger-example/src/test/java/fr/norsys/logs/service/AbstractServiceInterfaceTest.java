package fr.norsys.logs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.norsys.logs.InterfaceAvecDesMethodes;

/**
 * Ceci est un test paramétré qui va tester toutes les implémentations d'un service
 */
@RunWith(Parameterized.class)
public class AbstractServiceInterfaceTest {

    private final Class<AbstractServiceInterface> testedService;

    public AbstractServiceInterfaceTest(final Class<AbstractServiceInterface> testedService) {
        this.testedService = testedService;
    }

    /**
     * Paramètres indiquant les implémentations à tester
     */
    @Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[] { ServiceAvecDesLogs.class },
                new Object[] { ServiceAvecBeaucoupTropDeLog.class },
                new Object[] { ServiceAvecPasAssezDeLog.class });
    }

    /**
     * On passe des classes, donc on doit récupérer une instance => reflexion
     * A noter qu'ici, on considère toujours que les classes possèdent un paramètre.
     * Ce n'est pas forcement vrai...
     */
    private ServiceInterface getInterface(final InterfaceAvecDesMethodes mock) {
        ServiceInterface returnService = null;
        try {
            returnService = testedService.getConstructor(InterfaceAvecDesMethodes.class).newInstance(mock);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
        return returnService;
    }

    @Test
    public void should_get_a_string() {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.methodeDansInterface(anyInt())).thenReturn("42");

        ServiceInterface service = getInterface(mock);

        String result = service.maMethodeQuiRenvoieUnString();

        assertThat(result).isNotNull()
                .isEqualTo("42");
    }

    private static List<Object> resultListMock() {
        List<Object> resultList = new ArrayList<>();
        resultList.add("42");
        resultList.add("41");
        resultList.add("40");
        return resultList;
    }

    @Test
    public void should_have_object_42_for_id_BADABADA() {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.recuperationDesDonnees(anyString())).thenReturn(resultListMock());

        ServiceInterface service = getInterface(mock);

        boolean result = service.hasObjectForId("42", "BADABADA");

        assertThat(result).isTrue();
    }

    @Test
    public void should_not_have_object_0_for_id_BADABADA() {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.recuperationDesDonnees(anyString())).thenReturn(resultListMock());

        ServiceInterface service = getInterface(mock);

        boolean result = service.hasObjectForId("0", "BADABADA");

        assertThat(result).isFalse();
    }

    @Test
    public void validation_should_work_everytime_when_no_exception_is_thrown() {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.recuperationDesDonnees(anyString())).thenReturn(resultListMock());

        final ServiceInterface service = getInterface(mock);

        Throwable thrownByMethod = catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                service.validationDonneesForId("0");
            }
        });

        assertThat(thrownByMethod).isNull();
    }

    @Test
    public void validation_should_work_everytime_when_sqlexception_is_thrown() throws SQLException {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.recuperationDesDonnees(anyString())).thenReturn(resultListMock());

        doThrow(SQLException.class)
                .when(mock)
                .verificationDesDonnees(anyList());

        final ServiceInterface service = getInterface(mock);

        Throwable thrownByMethod = catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                service.validationDonneesForId("0");
            }
        });

        assertThat(thrownByMethod).isNull();
    }

}
