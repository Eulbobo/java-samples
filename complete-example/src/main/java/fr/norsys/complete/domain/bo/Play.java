package fr.norsys.complete.domain.bo;

import java.util.Date;

public class Play {

    private final Long id;

    private final String title;

    private final Date releaseDate;

    private final Genre genre;

    private final Media media;

    public Play(final Long id, final String title, final Date releaseDate, final Genre genre, final Media media) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.media = media;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * @return the media
     */
    public Media getMedia() {
        return media;
    }


}
