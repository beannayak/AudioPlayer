package com.project.audioplayerproject.service;

import com.project.audioplayerproject.domain.Playlist;
import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.repository.PlayListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional (propagation = Propagation.REQUIRES_NEW)
public class PlaylistService {
    @Autowired
    private PlayListDao playListDao;
    
    public void save(Playlist playlist){
        playListDao.save(playlist);
    }
    
    public Playlist getPlaylistById(long id){
        return playListDao.getById(id);
    }
    
    public Playlist update(Playlist playlist){
        return playListDao.update(playlist);
    }
    
    public void delete(Playlist playlist){
        playListDao.removePlaylist(playlist);
    }
    
    public Playlist addSongToPlaylist (Playlist playlist, Song song){
        playlist.addSong(song);
        return playListDao.update(playlist);
    }
}
