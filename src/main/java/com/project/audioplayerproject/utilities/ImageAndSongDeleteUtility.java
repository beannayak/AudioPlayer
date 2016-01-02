package com.project.audioplayerproject.utilities;

import java.io.File;
import org.springframework.stereotype.Component;

@Component
public class ImageAndSongDeleteUtility {
    public static final String SONG_STRING_FORMAT = AllUtilitiesProperties.SONG_STRING_FORMAT;
    public static final String IMAGE_STRING_FORMAT = AllUtilitiesProperties.IMAGE_STRING_FORMAT;
    
    public boolean deleteSongAndReturnResult (String loggedInUserName, String songName){
        String songFile = String.format(SONG_STRING_FORMAT, loggedInUserName, songName);
        
        boolean flag;
        File file;
        try {
            file = new File(songFile);
            if (!file.exists())
                return false;
            flag = file.delete();
            
        } catch (Exception e){
            return false;
        }
        return flag;
    }
    
    public boolean deleteImageAssociatedWithSongAndReturnResult (String loggedInUserName, String songName){
        String imageFile = String.format(IMAGE_STRING_FORMAT, loggedInUserName, getImageNameBasedOnSongName(songName));
        
        boolean flag;
        File file;
        try {
            file = new File(imageFile);
            if (!file.exists())
                return false;
            flag = file.delete();
        } catch (Exception e){
            return false;
        }
        return flag;
    }
    
    public String getImageNameBasedOnSongName(String songName){
        return songName.replace("S", "I");
    }
}
