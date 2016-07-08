package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Le queryForList permet de r�cup�rer une liste d'�l�ments
 */
@Repository
public class QueryForList {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryForList(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * R�cup�ration de tous les �l�ments dans une map
     *
     * @return liste de map nom de colonne/valeur
     */
    public List<Map<String, Object>> getAllElements() {
        return jdbcTemplate.queryForList("select * from users");
    }

    /**
     * R�cup�ration de tous les �l�ments
     * Dans cette requ�te, on sait r�cup�rer automatiquement le type de l'�l�ment pass� en requ�te
     *
     * @return liste de map nom de colonne/valeur
     */
    public List<Integer> getAllElementsId() {
        return jdbcTemplate.queryForList("select id from users", Integer.class);
    }

    /**
     * Utilisation d'un param�tre dans la requ�te
     *
     * @param name nom de recherche
     * @return liste de map nom de colonne/valeur
     */
    public List<Map<String, Object>> getElementsWithName(final String name) {
        return jdbcTemplate.queryForList("select * from users where upper(name) = upper(?)", name);
    }

    /**
     * Utilisation d'un param�tre dans la requ�te avec un like
     *
     * @param name nom de recherche
     * @return liste de map nom de colonne/valeur
     */
    public List<Map<String, Object>> getElementsLike(final String name) {
        return jdbcTemplate.queryForList("select * from users where upper(name) like upper(?)", "%" + name + "%");
    }

}
