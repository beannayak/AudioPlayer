/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.utilities;

/**
 *
 * @author binayak
 */
public class AllUtilitiesProperties {
    public static final String SLASHES_DEPENDING_ON_OS = "/";
    public static final String BASE_LOCATION_WITH_APPROPRIATE_SLASHES_BUT_NOT_IN_END = "/home/binayak/Desktop/songs";
    
    private static final String LOGGED_IN_USER = "%s";
    private static final String SONG_NAME_OR_IMAGE_NAME = "%s";
   
    public static final String SONG_STRING_FORMAT = 
             BASE_LOCATION_WITH_APPROPRIATE_SLASHES_BUT_NOT_IN_END + SLASHES_DEPENDING_ON_OS + 
            LOGGED_IN_USER + SLASHES_DEPENDING_ON_OS + SONG_NAME_OR_IMAGE_NAME + ".mp3";
    
    public static final String IMAGE_STRING_FORMAT = 
             BASE_LOCATION_WITH_APPROPRIATE_SLASHES_BUT_NOT_IN_END + SLASHES_DEPENDING_ON_OS + 
            LOGGED_IN_USER + SLASHES_DEPENDING_ON_OS + SONG_NAME_OR_IMAGE_NAME + ".jpg";
}
