package fr.norsys.complete.domain.bo;

public class Role {

    private final Long id;

    private final Long playId;

    private final Long artistId;

    private final String roleName;

    public Role(final Long id, final Long playId, final Long artistId, final String roleName) {
        this.id = id;
        this.playId = playId;
        this.artistId = artistId;
        this.roleName = roleName;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @return the playId
     */
    public Long getPlayId() {
        return playId;
    }

    /**
     * @return the artistId
     */
    public Long getArtistId() {
        return artistId;
    }
}
