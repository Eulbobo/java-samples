package fr.norsys.springexample.evenmoreComplex.elements.service;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;
import fr.norsys.springexample.evenmoreComplex.ComplexConfiguration;

/**
 * On remarque dans le @ContextConfiguration une nouvelle information : classes=ComplexConfiguration.class
 * Cette configuration permet de déterminer la configuration à utiliser pour notre test
 * Ca peut permettre de mutualiser les configuration de test pour différentes classes à tester
 *
 * On est ici dans un test unitaire, on n'injecte pas les éléments : on mock !
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ComplexConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class AllImplementationServiceTest {

    @Test
    public void should_get_bean_in_all_repositories() {
        BeanSimpleRepositoryInterface mock1 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface mock2 = mock(BeanSimpleRepositoryInterface.class);

        AllImplementationService service = new AllImplementationService(asList(mock1, mock2));

        BeanSimple bean = service.getBean(0l);
        assertThat(bean)
            .isNull();

        verify(mock1).getById(0l);
        verify(mock2).getById(0l);

        verifyNoMoreInteractions(mock1, mock2);
    }

    @Test
    public void should_get_bean_from_last_repository() {
        BeanSimpleRepositoryInterface mock1 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface mock2 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface mock3 = mock(BeanSimpleRepositoryInterface.class);

        when(mock1.getById(anyLong())).thenReturn(new BeanSimple(1l, "mock1"));
        when(mock2.getById(anyLong())).thenReturn(new BeanSimple(2l, "mock2"));
        when(mock3.getById(anyLong())).thenReturn(new BeanSimple(3l, "mock3"));
        AllImplementationService service = new AllImplementationService(asList(mock1, mock2, mock3));

        BeanSimple bean = service.getBean(0l);
        assertThat(bean)
                .isNotNull()
                .isEqualToComparingFieldByField(new BeanSimple(3l, "mock3"));

        verify(mock1).getById(0l);
        verify(mock2).getById(0l);
        verify(mock3).getById(0l);

        verifyNoMoreInteractions(mock1, mock2, mock3);
    }

    @Test
    public void should_save_bean_in_all_repositories_and_change_name_when_doesnt_exist() {
        BeanSimpleRepositoryInterface mock1 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface mock2 = mock(BeanSimpleRepositoryInterface.class);

        AllImplementationService service = new AllImplementationService(asList(mock1, mock2));

        service.createOrUpdate(4l, "testValue");

        BeanSimple valueCreated = new BeanSimple(4l, "fromAllImplService");

        verify(mock1).getById(4l);
        verify(mock2).getById(4l);

        verify(mock1).save(refEq(valueCreated));
        verify(mock2).save(refEq(valueCreated));

        verifyNoMoreInteractions(mock1, mock2);
    }

    @Test
    public void should_save_bean_in_all_repositories_and_not_update_name_when_exist() {
        BeanSimpleRepositoryInterface mock1 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface mock2 = mock(BeanSimpleRepositoryInterface.class);

        when(mock1.getById(4l)).thenReturn(new BeanSimple(4l, "fromMock1"));
        when(mock2.getById(4l)).thenReturn(new BeanSimple(4l, "fromMock2"));

        AllImplementationService service = new AllImplementationService(asList(mock1, mock2));

        service.createOrUpdate(4l, "testValue");

        BeanSimple valueCreated = new BeanSimple(4l, "testValue");

        verify(mock1).getById(4l);
        verify(mock2).getById(4l);

        verify(mock1).save(refEq(valueCreated));
        verify(mock2).save(refEq(valueCreated));

        verifyNoMoreInteractions(mock1, mock2);
    }
}
