package com.project.audioplayerproject.service;

import com.project.audioplayerproject.domain.Playlist;
import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.repository.SongDao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional (propagation = Propagation.REQUIRES_NEW)
public class SongService {
    @Autowired
    private SessionFactory sf;
    
    @Autowired
    private SongDao songDao;
    
    @Autowired
    private PlaylistService playlistService;
    
    @Autowired
    private UserService userService;
    
    public void save(Song song){
        songDao.persist(song);
    }
    
    public Song getSongById(long id){
        return songDao.getSongById(id);
    }
    
    public long getTotalSongCount(){
        return songDao.getTotalSongCount();
    }
    
    public Song update(Song song){
        return songDao.update(song);
    }
    
    public void delete(Song song){
        List<Playlist> playlists = song.getPlaylists();
        for (Playlist playlist : playlists) {
            playlist.removeSong(song);
            playlistService.update(playlist);
        }
        song.setPlaylists(null);
        songDao.update(song);
        sf.getCurrentSession().flush();
        songDao.delete(song);
    }
    
    public long getLastInserted(){
        return songDao.lastIdInserted();
    }
    
    public Song getSongByLocation(String location){
        return songDao.getSongByLocation(location);
    }
}
