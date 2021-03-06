package com.project.audioplayerproject.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String email;
    
    private String phone;
    
    @OneToMany (mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Song> songs;
    
    @OneToMany (mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Playlist> playlists;

    public User() {
    }

    public User(long id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        songs = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSongs(Song song) {
        this.songs.add(song);
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
    
    public void addPlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }
    
    public void removeSong(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
        }
    }
    
    public void removePlaylist (Playlist playlist) {
        if (playlists.contains(playlist)) {
            playlists.remove(playlist);
        }
    }
}
