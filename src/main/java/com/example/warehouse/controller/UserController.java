package com.example.warehouse.controller;

import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.service.UserService;
import com.example.warehouse.utility.ResponseStructure;
import com.example.warehouse.utility.RestResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/users")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody UserRegistrationRequest urr) {
        UserResponse userResponse = userService.addUser(urr);
        return RestResponseBuilder.ok("User registered successfully", userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseStructure<UserResponse>> findUser(@RequestParam String id) {
        UserResponse userResponse = userService.findUser(id);
        return RestResponseBuilder.ok("Found user", userResponse, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(
            @RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserResponse userResponse = userService.updateUser(userRegistrationRequest);
        return RestResponseBuilder.ok("User updated successfully", userResponse, HttpStatus.OK);
    }
}
