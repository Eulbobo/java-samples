package fr.norsys.aop.domain.bean;

import java.util.Date;

/**
 * Un simple pojo
 *
 * Et on remarque que malgré le mapping sur tous les éléments du package fr.norsys.aop, les appels des méthodes du bean
 * ne sont jamais appelées.
 * C'est un problème de visibilité : seuls les éléments existant en tant que bean Spring peuvent être vu par les aspects. Pour
 * voir ces appels, il faut rajouter @Component à la classe et faire en sorte que la récupération d'une nouvelle instance passe
 * par une factory Spring.
 */
public class User {

    private Long id;

    private String name;

    private Date birthDate;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }
}
