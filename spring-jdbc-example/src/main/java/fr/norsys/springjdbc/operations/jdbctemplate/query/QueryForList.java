package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Le queryForList permet de récupérer une liste d'éléments
 */
@Repository
public class QueryForList {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryForList(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Récupération de tous les éléments dans une map
     *
     * @return liste de map nom de colonne/valeur
     */
    public List<Map<String, Object>> getAllElements() {
        return jdbcTemplate.queryForList("select * from users");
    }

    /**
     * Récupération de tous les éléments
     * Dans cette requête, on sait récupérer automatiquement le type de l'élément passé en requête
     *
     * @return liste de map nom de colonne/valeur
     */
    public List<Integer> getAllElementsId() {
        return jdbcTemplate.queryForList("select id from users", Integer.class);
    }

    /**
     * Utilisation d'un paramètre dans la requête
     *
     * @param name nom de recherche
     * @return liste de map nom de colonne/valeur
     */
    public List<Map<String, Object>> getElementsWithName(final String name) {
        return jdbcTemplate.queryForList("select * from users where upper(name) = upper(?)", name);
    }

    /**
     * Utilisation d'un paramètre dans la requête avec un like
     *
     * @param name nom de recherche
     * @return liste de map nom de colonne/valeur
     */
    public List<Map<String, Object>> getElementsLike(final String name) {
        return jdbcTemplate.queryForList("select * from users where upper(name) like upper(?)", "%" + name + "%");
    }

}
