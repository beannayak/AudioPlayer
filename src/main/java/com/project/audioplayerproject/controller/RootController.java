package com.project.audioplayerproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping ("/")
public class RootController {
    @RequestMapping (value="", method=RequestMethod.GET)
    public String rootController(){
        return "SplashScreen";
    }
      
    @RequestMapping (value="/403", method=RequestMethod.GET)
    public String error(){
        return "403";
    }
    
    @RequestMapping (value="/AuthenticationError", method=RequestMethod.GET)
    public String authenticationError(){
        return "AuthenticationError";
    }
}
