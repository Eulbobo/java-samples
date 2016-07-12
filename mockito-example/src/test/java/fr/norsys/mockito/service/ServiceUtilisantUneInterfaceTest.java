package fr.norsys.mockito.service;

import static fr.norsys.mockito.fixture.BeanDeDomaineFixture.beanDeDomaine;
import static fr.norsys.mockito.fixture.BeanDeDomaineFixture.beanDeDomaineKnow;
import static fr.norsys.mockito.fixture.BeanDeDomaineFixture.beanDeDomaineNew;
import static fr.norsys.mockito.fixture.BeanDeDomaineFixture.beanDeDomaineTerminated;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.norsys.mockito.domain.BeanDeDomainRepository;
import fr.norsys.mockito.domain.BeanDeDomaine;
import fr.norsys.mockito.domain.DomainException;
import fr.norsys.mockito.domain.EtatBeanDomain;
import fr.norsys.mockito.service.ServiceException;
import fr.norsys.mockito.service.ServiceUtilisantUneInterface;

/**
 * La classe de test
 *
 * A noter qu'elle a été réalisée avant l'implémentation réelle de la classe ClasseUtilisantUneInterface
 */
public class ServiceUtilisantUneInterfaceTest {

    // tests à réaliser :

    // - aucune méthode ne doit renvoyer d'exception en mode de fonctionnement normal
    // - toutes les méthodes doivent renvoyer une exception si le paramètre passé est null

    // - la méthode do_things doit appeller la méthode do_things de l'interface

    // - si on recherche un bean par ID, on doit retrouver le bean ou null BeanDeDomaine getBeanById(long idBean);
    // - quand on veut créer un bean de domaine, on appelle le service et on récupère le bean avec son nouvel id void
    // createBean(BeanDeDomaine bean);

    // - Quand on passe un bean sur createOrUpdate, il appelle soit create, soit update selon la présence de l'id ou la
    // présence du bean en base
    // - Le bean créé doit être créé à l'état Nouveau quoi qui ait été indiqué dans le bean en paramètre
    // - Le service doit renvoyer une exception si on essaye de passer à un état inférieur dans le cycle de vie

    // - quand on veut supprimer un bean, on vérifie son état et renvoie une exception si l'état est déjà 'Termine',
    // sinon on le passe au service qui le passe à l'état terminé void deleteBean(BeanDeDomaine bean);

    @Test
    public void should_do_things_when_calling_dothings() {
        // ARRANGE
        // création d'un mock pour le repository
        BeanDeDomainRepository repository = Mockito.mock(BeanDeDomainRepository.class);

        // Création du service avec le repository
        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        // ACT
        service.doThings();

        // ASSERT
        // vérification que la méthode a été appelée. times(1) est par défaut si on ne le précise pas
        Mockito.verify(repository, Mockito.times(1)).doThings();
        // Même test que la ligne du dessus, mais avec les imports statiques et l'appel par défaut
        verify(repository).doThings();
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_return_null_when_trying_to_get_bean_with_unknown_id() {
        // ARRANGE
        // création d'un mock pour le repository
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        // quand on appelle la méthode avec un ID inconnu, on récupère une exception
        when(repository.getBeanById(Matchers.eq(0l)))
                .thenThrow(DomainException.class);

        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        // ACT
        // l'appel ne doit pas provoquer d'exception même si le service en renvoie une
        BeanDeDomaine resultBean = service.getById(0l);

        // ASSERT
        // vérification résultat avec assertj
        assertThat(resultBean)
                .isNull();

        // vérification appel au service avec le paramètre 0l
        verify(repository).getBeanById(0l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_fail_when_create_or_update_parameters_is_null() {
        // ARRANGE
        // création d'un mock pour le repository
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);

        // Création du service avec le repository
        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        // ACT
        Throwable thrownByException = null;
        try {
            service.createOrUpdate(null);
            failBecauseExceptionWasNotThrown(NullPointerException.class);
        } catch (Exception serviceException) {
            thrownByException = serviceException;
        }

        // ASSERT
        assertThat(thrownByException)
                .isNotNull()
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessageContaining("bean parameter is mandatory");
        // Le service ne doit pas avoir été appelé
        verifyZeroInteractions(repository);
    }

    @Test
    // ce test ressemble énormément au précédent.
    // on aurait pu le factoriser un peu plus, mais si le fonctionnement change pour un cas, on aura un seul test à
    // modifier
    // En java8, on aurait utilisé des lambdas
    public void should_fail_when_getById_parameters_is_null() {
        // ARRANGE
        // création d'un mock pour le repository
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);

        // Création du service avec le repository
        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        // ACT
        Throwable thrownByException = null;
        try {
            service.getById(null);

            failBecauseExceptionWasNotThrown(NullPointerException.class);
        } catch (Exception serviceException) {
            thrownByException = serviceException;
        }

        // ASSERT
        assertThat(thrownByException)
                .isNotNull()
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessageContaining("id parameter is mandatory");

        // Le service ne doit pas avoir été appelé
        verifyZeroInteractions(repository);
    }

    @Test
    public void should_get_proper_bean_when_trying_to_get_bean_with_known_id() {
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        when(repository.getBeanById(42l))
                .thenReturn(beanDeDomaineKnow());

        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        BeanDeDomaine bean = service.getById(42l);

        assertThat(bean)
                .isNotNull()
                .isExactlyInstanceOf(BeanDeDomaine.class)
                .isEqualsToByComparingFields(beanDeDomaineKnow());

        verify(repository).getBeanById(42l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_create_new_bean_when_no_id_is_given_to_create_or_update() {
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        BeanDeDomaine bean = beanDeDomaineNew();
        service.createOrUpdate(bean);

        verify(repository).createBean(any(BeanDeDomaine.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_get_etat_NOUVEAU_on_new_bean_even_if_previously_set_differently() {
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        BeanDeDomaine bean = beanDeDomaine()
                .withEtat(EtatBeanDomain.EN_COURS)
                .withNom("nom")
                .build();

        service.createOrUpdate(bean);

        BeanDeDomaine expectedBean = beanDeDomaine()
                .withEtat(EtatBeanDomain.NOUVEAU)
                .withNom("nom")
                .build();

        assertThat(bean)
                .isNotNull()
                .isEqualsToByComparingFields(expectedBean);

        verify(repository).createBean(any(BeanDeDomaine.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_update_bean_when_id_is_given_to_create_or_update() {
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        BeanDeDomaine bean = beanDeDomaineKnow();
        service.createOrUpdate(bean);

        verify(repository).updateBean(any(BeanDeDomaine.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_fail_when_we_try_to_incorrectly_update_bean_level() {
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        when(repository.getBeanById(666l)).thenReturn(beanDeDomaineTerminated());

        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        try {
            service.updateEtat(Long.valueOf(666l), EtatBeanDomain.NOUVEAU);

            failBecauseExceptionWasNotThrown(ServiceException.class);
        } catch (ServiceException serviceException) {
            assertThat(serviceException)
                    .hasMessageContaining("can't go from TERMINE to NOUVEAU");
        }

        verify(repository).getBeanById(666l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_success_when_we_try_to_correctly_update_bean_level() throws ServiceException {
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        when(repository.getBeanById(42l)).thenReturn(beanDeDomaineKnow());

        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        // on n'attend pas d'exception, donc on ne catch pas
        service.updateEtat(Long.valueOf(42), EtatBeanDomain.TERMINE);

        verify(repository).getBeanById(42l);
        verify(repository).updateBean(any(BeanDeDomaine.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_success_deleting_bean_when_state_is_Terminated() throws ServiceException {
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        BeanDeDomaine bean = beanDeDomaineTerminated();
        service.deleteBean(bean);

        verify(repository).deleteBean(any(BeanDeDomaine.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void should_fail_when_trying_to_delete_non_Terminated_bean() {
        BeanDeDomainRepository repository = mock(BeanDeDomainRepository.class);
        ServiceUtilisantUneInterface service = new ServiceUtilisantUneInterface(repository);

        BeanDeDomaine bean = beanDeDomaineKnow();

        Throwable thrownByException = null;
        try {
            service.deleteBean(bean);

            failBecauseExceptionWasNotThrown(ServiceException.class);
        } catch (ServiceException serviceException) {
            thrownByException = serviceException;
        }

        assertThat(thrownByException)
                .isNotNull()
                .isExactlyInstanceOf(ServiceException.class)
                .hasMessageContaining("should be TERMINATED before trying to delete");

        // Le service ne doit pas avoir été appelé
        verifyZeroInteractions(repository);
    }



    // Il manque des tests, et donc il y aura des bugs en l'état : saurez-vous trouver lesquels?
}
