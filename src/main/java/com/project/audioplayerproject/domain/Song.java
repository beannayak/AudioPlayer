package com.project.audioplayerproject.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Song implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    
    @NotBlank
    private String title;
    
    private String artist;
    private String album;
    private String location;
    
    @ManyToMany (mappedBy = "songs", cascade = CascadeType.DETACH)
    @JsonIgnore
    private List<Playlist> playlists;
    
    @ManyToOne
    @JsonIgnore
    private User user;

    public Song() {
    }

    public Song(long id, String title, String artist, String album, String location, User user) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
        
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
    
    public void removePlaylist(Playlist playlist) {
        if (playlists.contains(playlist)){
            playlists.remove(playlist);
        }
    }
}
