package com.project.audioplayerproject.utilities;

import com.project.audioplayerproject.other.ResourceNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class ImageAndSongRetrieverUtility {
    public static final String SONG_STRING_FORMAT = AllUtilitiesProperties.SONG_STRING_FORMAT;
    public static final String IMAGE_STRING_FORMAT = AllUtilitiesProperties.IMAGE_STRING_FORMAT;
    
    public byte[] getSongWithLoggedInUserAndSongName (String loggedInUserName, String songName) 
            throws ResourceNotFoundException{
        try {
            String filePath = String.format(SONG_STRING_FORMAT, loggedInUserName, songName);
            System.out.println("song: " + filePath);
            InputStream in = new FileInputStream(filePath);
            return IOUtils.toByteArray(in);
        } catch (IOException e){
            System.out.println(e.getMessage());
            throw new ResourceNotFoundException();
        }
    }
    
    public byte[] getImageWithLoggedInUserAndImageName (String loggedInUserName, String imageName){
        String filePath = String.format(IMAGE_STRING_FORMAT, loggedInUserName, imageName);
        System.out.println("image: " + filePath);
        try {
            InputStream in = new FileInputStream(filePath);
            return IOUtils.toByteArray(in);
        } catch (IOException e){
            throw new ResourceNotFoundException();
        }
    }
}
