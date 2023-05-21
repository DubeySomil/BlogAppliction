package com.somil.blog.blogappapis.services;

import java.util.List;

import com.somil.blog.blogappapis.payloads.UserDto;

public interface UserService {
    
    UserDto creatUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer UserId);
    List<UserDto> getAllUsers();
    void deleteuser(Integer userID);
}
