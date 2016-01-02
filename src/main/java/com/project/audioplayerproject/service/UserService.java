package com.project.audioplayerproject.service;

import com.project.audioplayerproject.domain.NewUser;
import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.domain.UserCredientials;
import com.project.audioplayerproject.repository.UserCredientialsDao;
import com.project.audioplayerproject.repository.UserDao;
import com.project.audioplayerproject.utilities.FileSystemManager;
import org.mindrot.jbcrypt.BCrypt;
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
    private UserDao userDao;
    
    @Autowired
    private UserCredientialsDao userCredentialsDao;
    
    public void save(User user){
        userDao.persist(user);
    }
    
    public User getUserById(long id){
        return userDao.getUserById(id);
    }
    
    public User getUserByUsername (String username){
        return userDao.getUserByUsername(username);
    }
    
    public User update(User user){
        return userDao.update(user);
    }
    
    public boolean isUserNameAlreadyTaken(String userName){
        return userDao.getUserByUsername(userName) != null;
    }

    public void addNewUser(NewUser newUser) {
        boolean userDirCreated = fileSystemManager.createUserDirectoryAndReturnResult(newUser.getUserName());
        if (userDirCreated) {
            User user = new User();
            user.setId(0);
            user.setUsername(newUser.getUserName());
            user.setEmail(newUser.getEmail());
            user.setPhone(null);
            save(user);
            
            UserCredientials userCredentials = new UserCredientials();
            userCredentials.setUserName(newUser.getUserName());
            userCredentials.setPasswordHash(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
            userCredentials.setRoles("ROLE_USER");
            userCredentialsDao.persist(userCredentials);
        }
    }
}
