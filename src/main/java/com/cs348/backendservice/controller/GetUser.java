package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.*;
import com.cs348.backendservice.repository.GetUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class GetUser {

    @Autowired
    private GetUsers userRepository;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserResponse>> getUser() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);  // SELECT * FROM users
    }

}
