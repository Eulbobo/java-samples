package fr.norsys.springjdbc.operations.jdbctemplate.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Modification de données (entre autres)
 */
@Service
public class Update {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Update(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
