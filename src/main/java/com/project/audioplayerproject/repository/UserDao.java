/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.repository;

import com.project.audioplayerproject.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author binayak
 */
@Repository
public class UserDao {
    @Autowired
    private SessionFactory sf;
    
    public void persist(User user){
        sf.getCurrentSession().persist(user);
    }
    
    public User getUserById(long id){
        return (User) sf.getCurrentSession().load(User.class, id);
    }
    
    public User getUserByUsername(String username){
        Query query = sf.getCurrentSession().createQuery("SELECT u FROM User u WHERE u.username = :name");
        query.setParameter("name", username);
        if (query.list().size() < 1){
            return null;
        }
        return (User) query.list().get(0);
    }
    
    public User update(User user){
        return (User) sf.getCurrentSession().merge(user);
    }
}
