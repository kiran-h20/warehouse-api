package com.example.warehouse.mapper;

import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.entity.User;
import com.example.warehouse.enums.UserRole;


import java.util.Locale;

public class UserMapper{

    public User userToEntity(UserRegistrationRequest source, User target){
        target.setUsername(source.username());
        target.setEmail(source.email());
        target.setPassword(source.password());
        target.setUserRole(source.userRole());//problem

        return target;
    }

    public UserResponse userToResponse(User user){
        return new UserResponse(user.getUserId(),user.getUsername(),user.getEmail(),user.getUserRole().name(),user.getCreatedAt().toEpochMilli(),user.getLastModifiedAt().toEpochMilli());//problem
    }

}
