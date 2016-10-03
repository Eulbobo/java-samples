package fr.norsys.springexample.domain;

/**
 * Un simple bean
 */
public class BeanSimple {

    private Long id;

    private String name;

    public BeanSimple(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BeanSimple [id=" + id + ", name=" + name + "]";
    }
}
