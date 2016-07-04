package fr.norsys.springjdbc.operations.jdbctemplate.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import fr.norsys.springjdbc.beans.User;
import fr.norsys.springjdbc.operations.mapper.rowmapper.UserRowMapper;

/**
 * Le preparedStatementCreator permet de créer une requête en précisant les paramètres du preparedStatement utilisé
 */
@Repository
public class Query_PreparedStatementCreator {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Query_PreparedStatementCreator(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Dans cet exemple, son usage est totalement disproportionné
     * @return List<User>
     */
    public List<User> getAllUsers() {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection con) throws SQLException {
                return con.prepareStatement("select * from users",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY,
                        ResultSet.HOLD_CURSORS_OVER_COMMIT);
            }
        };

        return jdbcTemplate.query(psc, new UserRowMapper());
    }

    /**
     * Dans cet exemple, son usage est encore plus disproportionné
     * @return User
     */
    public User getSingleUserWithOverkill(final int id) {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            /**
             * Création d'un preparedStatement que l'on peut parcourir dans le sens qu'on veut
             * @see org.springframework.jdbc.core.PreparedStatementCreator#createPreparedStatement(java.sql.Connection)
             */
            @Override
            public PreparedStatement createPreparedStatement(final Connection con) throws SQLException {
                return con.prepareStatement("select * from users",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY,
                        ResultSet.HOLD_CURSORS_OVER_COMMIT);
            }
        };

        /**
         * Création d'un resultSetExtractor qui lit à l'envers
         */
        ResultSetExtractor<User> rse = new ResultSetExtractor<User>() {
            @Override
            public User extractData(final ResultSet rs) throws SQLException, DataAccessException {
                User finalUser = new User();

                // lecture par la fin
                boolean continueReading = true;
                if (rs.last()){
                    while (continueReading){
                        User extractor = extractUser(rs);
                        if (extractor.getId() == id){
                            finalUser = extractor;
                        }
                        continueReading = rs.relative(-1);
                    }
                }

                return finalUser;
            }

            private User extractUser(final ResultSet rs) throws SQLException {
                User extracted = new User();
                extracted.setId(rs.getInt("ID"));
                extracted.setName(rs.getString("NAME"));
                extracted.setMail(rs.getString("EMAIL"));
                return extracted;
            }
        };

        return jdbcTemplate.query(psc, rse);
    }
}
