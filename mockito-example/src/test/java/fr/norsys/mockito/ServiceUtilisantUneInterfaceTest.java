package fr.norsys.mockito;

import org.junit.Test;


/**
 * La classe de test
 *
 * A noter qu'elle a été réalisée avant l'implémentation réelle de la classe ClasseUtilisantUneInterface
 */
public class ServiceUtilisantUneInterfaceTest {

    // tests à réaliser :

    // - aucune méthode ne doit renvoyer d'exception en mode de fonctionnement normal
    // - toutes les méthodes doivent renvoyer une exception si le paramètre passé est null

    // - si on recherche un bean par ID, on doit retrouver le bean ou null BeanDeDomaine getBeanById(long idBean);
    // - quand on veut créer un bean de domaine, on appelle le service et on récupère le bean avec son nouvel id void createBean(BeanDeDomaine bean);


    // - Quand on passe un bean sur createOrUpdate, il appelle soit create, soit update selon la présence de l'id ou la présence du bean en base
    // - Le bean créé doit être créé à l'état Nouveau quoi qui ait été indiqué dans le bean en paramètre
    // - Le service doit renvoyer une exception si on essaye de passer à un état inférieur dans le cycle de vie


    // - quand on veut supprimer un bean, on vérifie son état et renvoie une exception si l'état est déjà 'Termine',
    // sinon on le passe au service qui le passe à l'état terminé void deleteBean(BeanDeDomaine bean);

    @Test
    public void should_return_null_when_trying_to_get_bean_with_unknown_id() {

    }

    @Test
    public void should_get_proper_bean_when_trying_to_get_bean_with_known_id() {

    }

    @Test
    public void should_fail_when_create_or_update_parameters_are_empty() {

    }

    @Test
    public void should_create_new_bean_when_no_id_is_given_to_create_or_update() {

    }

    @Test
    public void should_get_etat_NOUVEAU_on_new_bean_even_if_previously_set_differently() {

    }

    @Test
    public void should_update_bean_when_id_is_given_to_create_or_update() {

    }

    @Test
    public void should_fail_when_we_try_to_incorrectly_update_bean_level() {

    }

    @Test
    public void should_fail_when_we_trying_to_delete_Terminated_bean() {

    }

    @Test
    public void should_only_get_bean_by_etat() {

    }

}
