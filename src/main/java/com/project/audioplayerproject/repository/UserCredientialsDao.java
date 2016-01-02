/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.repository;

import com.project.audioplayerproject.domain.User;
import com.project.audioplayerproject.domain.UserCredientials;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author binayak
 */
@Repository
@Transactional (propagation = Propagation.MANDATORY)
public class UserCredientialsDao {
    @Autowired
    private SessionFactory sf;
    
    public void persist(UserCredientials userCredentials){
        sf.getCurrentSession().persist(userCredentials);
    }
    
    public UserCredientials getUserCredentialsById(long id){
        return (UserCredientials) sf.getCurrentSession().load(UserCredientials.class, id);
    }
    
    public UserCredientials getUserByUsername(String username){
        Query query = sf.getCurrentSession().createQuery("SELECT u FROM UserCredientials u WHERE u.userName = :name");
        query.setParameter("name", username);
        if (query.list().size() < 1){
            return null;
        }
        return (UserCredientials) query.list().get(0);
    }
    
    public UserCredientials update(UserCredientials userCredentials){
        return (UserCredientials) sf.getCurrentSession().merge(userCredentials);
    }
}
