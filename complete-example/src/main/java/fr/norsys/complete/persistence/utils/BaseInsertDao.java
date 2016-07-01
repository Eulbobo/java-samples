package fr.norsys.complete.persistence.utils;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import fr.norsys.complete.persistence.BeanMap;

public class BaseInsertDao<T> {

    private final BeanMap<T> beanMap;

    private final String tableName;

    private final JdbcTemplate jdbcTemplate;

    public BaseInsertDao(final BeanMap<T> beanMap, final String tableName, final DataSource dataSource){
        this.beanMap = beanMap;
        this.tableName = tableName;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(final T element) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(tableName);
        insert.execute(beanMap.getBeanMap(element));
    }
}
