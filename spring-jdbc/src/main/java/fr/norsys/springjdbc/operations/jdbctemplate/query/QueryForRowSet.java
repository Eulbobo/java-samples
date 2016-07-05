package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;

@Repository
public class QueryForRowSet {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryForRowSet(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Démonstration de l'utilisation d'un rowSet pour récupérer un élément
     */
    public User getUser(final int id) {
        SqlRowSet rowset = jdbcTemplate.queryForRowSet("select * from users where id = ?", id);
        User user = null;
        if (rowset.first()) {
            user = new User();
            user.setId(rowset.getInt("ID"));
            user.setName(rowset.getString("NAME"));
            user.setMail(rowset.getString("EMAIL"));
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        SqlRowSet rowset = jdbcTemplate.queryForRowSet("select * from users");

        while (rowset.next()) {
            User user = new User();
            user.setId(rowset.getInt("ID"));
            user.setName(rowset.getString("NAME"));
            user.setMail(rowset.getString("EMAIL"));
            userList.add(user);
        }

        return userList;
    }
}
