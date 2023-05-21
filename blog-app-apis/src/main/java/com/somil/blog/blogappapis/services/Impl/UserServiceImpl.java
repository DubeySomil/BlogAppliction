package com.somil.blog.blogappapis.services.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somil.blog.blogappapis.exception.*;
import com.somil.blog.blogappapis.entities.User;
import com.somil.blog.blogappapis.payloads.UserDto;
import com.somil.blog.blogappapis.repositories.UserRepo;
import com.somil.blog.blogappapis.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto creatUser(UserDto userDto) {
        User user = this.DtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.UserToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
        		.orElseThrow(()-> new ResourceNotFoundException("User" ," ID ", userId));
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        
        User updatedUser = this.userRepo.save(user);
        return this.UserToDto(updatedUser);
        
    }

    @Override
    public UserDto getUserById(Integer userId) {
    	User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " ID ", userId));
    	return UserToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtoList = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteuser(Integer userId) {
    	User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " ID ", userId));
    	this.userRepo.delete(user);
    }
    

    public User DtoToUser(UserDto user){
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setAbout(user.getAbout());
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    public UserDto UserToDto(User user){
        UserDto newUser = new UserDto();
        newUser.setId(user.getId());
        newUser.setAbout(user.getAbout());
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        return newUser;
    }
}
