package fr.norsys.springjdbc.operations.jdbctemplate.modify;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

/**
 * Ceci est la présentation d'une autre façon de faire un update
 * On utilise un sqlUpdate auquel on aura passé les éléments pour la mise à jour
 */
@Repository
public class UserUpdate {

    private final SqlUpdate sqlUpdate;

    @Autowired
    public UserUpdate(final DataSource dataSource){
        sqlUpdate = new SqlUpdate(dataSource, "update users set name = ?, email = ? where id = ?");
        sqlUpdate.declareParameter(new SqlParameter("name", Types.NVARCHAR));
        sqlUpdate.declareParameter(new SqlParameter("email", Types.NVARCHAR));
        sqlUpdate.declareParameter(new SqlParameter("id", Types.NUMERIC));
        sqlUpdate.compile();
    }

    public int updateUser(final int id, final String name, final String email){
        return sqlUpdate.update(name, email, id);
    }

}
