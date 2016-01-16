package com.project.audioplayerproject.service;

import com.project.audioplayerproject.domain.Playlist;
import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.repository.PlayListDao;
import com.project.audioplayerproject.repository.SongDao;
import com.project.audioplayerproject.repository.UserDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional (propagation = Propagation.REQUIRES_NEW)
public class PlaylistService {
    @Autowired
    private PlayListDao playListDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SongDao songDao;
    
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

    public boolean addSongToPlaylist (long playlistId, String songName, String loggedInUser){
        Song song = songDao.getSongByLocation(songName);
        Playlist playlist = getPlaylistById(playlistId);
        User user = userDao.getUserByUsername(loggedInUser);
        
        if (song != null && playlist != null && user.equals(playlist.getUser()) && !playlist.getSongs().contains(song)) {
            playlist.addSong(song);
            playListDao.update(playlist);
            return true;
        }
        
        return false;
    }
    
    public boolean removeSongFromPlaylist(long playlistId, String songName, String loggedInUserName) {
        Song song = songDao.getSongByLocation(songName);
        Playlist playlist = getPlaylistById(playlistId);
        User user = userDao.getUserByUsername(loggedInUserName);
        
        if (song != null && playlist != null && user.equals(playlist.getUser()) && playlist.getSongs().contains(song)) {
            playlist.getSongs().remove(song);
            playListDao.update(playlist);
            return true;
        }
        
        return false;
    }
    
    public List<Playlist> getAllPlaylistsForUser(String loggedInUserName){
        User user = userDao.getUserByUsername(loggedInUserName);
        return user.getPlaylists();
    }

    public Playlist getPlaylistByIdAndUser(long playlistId, User loggedInUser) {
        Playlist playlist = getPlaylistById(playlistId);
        
        if (playlist != null && playlist.getUser().equals(loggedInUser)) {
            return playlist;
        }
        
        return null;
    }
}
