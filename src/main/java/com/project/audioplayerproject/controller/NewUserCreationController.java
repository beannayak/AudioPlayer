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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usercreation")
public class NewUserCreationController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping("/new")
    public String createNewUser (NewUser newUser, Model model) {
        String message = null;
        if (!userService.isUserNameAlreadyTaken(newUser.getUserName()) 
                && "3792".equals(newUser.getInvitationCode())){
            userService.addNewUser(newUser);
            return "Player";
        } else if (userService.isUserNameAlreadyTaken(newUser.getUserName())){
            message = "Username is already taken"; 
        } else if (!"3792".equals(newUser.getInvitationCode())) {
            message = "Invalid invitation code";
        }
        model.addAttribute("message", message);
        return "AuthenticationError";
    }
}
