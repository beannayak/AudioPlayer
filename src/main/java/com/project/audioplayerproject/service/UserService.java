/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.service;

import com.project.audioplayerproject.domain.NewUser;
import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.repository.UserDao;
import com.project.audioplayerproject.utilities.FileSystemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional (propagation = Propagation.REQUIRES_NEW)
public class UserService {
    
    @Autowired
    private FileSystemManager fileSystemManager;
    
    @Autowired
    private UserDao ud;
    
    public void save(User user){
        ud.persist(user);
    }
    
    public User getUserById(long id){
        return ud.getUserById(id);
    }
    
    public User getUserByUsername (String username){
        return ud.getUserByUsername(username);
    }
    
    public User update(User user){
        return ud.update(user);
    }
    
    public boolean isUserNameAlreadyTaken(String userName){
        return ud.getUserByUsername(userName) != null;
    }

    public void addNewUser(NewUser newUser) {
        boolean userDirCreated = fileSystemManager.createUserDirectoryAndReturnResult(newUser.getUserName());
        if (userDirCreated) {
            User user = new User();
            user.setId(0);
            user.setUsername(newUser.getUserName());
            user.setEmail(newUser.getEmail());
            user.setPhone(null);
            //TODO: addPassword to password table
            save(user);
        }
    }
}
