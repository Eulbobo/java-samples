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
 * Il n'existe pas d'impl�mentation du type SimpleJdbcInsert qui permette de g�rer les s�quences
 * Une solution serait d'impl�menter une copie du SimpleJdbcInsert en lui fournissant un g�n�rateur de cl�
 * Et le g�n�rateur se chargerait de r�cup�rer la valeur d�sir�e de la s�quence
 */
@Repository
public class JdbcInsertUsageWithSequence {

    private final SimpleJdbcInsert insert;

    /**
     * Initialisation du {@link SimpleJdbcInsert}
     *
     * Tant qu'il n'a pas �t� ex�cut�, on peut modifier ses param�tre avec les m�thodes withXXX
     *
     * Ici, on utilise l'autoIncrement de la colonne ID, donc on le pr�cise dans le builder
     */
    @Autowired
    public JdbcInsertUsageWithSequence(final DataSource dataSource) {
        insert = new SimpleJdbcInsert(dataSource)
                .withTableName("users_auto")
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Cr�ation d'un user avec le bean properties
     *
     * Le syst�me se base sur le nom des champs pour faire le mapping d'insertion entre les colonnes et les champs
     *
     * Dans la table users_auto, les champs en base correspondent parfaitement aux propri�t�s du bean
     * Bon, par contre, on r�cup�re pas l'ID g�n�r� en faisant comme �a
     */
    public void insertUserWithBeanProperties(final User user) {
        insert.execute(new BeanPropertySqlParameterSource(user));
    }


    /**
     * Ici, on ins�re et un r�cup�re la cl� g�n�r�e
     *
     * Ca marche bien si c'est un Number standard
     */
    public Number insertUserAndGetId(final User user){
        return insert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
    }

    /**
     * M�me chose que l'insert pr�c�dent, mais avec un twist : on set l'ID dans le user en param�tre
     */
    public void saveUser(final User user){
        Number id = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
        user.setId(id.intValue());
    }

    /**
     * Ici, on ins�re et un r�cup�re un key holder
     *
     * Ici, il ne contient d'un �l�ment id, mais on peut imaginer une cl� automatique plus complexe
     */
    public KeyHolder insertUserAndGetKeyHolder(final User user){
        return insert.executeAndReturnKeyHolder(new BeanPropertySqlParameterSource(user));
    }

}
