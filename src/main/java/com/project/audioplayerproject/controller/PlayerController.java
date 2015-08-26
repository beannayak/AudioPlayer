/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author binayak
 */
@Controller
@RequestMapping ("/player")
public class PlayerController {
    @RequestMapping (value="/home", method=RequestMethod.GET)
    public @ResponseBody String playerLibrary(){
        return "not implemented yet";
    }
}
