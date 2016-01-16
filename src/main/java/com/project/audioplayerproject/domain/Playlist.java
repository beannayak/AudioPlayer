package com.project.audioplayerproject.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Playlist {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    
    @NotEmpty
    private String name;
    
    @OneToMany
    @JsonIgnore
    private List<Song> songs;
    
    @ManyToOne 
    @JsonIgnore
    private User user;

    public Playlist() {
        songs = new ArrayList<>();
    }

    public Playlist(long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.songs = new ArrayList<>();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    
    public void addSong(Song song){
        this.songs.add(song);
    }
}
