package fr.norsys.complete.domain.service;

import java.util.Date;
import java.util.List;

import fr.norsys.complete.domain.bo.Genre;
import fr.norsys.complete.domain.bo.Media;
import fr.norsys.complete.domain.bo.Play;

public interface PlayService {

    Play createPlay(final String title, final Date releaseDate, final Genre genres, final Media medias);

    List<Play> getPlaysByYear(int releaseYear);

    /**
     * Play by months
     *
     * @param releaseYear year of release
     * @param releaseMonth month of release (11 means November)
     * @return plays of given month
     */
    List<Play> getPlaysByMonth(int releaseYear, int releaseMonth);

    Play getPlayById(Long id);
}
