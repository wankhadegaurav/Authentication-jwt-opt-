package com.jwt.securities;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.services.userservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtfilter extends OncePerRequestFilter
{
    @Autowired
    private jwtutil jwtutil;
    
    @Autowired
    private userservice userservice;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            String authorizationtoken=request.getHeader("Authorization");

            String username=null;
            String jtwtoken=null;

            if(authorizationtoken!=null&&authorizationtoken.startsWith("Bearer"))
            {
                jtwtoken=authorizationtoken.substring(7);
                username=jwtutil.extractUsername(jtwtoken);
               
            }

            if(authorizationtoken!=null&&SecurityContextHolder.getContext().getAuthentication()==null )
            {
                 UserDetails UserDetails=userservice.loadUserByUsername(username);
                 if(jwtutil.validateToken(jtwtoken, UserDetails.getUsername())){
                    UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(UserDetails,null,UserDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);

                 }  

            }
            filterChain.doFilter(request, response);
            
            
              
    }
    
    
}
