package fr.norsys.aop.domain.bean;

import java.util.Date;

/**
 * Un simple pojo
 *
 * Et on remarque que malgr� le mapping sur tous les �l�ments du package fr.norsys.aop, les appels des m�thodes du bean
 * ne sont jamais appel�es.
 * C'est un probl�me de visibilit� : seuls les �l�ments existant en tant que bean Spring peuvent �tre vu par les aspects. Pour
 * voir ces appels, il faut rajouter @Component � la classe et faire en sorte que la r�cup�ration d'une nouvelle instance passe
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
