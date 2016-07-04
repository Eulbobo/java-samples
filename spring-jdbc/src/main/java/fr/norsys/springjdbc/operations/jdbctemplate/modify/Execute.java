package fr.norsys.springjdbc.operations.jdbctemplate.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Ex�cution de requ�tes
 */
@Service
public class Execute {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Execute(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // toto execute
}
