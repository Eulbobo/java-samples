package fr.norsys.complete.persistence.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import fr.norsys.complete.domain.bo.Role;
import fr.norsys.complete.persistence.Dao;

@Repository
public class RoleDao extends NamedParameterJdbcDaoSupport implements Dao<Role, Long> {

    @Autowired
    public RoleDao(final DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public Long save(final Role role) {
        // TODO
        return null;
    }

    @Override
    public Role getById(final Long id) {
        // TODO
        return null;
    }

    @Override
    public List<Role> findLike(final Role example) {
        // TODO
        return null;
    }

    @Override
    public List<Role> findAll() {
        // TODO
        return null;
    }
}
