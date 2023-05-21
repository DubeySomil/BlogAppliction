package com.somil.blog.blogappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somil.blog.blogappapis.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
    
}
