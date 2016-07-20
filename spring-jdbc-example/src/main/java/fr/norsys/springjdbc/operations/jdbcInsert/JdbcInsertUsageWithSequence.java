package fr.norsys.springjdbc.operations.jdbcInsert;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;

/**
 * Quelques exemples d'utilisation du {@link SimpleJdbcInsert}, en utilisant un autoincrement
 *
 * Il n'existe pas d'implémentation du type SimpleJdbcInsert qui permette de gérer les séquences
 * Une solution serait d'implémenter une copie du SimpleJdbcInsert en lui fournissant un générateur de clé
 * Et le générateur se chargerait de récupérer la valeur désirée de la séquence
 */
@Repository
public class JdbcInsertUsageWithSequence {

    private final SimpleJdbcInsert insert;

    /**
     * Initialisation du {@link SimpleJdbcInsert}
     *
     * Tant qu'il n'a pas été exécuté, on peut modifier ses paramètre avec les méthodes withXXX
     *
     * Ici, on utilise l'autoIncrement de la colonne ID, donc on le précise dans le builder
     */
    @Autowired
    public JdbcInsertUsageWithSequence(final DataSource dataSource) {
        insert = new SimpleJdbcInsert(dataSource)
                .withTableName("users_auto")
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Création d'un user avec le bean properties
     *
     * Le système se base sur le nom des champs pour faire le mapping d'insertion entre les colonnes et les champs
     *
     * Dans la table users_auto, les champs en base correspondent parfaitement aux propriétés du bean
     * Bon, par contre, on récupère pas l'ID généré en faisant comme ça
     */
    public void insertUserWithBeanProperties(final User user) {
        insert.execute(new BeanPropertySqlParameterSource(user));
    }


    /**
     * Ici, on insère et un récupère la clé générée
     *
     * Ca marche bien si c'est un Number standard
     */
    public Number insertUserAndGetId(final User user){
        return insert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
    }

    /**
     * Même chose que l'insert précédent, mais avec un twist : on set l'ID dans le user en paramètre
     */
    public void saveUser(final User user){
        Number id = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
        user.setId(id.intValue());
    }

    /**
     * Ici, on insère et un récupère un key holder
     *
     * Ici, il ne contient d'un élément id, mais on peut imaginer une clé automatique plus complexe
     */
    public KeyHolder insertUserAndGetKeyHolder(final User user){
        return insert.executeAndReturnKeyHolder(new BeanPropertySqlParameterSource(user));
    }

}
