/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.controller;

import com.project.audioplayerproject.domain.AddSong;
import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.service.SongService;
import com.project.audioplayerproject.service.UserService;
import com.project.audioplayerproject.utilities.ImageAndSongSaveUtility;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author binayak
 */
@Controller
@RequestMapping("/player")
public class PlayerController {

    Authentication authentication;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ImageAndSongSaveUtility imageAndSongSaveUtility;
    
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String playerLibrary(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName();
        User loggedInUser = userService.getUserByUsername(loggedInUserName);
        if (loggedInUser != null) {
            model.addAttribute("songs", loggedInUser.getSongs());
        } else {
            model.addAttribute("songs", null);
        }
        return "Player";
    }

    @RequestMapping(value = "/saveSong", method = RequestMethod.GET)
    public String saveSongGet(@ModelAttribute AddSong addSong) {
        return "AddSong";
    }

    @RequestMapping(value = "/saveSong", method = RequestMethod.POST)
    public String saveSongPost(@ModelAttribute @Valid AddSong addSong, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addSong", addSong);
            return "AddSong";
        }

        long nextVal = songService.getLastInserted() + 1;
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName();
        User user = userService.getUserByUsername(loggedInUserName);        
        
        boolean isSongAndImageSaved = imageAndSongSaveUtility.saveImageAndSongToFileSystem(addSong, 
                loggedInUserName, nextVal);
        
        if (isSongAndImageSaved){
            saveSongAndImageValuesToDatabaseOfUser(addSong, user, nextVal);
        }
        
        //TODO: if not saved, then display error message saying its not saved
        //Make a common error page, where message can be displayed, 
        //or goto previous page and change sth again, or goto home or any other pages
        
        return "redirect:/player/home";
    }
    
    @RequestMapping(value = "/numberOfSongs", method = RequestMethod.GET)
    public @ResponseBody String saveSongPost() {
        return Long.toString(songService.getTotalSongCount());
    }

    private void saveSongAndImageValuesToDatabaseOfUser(AddSong addSong, User user, long nextVal) {
        Song song = new Song(0, addSong.getTitle(), addSong.getArtist(), addSong.getAlbum(), 
                ("S" + Long.toString(nextVal)));
        songService.save(song);
        user.addSongs(song);
        userService.update(user);
    }
}
