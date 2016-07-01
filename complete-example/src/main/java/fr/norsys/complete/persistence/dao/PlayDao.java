package fr.norsys.complete.persistence.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.norsys.complete.domain.bo.Play;
import fr.norsys.complete.persistence.BeanMap;
import fr.norsys.complete.persistence.Dao;
import fr.norsys.complete.persistence.utils.BaseDao;

@Repository
public class PlayDao implements Dao<Play, Long> {

    private final BaseDao<Play> baseDao;

    @Autowired
    public PlayDao(final RowMapper<Play> rowMapper, final BeanMap<Play> beanMap, final DataSource datasource){
        baseDao = new BaseDao<Play>(beanMap, rowMapper, "T_PLAY", datasource);
    }

    @Override
    public Long save(final Play play) {
        return baseDao.save(play).longValue();
    }

    @Override
    public Play getById(final Long id) {
        return baseDao.getById("PLAY_ID", id);
    }

    @Override
    public List<Play> findLike(final Play example) {
        String sql = "select * from T_PLAY where ...";
        return baseDao.findByExample(sql, example);
    }

    @Override
    public List<Play> findAll() {
        return baseDao.findAll();
    }

}
