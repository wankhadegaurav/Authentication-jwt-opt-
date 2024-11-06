package com.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entities.User;



@Repository 
public interface userrepositories extends  JpaRepository<User,Integer>
{
   User findByUsername(String username);
}
