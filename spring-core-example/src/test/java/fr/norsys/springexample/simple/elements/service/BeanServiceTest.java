package fr.norsys.springexample.simple.elements.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;

import fr.norsys.springexample.domain.BeanSimple;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

/**
 * Ceci est un test unitaire qui permet de valider le fonctionnement standard de la classe
 * On mock et on lance de manière standard
 */
public class BeanServiceTest {

    @Test
    public void should_get_bean_when_getting_known_id() {
        BeanSimpleRepositoryInterface repository = mock(BeanSimpleRepositoryInterface.class);
        when(repository.getById(5L)).thenReturn(new BeanSimple(5L, "my bean"));

        BeanService service = new BeanService(repository);
        BeanSimple bean = service.getBean(5l);

        assertThat(bean).isNotNull()
                .isEqualToComparingFieldByField(new BeanSimple(5L, "my bean"));

        verify(repository).getById(anyLong());
    }

    @Test
    public void should_get_null_when_getting_unkown_id() {
        BeanSimpleRepositoryInterface repository = mock(BeanSimpleRepositoryInterface.class);
        when(repository.getById(5L)).thenReturn(new BeanSimple(5L, "my bean"));

        BeanService service = new BeanService(repository);
        BeanSimple bean = service.getBean(2l);

        assertThat(bean).isNull();
        verify(repository).getById(anyLong());
    }

    @Test
    public void should_save_bean_and_change_name_when_doesnt_exist() {
        BeanSimpleRepositoryInterface mock = mock(BeanSimpleRepositoryInterface.class);

        BeanService service = new BeanService(mock);
        service.createOrUpdate(4l, "testValue");

        BeanSimple valueCreated = new BeanSimple(4l, "fromService");
        verify(mock).getById(4l);
        verify(mock).save(refEq(valueCreated));
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_save_bean_and_not_update_name_when_exist() {
        BeanSimpleRepositoryInterface mock = mock(BeanSimpleRepositoryInterface.class);
        when(mock.getById(4l)).thenReturn(new BeanSimple(4l, "fromMock"));

        BeanService service = new BeanService(mock);
        service.createOrUpdate(4l, "testValue");

        BeanSimple valueCreated = new BeanSimple(4l, "testValue");
        verify(mock).getById(4l);
        verify(mock).save(refEq(valueCreated));
        verifyNoMoreInteractions(mock);
    }

}
