package com.project.audioplayerproject.repository;

import com.project.audioplayerproject.domain.Song;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SongDao {
    @Autowired
    private SessionFactory sf;
    
    public void persist(Song song){
        sf.getCurrentSession().persist(song);
    }
    
    public Song getSongById(long id){
        return (Song) sf.getCurrentSession().load(Song.class, id);
    }
    
    public long getTotalSongCount(){
        return (Long) sf.getCurrentSession().createQuery("select count(*) from Song").uniqueResult();
    }
    
    public Song update(Song song){
        return (Song)sf.getCurrentSession().merge(song);
    }
    
    public void delete(Song song){
        sf.getCurrentSession().delete(song);
    }
    
    public long lastIdInserted(){
        Long lastIdInserted = null;
        
        lastIdInserted = (Long) sf.getCurrentSession().createQuery("select max(id) from Song").uniqueResult();
        if (lastIdInserted == null){
            return 0;
        }
        return lastIdInserted;
    }
    
    public Song getSongByLocation(String location){
        Query query = sf.getCurrentSession().createQuery("SELECT s FROM Song s WHERE location = :location");
        query.setParameter("location", location);
        List<Song> ls = query.list();
        if (ls.size() >= 1){
            return ls.get(0);
        } 
        return null;
    }
}
