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
import java.io.File;
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

    Authentication auth;
    
    @Autowired
    private UserService us;
    
    @Autowired
    private SongService ss;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String playerLibrary(Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        model.addAttribute("songs", us.getUserByUsername(loggedInUserName).getSongs());
        return "Player";
    }

    @RequestMapping(value = "/saveSong", method = RequestMethod.GET)
    public String saveSongGet(@ModelAttribute AddSong addSong) {
        return "AddSong";
    }

    @RequestMapping(value = "/numberOfSongs", method = RequestMethod.GET)
    public @ResponseBody String saveSongPost() {
        return Long.toString(ss.getTotalSongCount());
    }
    
    @RequestMapping(value = "/saveSong", method = RequestMethod.POST)
    public String saveSongPost(@ModelAttribute @Valid AddSong addSong, BindingResult bindingResult, Model model) {
        System.out.println(addSong.getTitle());

        if (bindingResult.hasErrors()) {
            model.addAttribute("addSong", addSong);
            return "AddSong";
        }

        long nextVal = ss.getLastInserted() + 1;
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        User user = us.getUserByUsername(loggedInUserName);
        
        /******************************/
        System.out.println("User is: " + user.getUsername() + ", Phone: " + user.getPhone());
        /******************************/
        
        Song song = new Song(0, addSong.getTitle(), addSong.getArtist(), addSong.getAlbum(), ("S" + Long.toString(nextVal)));
        ss.save(song);
        
        user.addSongs(song);
        
        us.update(user);
        
        try {
            addSong.getSong().transferTo(new File("/home/binayak/Desktop/songs/" + loggedInUserName + "/S" + Long.toString(nextVal) + ".mp3"));
            addSong.getAlbumCover().transferTo(new File("/home/binayak/Desktop/songs/" + loggedInUserName + "/I" + Long.toString(nextVal) + ".jpg"));
        } catch (Exception e) {
            return "403";
        }

        return "redirect:/player/home";
    }

    
    
}
