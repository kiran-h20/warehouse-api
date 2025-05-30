package com.example.warehouse.service;

import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    UserResponse addUser(UserRegistrationRequest user);
    UserResponse findUser(String userId);
    UserResponse updateUser(String userId, User updatedUser);
}
