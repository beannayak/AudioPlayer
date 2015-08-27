/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.controller;

import com.project.audioplayerproject.domain.AddSong;
import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.other.ResourceNotFoundException;
import com.project.audioplayerproject.service.SongService;
import com.project.audioplayerproject.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String playerLibrary() {
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

        long nextVal = ss.getTotalSongCount() + 1;
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

    @RequestMapping("/songCache/{song}.mp3")
    public @ResponseBody
    byte[] demoMp3(@PathVariable String song, HttpServletRequest request, HttpServletResponse response) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
        response.setHeader("Content-Type", "audio/mpeg");
        try {
            InputStream in = new FileInputStream("/home/binayak/Desktop/songs/" + loggedInUserName + "/" + song + ".mp3");
            return IOUtils.toByteArray(in);
        } catch (IOException e){
            System.out.println(e.getMessage());
            throw new ResourceNotFoundException();
        }
    }
}
