/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.audioplayerproject.other;

import com.project.audioplayerproject.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private SessionFactory sf;

    public CustomAuthenticationProvider() {
    }

    public CustomAuthenticationProvider(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        Session session;
        User user = null;
        try {
            session = sf.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from User where userName = :uname");
            query.setParameter("uname", name);
            
            user = (User) query.list().get(0);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }

        if (name.equals("aUser") && password.equals("aUser")) {
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (name.equals("linapp") && password.equals("linapp")) {
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (user != null) {
            if (password.equals("123")) {
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        } else {
            throw new BadCredentialsException("Bad Credentials");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
