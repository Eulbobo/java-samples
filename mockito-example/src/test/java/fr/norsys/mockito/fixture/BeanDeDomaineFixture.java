package fr.norsys.mockito.fixture;

import java.math.BigDecimal;

import fr.norsys.mockito.domain.BeanDeDomaine;
import fr.norsys.mockito.domain.EtatBeanDomain;

// cet objet est une fixture
// son rôle est de fournir rapidement des instances d'objets faciles à configurer
public class BeanDeDomaineFixture {

    private Long idBean;

    private String nomBean;

    private BigDecimal valeurBean;

    private EtatBeanDomain etatBean;

    private BeanDeDomaineFixture() {
        // private constructor to prevent instanciation
    }

    /**
     * Méthode statique d'instanciation de la fixture de base
     *
     * @return fixture
     */
    public static BeanDeDomaineFixture beanDeDomaine() {
        return new BeanDeDomaineFixture();
    }

    /**
     * Shortcut de fabrication rapide d'un élément connu
     *
     * @return Bean De domaine
     */
    public static BeanDeDomaine beanDeDomaineKnow() {
        return new BeanDeDomaineFixture()
                .withId(42l)
                .withEtat(EtatBeanDomain.A_TRAITER)
                .withNom("fast-fixture")
                .withValeur(BigDecimal.valueOf(5552))
                .build();
    }

    /**
     * Shortcut de fabrication rapide d'un nouvel element
     *
     * @return Bean De domaine
     */
    public static BeanDeDomaine beanDeDomaineNew() {
        return new BeanDeDomaineFixture()
                .withEtat(EtatBeanDomain.NOUVEAU)
                .withNom("fast-new")
                .withValeur(BigDecimal.valueOf(822))
                .build();
    }

    /**
     * Shortcut de fabrication rapide d'un nouvel element
     *
     * @return Bean De domaine
     */
    public static BeanDeDomaine beanDeDomaineTerminated() {
        return new BeanDeDomaineFixture()
                .withId(666l)
                .withEtat(EtatBeanDomain.TERMINE)
                .withNom("fast-terminated")
                .withValeur(BigDecimal.valueOf(55))
                .build();
    }

    /**
     * Point final : construction de la fixture
     *
     * @return BeanDeDomaine
     */
    public BeanDeDomaine build() {
        BeanDeDomaine bean = new BeanDeDomaine();
        bean.setEtatBean(etatBean);
        bean.setIdBean(idBean);
        bean.setNomBean(nomBean);
        bean.setValeurBean(valeurBean);
        return bean;
    }

    // Méthodes de construction de la fixture par champ
    public BeanDeDomaineFixture withId(final Long id) {
        this.idBean = id;
        return this;
    }

    public BeanDeDomaineFixture withNom(final String nom) {
        this.nomBean = nom;
        return this;
    }

    public BeanDeDomaineFixture withValeur(final BigDecimal valeur) {
        this.valeurBean = valeur;
        return this;
    }

    public BeanDeDomaineFixture withEtat(final EtatBeanDomain etat) {
        this.etatBean = etat;
        return this;
    }

}
