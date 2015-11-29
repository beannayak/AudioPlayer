package com.project.audioplayerproject.controller;

import com.project.audioplayerproject.domain.TestDomain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @RequestMapping (value="/check", method=RequestMethod.GET)
    public @ResponseBody TestDomain check(){
        TestDomain td = new TestDomain (5, "a text");
        return td;
    }
    
    @RequestMapping (value="/check", method=RequestMethod.POST)
    public @ResponseBody String testDomainGet(@RequestBody TestDomain testDomain){
        return testDomain.getText() + " got successfully";
    }
}
