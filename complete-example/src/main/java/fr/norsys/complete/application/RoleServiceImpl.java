package fr.norsys.complete.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.norsys.complete.domain.bo.Artist;
import fr.norsys.complete.domain.bo.Play;
import fr.norsys.complete.domain.bo.Role;
import fr.norsys.complete.domain.service.RoleService;
import fr.norsys.complete.persistence.Dao;

@Service
public class RoleServiceImpl implements RoleService {

    private final Dao<Play, Long> playDao;

    private final Dao<Role, Long> roleDao;

    private final Dao<Artist, Long> artistDao;

    @Autowired
    public RoleServiceImpl(final Dao<Artist, Long> artistDao, final Dao<Role, Long> roleDao, final Dao<Play, Long> playDao){
        this.artistDao= artistDao;
        this.roleDao = roleDao;
        this.playDao = playDao;
    }

    @Override
    public List<Role> getRolesForArtist(final Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Role> getRolesByYear(final int year) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Role> getRolesByPlay(final Play play) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addRole(final Artist artist, final Play play, final String roleName) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Play> getPlayByArtist(final Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
