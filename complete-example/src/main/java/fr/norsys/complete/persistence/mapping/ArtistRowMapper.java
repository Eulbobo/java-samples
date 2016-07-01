package fr.norsys.complete.persistence.mapping;

import static fr.norsys.complete.persistence.utils.MapperUtils.getDate;
import static fr.norsys.complete.persistence.utils.MapperUtils.getLong;
import static fr.norsys.complete.persistence.utils.MapperUtils.getString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.norsys.complete.domain.bo.Artist;
import fr.norsys.complete.persistence.BeanMap;

@Component
public class ArtistRowMapper implements RowMapper<Artist>, BeanMap<Artist> {

    @Override
    public Artist mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Artist(
                getLong(rs, "ARTIST_ID"),
                getString(rs, "ARTIST_LB_FNM"),
                getString(rs, "ARTIST_LB_LNM"),
                getDate(rs, "ARTIST_DT_BTH"),
                getDate(rs, "ARTIST_DT_DTH"));
    }

    @Override
    public Map<String, Object> getBeanMap(final Artist artist) {
        Map<String, Object> insertMapping = new HashMap<String, Object>();
        insertMapping.put("ARTIST_ID", artist.getId());
        insertMapping.put("ARTIST_LB_FNM", artist.getFirstName());
        insertMapping.put("ARTIST_LB_LNM", artist.getLastName());
        insertMapping.put("ARTIST_DT_BTH", artist.getBirthDate());
        insertMapping.put("ARTIST_DT_DTH", artist.getDeathDate());
        return insertMapping;
    }

}
