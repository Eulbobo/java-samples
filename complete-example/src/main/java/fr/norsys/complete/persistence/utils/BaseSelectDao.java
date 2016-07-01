package fr.norsys.complete.persistence.utils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.norsys.complete.persistence.BeanMap;

public class BaseSelectDao<T> {

    private final RowMapper<T> rowMapper;

    private final BeanMap<T> beanMap;

    private final String tableName;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BaseSelectDao(final BeanMap<T> beanMap, final RowMapper<T> rowMapper, final String tableName, final DataSource dataSource){
        this.rowMapper = rowMapper;
        this.tableName = tableName;
        this.beanMap = beanMap;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public T getById(final String idColumnName, final Serializable id) {
        String query = "select * from " + tableName + " where " + idColumnName + " = :id";
        return namedParameterJdbcTemplate.queryForObject(query, Collections.singletonMap("id", id), rowMapper);
    }

    public List<T> findByExample(final String query, final T example) {
        return namedParameterJdbcTemplate.query(query, beanMap.getBeanMap(example), rowMapper);
    }

    public List<T> findLike(final String query, final Map<String, ?> map) {
        return namedParameterJdbcTemplate.query(query, map, rowMapper);
    }

    public List<T> findAll() {
        return namedParameterJdbcTemplate.query("select * from " + tableName, rowMapper);
    }
}
