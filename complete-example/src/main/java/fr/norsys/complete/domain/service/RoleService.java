package fr.norsys.complete.domain.service;

import java.util.List;

import fr.norsys.complete.domain.bo.Artist;
import fr.norsys.complete.domain.bo.Play;
import fr.norsys.complete.domain.bo.Role;

public interface RoleService {

    List<Role> getRolesForArtist(final Long id);

    List<Play> getPlayByArtist(final Long id);

    List<Role> getRolesByPlay(final Play play);

    List<Role> getRolesByYear(final int year);

    void addRole(Artist artist, Play play, String roleName);

}
