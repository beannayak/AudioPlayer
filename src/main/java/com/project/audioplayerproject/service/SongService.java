/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.service;

import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.repository.SongDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author binayak
 */
@Service
@Transactional (propagation = Propagation.REQUIRES_NEW)
public class SongService {
    @Autowired
    private SongDao sd;
    
    public void save(Song song){
        sd.persist(song);
    }
    
    public Song getSongById(long id){
        return sd.getSongById(id);
    }
    
    public long getTotalSongCount(){
        return sd.getTotalSongCount();
    }
    
    public Song update(Song song){
        return sd.update(song);
    }
}
