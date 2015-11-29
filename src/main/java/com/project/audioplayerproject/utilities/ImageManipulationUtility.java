/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.utilities;

import com.project.audioplayerproject.controller.PlayerRestServices;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author binayak
 */
public class ImageManipulationUtility {
    
    public String getImageFormat (String filename){
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
}
