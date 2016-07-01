package fr.norsys.complete.domain.service;

import java.util.Date;
import java.util.List;

import fr.norsys.complete.domain.bo.Artist;

public interface ArtistService {

    Artist createArtist(final String firstName, final String lastName, Date birthDate);

    Artist getArtistById(Long id);

    List<Artist> getAllArtists();

}
