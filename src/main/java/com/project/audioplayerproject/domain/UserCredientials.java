/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author binayak
 */
@Entity
public class UserCredientials implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    
    private String userName;
    private String passwordHash;
    private String roles;

    public UserCredientials() {
    }

    public UserCredientials(int id, String userName, String passwordHash, String roles) {
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
