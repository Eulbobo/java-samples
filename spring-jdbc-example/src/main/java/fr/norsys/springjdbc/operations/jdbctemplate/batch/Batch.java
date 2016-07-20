package fr.norsys.springjdbc.operations.jdbctemplate.batch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.jdbctemplate.batch.command.UpdateNameCommand;

/**
 * Exemples d'utilisation de traitements de masse
 */
@Repository
public class Batch {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Batch(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Exemple d'utilisation du ParameterizedPreparedStatementSetter
     * Son intérêt et de pouvoir utiliser des objets typés directement
     *
     * Par contre, il renvoie un int[][] pour refleter les actions sur chaque batchSize
     */
    public int[][] updateUserAgain(final int batchSize, final User... usersToUpdate) {
        ParameterizedPreparedStatementSetter<User> ppss = new ParameterizedPreparedStatementSetter<User>() {

            @Override
            public void setValues(final PreparedStatement ps, final User user) throws SQLException {
                ps.setString(1, user.getName());
                ps.setString(2, user.getMail());
                ps.setInt(3, user.getId());
            }

        };

        return jdbcTemplate.batchUpdate("update users set name = ?, email = ? where id = ?",
                Arrays.asList(usersToUpdate), batchSize, ppss);
    }

    /**
     * Exemple d'utilisation d'un BatchPreparedStatementSetter
     *
     * Cette méthode permet de mettre à jour en masse une liste de users
     */
    public int[] updateUsers(final User... usersToUpdate) {
        BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                User user = usersToUpdate[i];
                ps.setString(1, user.getName());
                ps.setString(2, user.getMail());
                ps.setInt(3, user.getId());
            }

            /**
             * Le batchsize est égal au nombre d'élément à traiter.
             * S'il est supérieur -> ArrayIndexOutOfBoundException
             * S'il est inférieur au nombre d'éléments à traiter, seuls les n premiers seront traités
             *
             * @see org.springframework.jdbc.core.BatchPreparedStatementSetter#getBatchSize()
             */
            @Override
            public int getBatchSize() {
                return usersToUpdate.length;
            }
        };
        return jdbcTemplate.batchUpdate("update users set name = ?, email = ? where id = ?", bpss);
    }

    /**
     * Exemple d'utilisation d'un BatchPreparedStatementSetter avec taille variable
     *
     * Cette méthode permet de mettre à jour en masse une liste de users
     * Seuls les <batchSize> premiers éléments seront traités
     * Si <batchSize> est supérieur au nombre d'éléments à traiter, on aura une erreur
     */
    public int[] updateUsersWithBatchSize(final int batchSize, final User... usersToUpdate) {
        BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                User user = usersToUpdate[i];
                ps.setString(1, user.getName());
                ps.setString(2, user.getMail());
                ps.setInt(3, user.getId());
            }

            @Override
            public int getBatchSize() {
                return batchSize;
            }
        };
        return jdbcTemplate.batchUpdate("update users set name = ?, email = ? where id = ?", bpss);
    }

    /**
     * Mise à jour multiple
     * On utilise un objet pour passer les paramètres, c'est la méthode qui se charge de faire la conversion dans le bon
     * format : List<Object[]>
     *
     * Ici, on passe aussi le type des variables
     * Dans les cas standards (string, int), ce n'est pas obligatoire
     * Ca le devient dans des cas où on veut quelque chose de précis (timestamp, blob...)
     */
    public int[] updateNameWithTypeSet(final UpdateNameCommand... updateCommands) {
        List<Object[]> commands = getBatchArguments(updateCommands);
        String sql = "update users set name = ? where id = ?";

        return jdbcTemplate.batchUpdate(sql, commands, new int[] { Types.NVARCHAR, Types.INTEGER });
    }

    /**
     * Mise à jour multiple
     * On utilise un objet pour passer les paramètres, c'est la méthode qui se charge de faire la conversion dans le bon
     * format : List<Object[]>
     */
    public int[] updateName(final UpdateNameCommand... updateCommands) {
        List<Object[]> commands = getBatchArguments(updateCommands);
        String sql = "update users set name = ? where id = ?";
        return jdbcTemplate.batchUpdate(sql, commands);
    }

    /**
     * Récupération des arguments du batch
     *
     * @param updateCommands
     * @return
     */
    private static List<Object[]> getBatchArguments(final UpdateNameCommand... updateCommands) {
        List<Object[]> commands = new ArrayList<Object[]>();
        for (UpdateNameCommand upd : updateCommands) {
            commands.add(getCommandFromUpdateName(upd));
        }
        return commands;
    }

    /**
     * Transformation d'une commande en Object[]
     */
    private static Object[] getCommandFromUpdateName(final UpdateNameCommand command) {
        return new Object[] { command.getName(), Integer.valueOf(command.getId()) };
    }

    /**
     * Démonstration de l'exécution séquentielle en tant que batch de plusieurs requêtes
     */
    public int[] upperCaseNameAndEmail() {
        String nameUpperCase = "update users set name = upper(name)";
        String mailUpperCase = "update users set email = upper(email)";

        return jdbcTemplate.batchUpdate(nameUpperCase, mailUpperCase);
    }

}
