package fr.norsys.springexample.evenmoreComplex.elements.service;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ComplexConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class ComplexServiceTest {

    @Test
    public void should_get_null_bean_when_repository_sends_null_bean() {
        BeanSimpleRepositoryInterface repo1 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface repo2 = mock(BeanSimpleRepositoryInterface.class);
        ComplexService service = new ComplexService(repo1, repo2);

        BeanSimple bean = service.getBean(0l);

        assertThat(bean)
                .isNull();
    }

    public void should_get_bean_returned_by_first_repository() {
        BeanSimpleRepositoryInterface repo1 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface repo2 = mock(BeanSimpleRepositoryInterface.class);

        when(repo1.getById(anyLong())).thenReturn(new BeanSimple(1l, "returned by repo1"));
        when(repo2.getById(anyLong())).thenReturn(new BeanSimple(2l, "returned by repo2"));

        ComplexService service = new ComplexService(repo1, repo2);

        BeanSimple bean = service.getBean(0l);

        assertThat(bean)
                .isNotNull()
                .isEqualsToByComparingFields(new BeanSimple(1l, "returned by repo1"));
    }

    @Test
    public void should_save_bean_in_all_repositories_and_change_name_when_doesnt_exist() {
        BeanSimpleRepositoryInterface repo1 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface repo2 = mock(BeanSimpleRepositoryInterface.class);
        ComplexService service = new ComplexService(repo1, repo2);

        service.createOrUpdate(4l, "testValue");

        BeanSimple valueCreated = new BeanSimple(4l, "fromComplexService");

        verify(repo1).getById(4l);
        verify(repo2).getById(4l);
        verify(repo1).save(refEq(valueCreated));
        verify(repo2).save(refEq(valueCreated));
        verifyNoMoreInteractions(repo1, repo2);
    }

    @Test
    public void should_save_bean_in_all_repositories_and_not_update_name_when_exist() {
        BeanSimpleRepositoryInterface repo1 = mock(BeanSimpleRepositoryInterface.class);
        BeanSimpleRepositoryInterface repo2 = mock(BeanSimpleRepositoryInterface.class);

        when(repo1.getById(4l)).thenReturn(new BeanSimple(4l, "fromMock1"));
        when(repo2.getById(4l)).thenReturn(new BeanSimple(4l, "fromMock2"));

        ComplexService service = new ComplexService(repo1, repo2);

        service.createOrUpdate(4l, "testValue");

        BeanSimple valueCreated = new BeanSimple(4l, "testValue");

        // only first element is get from repositories
        verify(repo1).getById(4l);
        verify(repo1).save(refEq(valueCreated));
        verify(repo2).save(refEq(valueCreated));
        verifyNoMoreInteractions(repo1, repo2);
    }
}
