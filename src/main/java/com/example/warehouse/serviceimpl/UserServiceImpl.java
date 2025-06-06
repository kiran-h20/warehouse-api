package com.example.warehouse.serviceimpl;

import com.example.warehouse.exception.InvalidUserRole;
import com.example.warehouse.exception.UserAlreadyExistException;
import com.example.warehouse.exception.UserNotFoundException;
import com.example.warehouse.exception.UserNotLoggedInException;
import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.entity.Admin;
import com.example.warehouse.entity.Staff;
import com.example.warehouse.entity.User;
import com.example.warehouse.enums.UserRole;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.example.warehouse.security.AuthUtils.*;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse addUser(UserRegistrationRequest urr) {

        Optional<User> existingUser = userRepository.findByEmail(urr.email());

        if(existingUser.isPresent()){

            throw new UserAlreadyExistException("Email already exist");

        }

        User user = switch (urr.userRole()) {
            case STAFF -> userMapper.userToEntity(urr, new Staff());
            case ADMIN -> userMapper.userToEntity(urr, new Admin());
            default -> throw new InvalidUserRole("Invalid user role");
        };
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return userMapper.userToResponse(user);



    }

    @Override
    public UserResponse findUser(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::userToResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserResponse updateUser(UserRegistrationRequest userRegistrationRequest) {
        // Find the user to update by ID
        User existingUser = userRepository.findById(userRegistrationRequest.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Only allow the currently logged-in user to update their own details
        String currentUsername = getCurrentUserName()
                .orElseThrow(() -> new UserNotLoggedInException("user not logged in"));
        if (!existingUser.getEmail().equals(currentUsername)) {
            throw new UserNotFoundException("You are not authorized to update this user");
        }

        // Update fields if provided
        if (userRegistrationRequest.username() != null) existingUser.setUsername(userRegistrationRequest.username());
        if (userRegistrationRequest.email() != null) existingUser.setEmail(userRegistrationRequest.email());
        if (userRegistrationRequest.password() != null) existingUser.setPassword(passwordEncoder.encode(userRegistrationRequest.password()));
        userRepository.save(existingUser);
        return userMapper.userToResponse(existingUser);
    }
}
