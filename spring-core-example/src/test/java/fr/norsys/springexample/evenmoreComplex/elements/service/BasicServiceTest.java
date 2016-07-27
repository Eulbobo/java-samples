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
public class BasicServiceTest {

    @Test
    public void should_get_null_bean_when_repository_sends_null_bean() {
        BeanSimpleRepositoryInterface repo = mock(BeanSimpleRepositoryInterface.class);
        BasicService service = new BasicService(repo);

        BeanSimple bean = service.getBean(0l);

        assertThat(bean)
                .isNull();
    }

    public void should_only_get_the_bean_sent_by_repository() {
        BeanSimpleRepositoryInterface repo = mock(BeanSimpleRepositoryInterface.class);
        when(repo.getById(anyLong())).thenReturn(new BeanSimple(4l, "returned"));

        BasicService service = new BasicService(repo);

        BeanSimple bean = service.getBean(0l);

        assertThat(bean)
                .isNotNull()
                .isEqualToComparingFieldByField(new BeanSimple(4l, "returned"));
    }

    @Test
    public void should_save_bean_in_all_repositories_and_change_name_when_doesnt_exist() {
        BeanSimpleRepositoryInterface repo = mock(BeanSimpleRepositoryInterface.class);

        BasicService service = new BasicService(repo);

        service.createOrUpdate(4l, "testValue");

        BeanSimple valueCreated = new BeanSimple(4l, "fromBasicService");

        verify(repo).getById(4l);
        verify(repo).save(refEq(valueCreated));
        verifyNoMoreInteractions(repo);
    }

    @Test
    public void should_save_bean_in_all_repositories_and_not_update_name_when_exist() {
        BeanSimpleRepositoryInterface repo = mock(BeanSimpleRepositoryInterface.class);

        when(repo.getById(4l)).thenReturn(new BeanSimple(4l, "fromMock"));

        BasicService service = new BasicService(repo);

        service.createOrUpdate(4l, "testValue");

        BeanSimple valueCreated = new BeanSimple(4l, "testValue");

        verify(repo).getById(4l);
        verify(repo).save(refEq(valueCreated));
        verifyNoMoreInteractions(repo);
    }
}
