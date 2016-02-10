/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/rhythm")
public class RhythmController {
    Authentication authentication;
    
    @RequestMapping("/show")
    public String showRhythm(Model model){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName();
        model.addAttribute("loggedInUser", loggedInUserName);
        return "rhythm";
    }
    
    @RequestMapping (value = "/testCheckBox", method = RequestMethod.GET) 
    public String showCheckBox(){
        return "getSomethingFromCheckBox";
    }
    
    @RequestMapping (value = "/testCheckBox", method = RequestMethod.POST) 
    public String getCheckBox(@RequestParam(required = false) String checkboxText){
        System.out.println(checkboxText);
        return "redirect:/rhythm/getSomethingFromCheckBox";
    }
}
