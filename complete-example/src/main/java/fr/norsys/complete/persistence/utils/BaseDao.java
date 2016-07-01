package fr.norsys.complete.persistence.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import fr.norsys.complete.persistence.BeanMap;

public class BaseDao<T> {

    private final BaseInsertDao<T> baseInsertDao;

    private final BaseSelectDao<T> baseSelectDao;

    public BaseDao(final BeanMap<T> beanMap, final RowMapper<T> rowMapper, final String tableName, final DataSource dataSource){
        this.baseInsertDao = new BaseInsertDao<T>(beanMap, tableName, dataSource);
        this.baseSelectDao = new BaseSelectDao<T>(beanMap, rowMapper, tableName, dataSource);
    }

    public void save(final T element) {
        baseInsertDao.save(element);
    }

    public T getById(final String idColumnName, final Serializable id) {
        return baseSelectDao.getById(idColumnName, id);
    }

    public List<T> findByExample(final String query, final T example) {
        return baseSelectDao.findByExample(query, example);
    }

    public List<T> findLike(final String query, final Map<String, ?> map) {
        return baseSelectDao.findLike(query, map);
    }

    public List<T> findAll() {
        return baseSelectDao.findAll();
    }
}
