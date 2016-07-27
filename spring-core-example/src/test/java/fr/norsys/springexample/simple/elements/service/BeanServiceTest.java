package fr.norsys.springexample.simple.elements.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
    public void should_get_bean_when_getting_known_id(){
        BeanSimpleRepositoryInterface repository = mock(BeanSimpleRepositoryInterface.class);
        when(repository.getById(5L)).thenReturn(new BeanSimple(5L, "my bean"));

        BeanService service = new BeanService(repository);
        BeanSimple bean = service.getBean(5l);

        assertThat(bean).isNotNull()
            .isEqualToComparingFieldByField(new BeanSimple(5L, "my bean"));

        verify(repository).getById(anyLong());
    }

    @Test
    public void should_get_null_when_getting_unkown_id(){
        BeanSimpleRepositoryInterface repository = mock(BeanSimpleRepositoryInterface.class);
        when(repository.getById(5L)).thenReturn(new BeanSimple(5L, "my bean"));

        BeanService service = new BeanService(repository);
        BeanSimple bean = service.getBean(2l);

        assertThat(bean).isNull();
        verify(repository).getById(anyLong());
    }

}
