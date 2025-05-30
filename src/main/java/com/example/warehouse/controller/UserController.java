package com.example.warehouse.controller;

import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.entity.User;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public void addUser(@RequestBody UserRegistrationRequest urr){
        userService.addUser(urr);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findById(@RequestParam String userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUserById(
            @RequestParam String userId,
            @RequestBody User updatedUser) {

        Optional<User> existingUserOpt = userService.findById(userId);

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found");
        }

        User existingUser = existingUserOpt.get();

        // Example update logic (customize as per your User fields)
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        User savedUser = userService.updateById(existingUser);

        return ResponseEntity.ok(savedUser);
    }


}
