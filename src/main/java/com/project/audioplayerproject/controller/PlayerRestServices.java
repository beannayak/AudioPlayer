/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.controller;

import com.project.audioplayerproject.domain.Song;
import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.other.ResourceNotFoundException;
import com.project.audioplayerproject.service.SongService;
import com.project.audioplayerproject.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
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

/**
 *
 * @author binayak
 */
@Controller
@RequestMapping ("/api")
public class PlayerRestServices {
    Authentication auth;
    
    @Autowired
    private UserService us;
    
    @Autowired
    private SongService ss;
    
    @RequestMapping("/getSong/{song}.mp3")
    public @ResponseBody
    byte[] demoMp3(@PathVariable String song, HttpServletRequest request, HttpServletResponse response) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
        response.setHeader("Content-Type", "audio/mpeg");
        System.out.println(song);
        try {
            InputStream in = new FileInputStream("/home/binayak/Desktop/songs/" + loggedInUserName + "/" + song + ".mp3");
            return IOUtils.toByteArray(in);
        } catch (IOException e){
            System.out.println(e.getMessage());
            throw new ResourceNotFoundException();
        }
    }
    
    private String getImageFormat (String filename){
        File file = new File(filename);
        ImageInputStream iis = null;
        try {
            iis = ImageIO.createImageInputStream(file);
        } catch (IOException ex) {
            Logger.getLogger(PlayerRestServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (iis == null){
            return "";
        }
        
        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
        
        if (!iter.hasNext()) {
            System.out.println("No image found");
            return "";
        }

        ImageReader reader = iter.next();
        try {
            iis.close();
        } catch (IOException ex) {
            Logger.getLogger(PlayerRestServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        String formatName = "";
        try {
            formatName = "image/" + reader.getFormatName();
        } catch (IOException ex) {
            Logger.getLogger(PlayerRestServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return formatName;
    }
    
    @RequestMapping("/getImage/{image}.jpg")
    public @ResponseBody
    byte[] sendImage(@PathVariable String image, HttpServletRequest request, HttpServletResponse response, Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        String fileName = "/home/binayak/Desktop/songs/" + loggedInUserName + "/" + image + ".jpg";
        
        response.setHeader("Content-Type", "image/jpeg");
        
        try {
            InputStream in = new FileInputStream(fileName);
            return IOUtils.toByteArray(in);
        } catch (IOException e){
            System.out.println(e.getMessage());
            throw new ResourceNotFoundException();
        }
    }
    
    @RequestMapping("/getSongsList")
    public @ResponseBody List<Song> songList(HttpServletRequest request, HttpServletResponse response) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
        User user = us.getUserByUsername(loggedInUserName);
        return user.getSongs();
    }
    
    @RequestMapping (value = "/deleteSong", method = RequestMethod.GET)
    public @ResponseBody boolean deleteSong(@RequestParam String songName){
        
        System.out.println("number value is: " + songName + ":" + songName.replace("S", ""));
        
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        String imageFile = "/home/binayak/Desktop/songs/" + loggedInUserName + "/" + songName.replace("S", "I") + ".jpg";
        String songFile = "/home/binayak/Desktop/songs/" + loggedInUserName + "/" + songName + ".mp3";
        
        boolean flag;
        try {
            File file = new File(imageFile);
            if (!file.exists())
                return false;
            file.delete();
                
            file = new File(songFile);
            if (!file.exists())
                return false;
            flag = file.delete();
        } catch (Exception e){
            System.out.println("Exception occured");
            return false;
        }
        
        User user = us.getUserByUsername(loggedInUserName);
        Song song = ss.getSongByLocation(songName);
             
        user.getSongs().remove(song);
        us.update(user);
        ss.delete(song);
        
        return flag;
    }
    
    @RequestMapping (value="/getAllMusic", method = RequestMethod.GET)
    public @ResponseBody List<Song> getAllSongs(){
        auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = auth.getName();
        
        User user = us.getUserByUsername(loggedInUserName);
        return user.getSongs();
    }
}
