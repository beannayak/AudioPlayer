/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author binayak
 */
public class AddSong {
    @NotBlank
    private String title;
    
    private String artist;
    private String album;
    
    MultipartFile song;
    MultipartFile albumCover;

    public AddSong() {
    }

    public AddSong(String title, String artist, String album, MultipartFile song, MultipartFile albumCover) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.song = song;
        this.albumCover = albumCover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public MultipartFile getSong() {
        return song;
    }

    public void setSong(MultipartFile song) {
        this.song = song;
    }

    public MultipartFile getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(MultipartFile albumCover) {
        this.albumCover = albumCover;
    }
}
