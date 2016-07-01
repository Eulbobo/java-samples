package fr.norsys.complete.persistence.mapping;

import static fr.norsys.complete.persistence.utils.MapperUtils.getDate;
import static fr.norsys.complete.persistence.utils.MapperUtils.getEnum;
import static fr.norsys.complete.persistence.utils.MapperUtils.getLong;
import static fr.norsys.complete.persistence.utils.MapperUtils.getString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.norsys.complete.domain.bo.Genre;
import fr.norsys.complete.domain.bo.Media;
import fr.norsys.complete.domain.bo.Play;
import fr.norsys.complete.persistence.BeanMap;

@Component
public class PlayRowMapper implements RowMapper<Play>, BeanMap<Play> {

    @Override
    public Play mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Play(
                getLong(rs, "PLAY_ID"),
                getString(rs, "PLAY_LB_TIT"),
                getDate(rs, "PLAY_DT_RLS"),
                getEnum(rs, "PLAY_TY_GNR", Genre.class),
                getEnum(rs, "PLAY_TY_MED", Media.class));
    }

    @Override
    public Map<String, Object> getBeanMap(final Play play) {
        Map<String, Object> insertMapping = new HashMap<String, Object>();
        insertMapping.put("PLAY_ID", play.getId());
        insertMapping.put("PLAY_LB_TIT", play.getTitle());
        insertMapping.put("PLAY_DT_RLS", play.getReleaseDate());
        insertMapping.put("PLAY_TY_GNR", play.getGenre());
        insertMapping.put("PLAY_TY_MED", play.getMedia());
        return insertMapping;
    }

}
