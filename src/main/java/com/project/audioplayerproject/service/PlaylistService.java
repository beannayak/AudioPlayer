/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.service;

import com.project.audioplayerproject.domain.Playlist;
import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.repository.PlayListDao;
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
public class PlaylistService {
    @Autowired
    private PlayListDao pld;
    
    public void save(Playlist playlist){
        pld.save(playlist);
    }
    
    public Playlist getPlaylistById(long id){
        return pld.getById(id);
    }
    
    public Playlist update(Playlist playlist){
        return pld.update(playlist);
    }
    
    public void delete(Playlist playlist){
        pld.removePlaylist(playlist);
    }
    
    public Playlist addSongToPlaylist (Playlist playlist, Song song){
        playlist.addSong(song);
        return pld.update(playlist);
    }
}
