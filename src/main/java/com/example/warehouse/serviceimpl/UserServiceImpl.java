package com.example.warehouse.serviceimpl;

import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.entity.Admin;
import com.example.warehouse.entity.Staff;
import com.example.warehouse.entity.User;
import com.example.warehouse.enums.UserRole;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();

    @Override
    public UserResponse addUser(UserRegistrationRequest urr) {
        User user = switch (urr.userRole()) {
            case STAFF -> userMapper.userToEntity(urr, new Staff());
            case ADMIN -> userMapper.userToEntity(urr, new Admin());
            default -> throw new IllegalArgumentException("Invalid user role");
        };
        userRepository.save(user);
        return userMapper.userToResponse(user);
    }

    @Override
    public UserResponse findUser(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::userToResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponse updateUser(String userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (updatedUser.getUsername() != null) existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
        userRepository.save(existingUser);
        return userMapper.userToResponse(existingUser);
    }
}
