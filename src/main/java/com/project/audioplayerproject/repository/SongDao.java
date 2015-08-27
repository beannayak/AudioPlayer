/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.repository;

import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author binayak
 */
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
}
