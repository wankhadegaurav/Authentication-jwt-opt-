package com.jwt.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.entities.User;
import com.jwt.repositories.userrepositories;
import com.jwt.securities.jwtutil;

@RestController
public class usercontroller 
{
    @Autowired
    private AuthenticationManager authenticationManager;
   
    @Autowired
    private  jwtutil jwtutil;

@Autowired
   private userrepositories userrepositories;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userrepositories.save(user);
         return userrepositories.save(user);
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<String>  userlogin(@RequestBody User user)
    {
       try{
     
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
     
        String jwt=jwtutil.generateToken(authentication.getName());
        return ResponseEntity.ok(jwt);
    }
    catch(Exception  e){
        return ResponseEntity.status(404).body("Invalid user");
    }
       
    }
    
    @GetMapping("/profile")
    public ResponseEntity<String> profile(){
        return ResponseEntity.ok("profile page");
    }
    
}
