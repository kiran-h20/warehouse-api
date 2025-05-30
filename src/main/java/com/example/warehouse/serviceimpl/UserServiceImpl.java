package com.example.warehouse.serviceimpl;

import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.dto.request.UserRegistrationRequest;
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

    @Override
    public void addUser(UserRegistrationRequest urr) {
        if (urr.userRole().equals(UserRole.STAFF)){
            User u = new UserMapper().userToEntity(urr,new Staff());
            userRepository.save(u);

        }
        else if (urr.userRole().equals(UserRole.ADMIN)){
            User u = new UserMapper().userToEntity(urr,new Admin());
            userRepository.save(u);
        }
    }

    @Override
    public Optional<User> findById(String userId) {
       Optional<User> user = userRepository.findById(userId);
        return user;
    }

    @Override
    public User updateById(User existingUser) {
        userRepository.save(existingUser);
        return null;
    }


}
