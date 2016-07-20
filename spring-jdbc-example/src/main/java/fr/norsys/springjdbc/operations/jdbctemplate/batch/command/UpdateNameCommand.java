package fr.norsys.springjdbc.operations.jdbctemplate.batch.command;

/**
 * Commande pour la mise à jour
 */
public class UpdateNameCommand {

    private final int id;

    private final String name;

    public UpdateNameCommand(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}
