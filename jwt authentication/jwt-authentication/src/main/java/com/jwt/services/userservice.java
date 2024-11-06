package com.jwt.services;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.entities.User;
import com.jwt.repositories.userrepositories;

@Service
public class userservice implements UserDetailsService
 {
    @Autowired
    private userrepositories userrepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user=userrepositories.findByUsername(username);
         if(user==null){
            new UsernameNotFoundException("user  not found");
         }
         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        
    }
    
}
