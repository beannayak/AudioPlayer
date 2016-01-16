/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.controller;

import com.project.audioplayerproject.domain.Playlist;
import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.service.PlaylistService;
import com.project.audioplayerproject.service.SongService;
import com.project.audioplayerproject.service.UserService;
import com.project.audioplayerproject.utilities.ImageAndSongDeleteUtility;
import com.project.audioplayerproject.utilities.ImageAndSongRetrieverUtility;
import com.project.audioplayerproject.utilities.ImageAndSongSaveUtility;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping ("/api")
public class PlayerRestServices {
    Authentication auth;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SongService songService;
    
    @Autowired
    private ImageAndSongRetrieverUtility imageAndSongRetrieverUtility;
    
    @Autowired
    private ImageAndSongDeleteUtility imageAndSongDeleteUtility;
    
    @Autowired
    private ImageAndSongSaveUtility imageAndSongSaveUtility;
    
    @Autowired
    private PlaylistService playlistService;
    
    @RequestMapping("/getSong/{song}.mp3")
    public @ResponseBody
    byte[] sendSong(@PathVariable String song, HttpServletRequest request, HttpServletResponse response) {
        String loggedInUserName = getLoggedInUserName();
        
        response.setHeader("Content-Type", "audio/mpeg");
        return imageAndSongRetrieverUtility.getSongWithLoggedInUserAndSongName(loggedInUserName, song);
    }
    
    @RequestMapping("/getImage/{image}.jpg")
    public @ResponseBody
    byte[] sendImage(@PathVariable String image, HttpServletRequest request, HttpServletResponse response, Model model) {
        String loggedInUserName = getLoggedInUserName();
        
        response.setHeader("Content-Type", "image/jpeg");
        return imageAndSongRetrieverUtility.getImageWithLoggedInUserAndImageName(loggedInUserName, image);
    }
    
    @RequestMapping("/getSongsList")
    public @ResponseBody List<Song> sendSongList(HttpServletRequest request, HttpServletResponse response) {
        String loggedInUserName = getLoggedInUserName();
        
        User user = userService.getUserByUsername(loggedInUserName);
        return user.getSongs();
    }
    
    @RequestMapping (value = "/deleteSong", method = RequestMethod.GET)
    public @ResponseBody boolean deleteSongAndSendResult(@RequestParam String songName){
        String loggedInUserName = getLoggedInUserName();
        
        User user = userService.getUserByUsername(loggedInUserName);
        Song song = songService.getSongByLocation(songName);
             
        imageAndSongDeleteUtility.deleteImageAssociatedWithSongAndReturnResult(loggedInUserName, songName);
        boolean flag = imageAndSongDeleteUtility.deleteSongAndReturnResult(loggedInUserName, songName);
        
        if (flag) {
            user.getSongs().remove(song);
            userService.update(user);
            songService.delete(song);
        }
        
        return flag;
    }
    
    @RequestMapping (value="/getAllSongs", method = RequestMethod.GET)
    public @ResponseBody List<Song> sendAllSongsList(){
        String loggedInUserName = getLoggedInUserName();
        
        User user = userService.getUserByUsername(loggedInUserName);
        return user.getSongs();
    }
    
    @RequestMapping(value="/editSong", method=RequestMethod.POST) 
    public @ResponseBody boolean editSong(@RequestParam String songName, @RequestParam String title,
                @RequestParam String artist, @RequestParam String album, @RequestParam boolean albumArtChanged,
                @RequestParam MultipartFile albumcover){
        
        String loggedInUserName = getLoggedInUserName();
        
        User user = userService.getUserByUsername(loggedInUserName);
        Song song = songService.getSongByLocation(songName);
        
        if (song.getUser().equals(user)){
            Song updatedSong = new Song(song.getId(), title, artist, album, song.getLocation(), song.getUser());
            songService.update(updatedSong);
            if (albumArtChanged) {
                return imageAndSongSaveUtility.saveImageOnly(albumcover, loggedInUserName, Long.valueOf(songName.substring(1)));
            }
        }
        
        return true;
    }
    
    @RequestMapping ("/getAllPlaylists")
    public @ResponseBody List<Playlist> getAllPlaylists(){
        String loggedInUserName = getLoggedInUserName();
        
        return playlistService.getAllPlaylistsForUser(loggedInUserName);
    }
    
    @RequestMapping ("/addPlaylist")
    public @ResponseBody boolean addAPlaylist(@RequestParam String playlistName){
        String loggedInUserName = getLoggedInUserName();
        
        return userService.createANewPlaylist(playlistName, loggedInUserName);
    }
    
    @RequestMapping ("/deletePlaylist")
    public @ResponseBody boolean deleteAPlaylists(@RequestParam long playlistId){
        String loggedInUserName = getLoggedInUserName();
        User user = userService.getUserByUsername(loggedInUserName);
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        
        if (playlist != null && playlist.getUser().equals(user)) {
            playlistService.delete(playlist);
            return true;
        }
        return false;
    }
    
    @RequestMapping ("/addSongToPlaylist")
    public @ResponseBody boolean addSongToPlaylist(@RequestParam String songName, @RequestParam long playlistId){
        String loggedInUserName = getLoggedInUserName();
        
        return playlistService.addSongToPlaylist(playlistId, songName, loggedInUserName);
    }
    
    @RequestMapping ("/removeSongFromPlaylist")
    public @ResponseBody boolean removeSongFromPlaylist(@RequestParam String songName, @RequestParam long playlistId){
        String loggedInUserName = getLoggedInUserName();
        
        return playlistService.removeSongFromPlaylist(playlistId, songName, loggedInUserName);
    }
    
    @RequestMapping (value="/getSongsFromPlaylist")
    public @ResponseBody List<Song> getSongsFromPlaylist(@RequestParam long playlistId){
        String loggedInUserName = getLoggedInUserName();
        
        User user = userService.getUserByUsername(loggedInUserName);
        Playlist playlist = playlistService.getPlaylistByIdAndUser(playlistId, user);
        
        if (playlist != null) {
            return playlist.getSongs();
        } 
        return null;
    }
    
    private String getLoggedInUserName(){
        auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();   
    }
}
