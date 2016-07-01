package fr.norsys.complete.domain.bo;

import java.util.Date;

public class Artist {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final Date birthDate;

    private final Date deathDate;

    public Artist(final Long id, final String firstName, final String lastName, final Date birthDate,
            final Date deathDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @return the deathDate
     */
    public Date getDeathDate() {
        return deathDate;
    }

}
