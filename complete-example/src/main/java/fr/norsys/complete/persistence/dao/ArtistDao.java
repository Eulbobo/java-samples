package fr.norsys.complete.persistence.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.norsys.complete.domain.bo.Artist;
import fr.norsys.complete.persistence.BeanMap;
import fr.norsys.complete.persistence.Dao;
import fr.norsys.complete.persistence.utils.BaseDao;

@Repository
public class ArtistDao implements Dao<Artist, Long> {

    private final BaseDao<Artist> baseDao;

    @Autowired
    public ArtistDao(final RowMapper<Artist> rowMapper, final BeanMap<Artist> beanMap, final DataSource datasource){
        baseDao = new BaseDao<Artist>(beanMap, rowMapper, "T_ARTIST", datasource);
    }

    @Override
    public Long save(final Artist artist) {
        return baseDao.save(artist).longValue();
    }

    @Override
    public Artist getById(final Long id) {
        return baseDao.getById("ARTIST_ID", id);
    }

    @Override
    public List<Artist> findLike(final Artist example) {
        String sql = "select * from T_ARTIST where ARTIST_LB_FNM = :firstName and ARTIST_LB_LNM = :lastName"
                + " and ARTIST_DT_BTH = :birthDate and ARTIST_DT_DTH = :deathDate";
        return baseDao.findByExample(sql, example);
    }

    public List<Artist> findByName(final String name){
        String sql = "select * from T_ARTIST where upper(ARTIST_LB_FNM) like upper(:name) "
                + " or upper(ARTIST_LB_LNM) like upper(:name)";
        return baseDao.findLike(sql, Collections.singletonMap("name", "%" + name + "%"));
    }

    @Override
    public List<Artist> findAll() {
        return baseDao.findAll();
    }
}
