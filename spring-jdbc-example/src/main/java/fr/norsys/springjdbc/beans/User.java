package fr.norsys.springjdbc.beans;

public class User {

    private int id;

    private String name;

    private String mail;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final int id) {
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
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(final String mail) {
        this.mail = mail;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", mail=" + mail + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id ^ (id >>> 32));
        result = prime * result + ((mail == null) ? 0 : mail.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (id != other.id) {
            return false;
        }
        if (mail == null) {
            if (other.mail != null) {
                return false;
            }
        } else if (!mail.equals(other.mail)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
