/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.controller;

import com.project.audioplayerproject.domain.NewUser;
import com.project.audioplayerproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usercreation")
public class NewUserCreationController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping("/new")
    public String createNewUser (NewUser newUser) {
        if (!userService.isUserNameAlreadyTaken(newUser.getUserName()) 
                && newUser.getInvitationCode().equals("3792")){
            userService.addNewUser(newUser);
            return "Player";
        }
        
        return "SplashScreen";
    }
}
