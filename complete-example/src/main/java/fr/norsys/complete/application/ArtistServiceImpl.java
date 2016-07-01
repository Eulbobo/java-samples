package fr.norsys.complete.application;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.norsys.complete.domain.bo.Artist;
import fr.norsys.complete.domain.service.ArtistService;
import fr.norsys.complete.persistence.dao.ArtistDao;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDao artistDao;

    @Autowired
    public ArtistServiceImpl(final ArtistDao artistDao){
        this.artistDao= artistDao;
    }

    @Override
    public Artist getArtistById(final Long id) {
        return artistDao.getById(id);
    }

    @Override
    public Artist createArtist(final String firstName, final String lastName, final Date birthDate) {
        Artist findLike = new Artist(null, firstName, lastName, birthDate, null);
        List<Artist> artists = artistDao.findLike(findLike);

        if (artists.isEmpty()){
            artistDao.save(findLike);
        } else {
            throw new IllegalStateException("Artist already exists");
        }

        return findLike;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistDao.findAll();
    }

}
