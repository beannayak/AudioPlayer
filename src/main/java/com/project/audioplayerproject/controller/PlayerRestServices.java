/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.controller;

import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.service.SongService;
import com.project.audioplayerproject.service.UserService;
import com.project.audioplayerproject.utilities.ImageAndSongDeleteUtility;
import com.project.audioplayerproject.utilities.ImageAndSongRetrieverUtility;
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
    
    @RequestMapping("/getSong/{song}.mp3")
    public @ResponseBody
    byte[] sendSong(@PathVariable String song, HttpServletRequest request, HttpServletResponse response) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
        response.setHeader("Content-Type", "audio/mpeg");
        return imageAndSongRetrieverUtility.getSongWithLoggedInUserAndSongName(loggedInUserName, song);
    }
    
    @RequestMapping("/getImage/{image}.jpg")
    public @ResponseBody
    byte[] sendImage(@PathVariable String image, HttpServletRequest request, HttpServletResponse response, Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
        response.setHeader("Content-Type", "image/jpeg");
        return imageAndSongRetrieverUtility.getImageWithLoggedInUserAndImageName(loggedInUserName, image);
    }
    
    @RequestMapping("/getSongsList")
    public @ResponseBody List<Song> sendSongList(HttpServletRequest request, HttpServletResponse response) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
        User user = userService.getUserByUsername(loggedInUserName);
        return user.getSongs();
    }
    
    @RequestMapping (value = "/deleteSong", method = RequestMethod.GET)
    public @ResponseBody boolean deleteSongAndSendResult(@RequestParam String songName){
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
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
    
    @RequestMapping (value="/getAllMusic", method = RequestMethod.GET)
    public @ResponseBody List<Song> sendAllSongsList(){
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
        User user = userService.getUserByUsername(loggedInUserName);
        return user.getSongs();
    }
}
