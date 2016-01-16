package com.project.audioplayerproject.utilities;

import com.project.audioplayerproject.domain.AddSong;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageAndSongSaveUtility {
    public static final String SONG_FORMAT = AllUtilitiesProperties.SONG_STRING_FORMAT;
    public static final String IMAGE_FORMAT = AllUtilitiesProperties.IMAGE_STRING_FORMAT;
    
    public boolean saveImageAndSongToFileSystem(AddSong addSong, String loggedInUserName, long nextVal) {
        String songName = "S" + Long.toString(nextVal);
        String imageName = "I" + Long.toString(nextVal);
        String songFileAndLocation = String.format(SONG_FORMAT, loggedInUserName, songName);
        String imageFileAndLocation = String.format(IMAGE_FORMAT, loggedInUserName, imageName);
        
        try {
            addSong.getSong().transferTo(new File(songFileAndLocation));
            addSong.getAlbumCover().transferTo(new File(imageFileAndLocation));
        } catch (IOException | IllegalStateException e ) {
            return false;
        } 
        return true;
    }
    
    public boolean saveImageOnly(MultipartFile albumArt, String loggedInUserName, long songNumber){
        String imageName = "I" + Long.toString(songNumber);
        String imageFileAndLocation = String.format(IMAGE_FORMAT, loggedInUserName, imageName);
        try {
            albumArt.transferTo(new File(imageFileAndLocation));
        } catch (IOException | IllegalStateException e ) {
            return false;
        } 
        return true;
    }
}
