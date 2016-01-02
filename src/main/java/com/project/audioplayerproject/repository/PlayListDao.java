package com.project.audioplayerproject.repository;

import com.project.audioplayerproject.domain.Playlist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional (propagation = Propagation.MANDATORY)
public class PlayListDao {
    @Autowired
    private SessionFactory sf;
    
    public void save(Playlist playlist){
        sf.getCurrentSession().persist(playlist);
    }
    
    public Playlist getById(long id){
        return (Playlist)sf.getCurrentSession().get(Playlist.class, id);
    }
    
    public Playlist update(Playlist playlist){
        return (Playlist) sf.getCurrentSession().merge(playlist);
    }
    
    public void removePlaylist(Playlist playlist){
        sf.getCurrentSession().delete(playlist);
    }
}
