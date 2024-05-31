package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetStatus {

    @PostMapping("/getStatus")
    public ResponseEntity<UserResponse> getStatus(@RequestBody User user) {
        if ("12345".equals(user.getUserId())) {
            UserResponse response = new UserResponse(user.getUserId(), "Active", "Name1", 30);
            return ResponseEntity.ok(response);
        } else {
            UserResponse response = new UserResponse(user.getUserId(), "Not recognized", "Unknown", 0);
            return ResponseEntity.ok(response);
        }
    }

}
