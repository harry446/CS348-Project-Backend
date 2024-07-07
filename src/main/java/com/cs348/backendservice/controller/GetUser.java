package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.*;
import com.cs348.backendservice.repository.GetUsers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Configurable;
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
    @Operation(summary = "Fetch all users", description = "Retrieve a list of all users from the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class),
                            examples = @ExampleObject(value = "[\n" +
                                    "    {\n" +
                                    "        \"uid\": 1,\n" +
                                    "        \"username\": \"admin01\",\n" +
                                    "        \"password\": \"iamadmin\",\n" +
                                    "        \"email\": \"admin@gmail.com\",\n" +
                                    "        \"phone\": \"1231231234\",\n" +
                                    "        \"identity\": \"admin\",\n" +
                                    "        \"booking_num\": 0,\n" +
                                    "        \"is_accessible\": false\n" +
                                    "    },\n" +
                                    "    {\n" +
                                    "        \"uid\": 2,\n" +
                                    "        \"username\": \"adfjhk\",\n" +
                                    "        \"password\": \"123123\",\n" +
                                    "        \"email\": \"adjgkhj@gmail.com\",\n" +
                                    "        \"phone\": \"1234567890\",\n" +
                                    "        \"identity\": \"student\",\n" +
                                    "        \"booking_num\": 0,\n" +
                                    "        \"is_accessible\": false\n" +
                                    "    }\n" +
                                    "]")))
            })
    public ResponseEntity<List<UserResponse>> getUser() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);  // SELECT * FROM users
    }

}
