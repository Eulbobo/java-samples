package fr.norsys.complete.persistence.utils;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDeleteDao<T> {

    private final String tableName;

    private final JdbcTemplate jdbcTemplate;

    public BaseDeleteDao(final String tableName, final DataSource dataSource){
        this.tableName = tableName;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void delete(final String idColumnName, final Serializable id) {
        jdbcTemplate.update("delete from " + tableName + " where " + idColumnName + " = ?", id);
    }
}
