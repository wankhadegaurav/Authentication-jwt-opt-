package com.jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.securities.jwtfilter;
import com.jwt.services.userservice;

@Configuration
@EnableWebSecurity
public class securityconfig 
{
     @Autowired
    private userservice userservice;
  
    @Autowired
    private jwtfilter jwtfilter;


    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF protection
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/authenticate").permitAll() 
                        .requestMatchers("/register").permitAll()
                        .anyRequest().authenticated() 
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        
        http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
        return http.build(); 
    }

      @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userservice); 
        return authenticationManagerBuilder.build();
    }
   


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
