package com.cs348.backendservice.controller;

import com.cs348.backendservice.exceptions.UserNotFoundException;
import com.cs348.backendservice.exceptions.WrongPasswordException;
import com.cs348.backendservice.model.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cs348.backendservice.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService l;

    @GetMapping("/login")
    @Operation(summary = "Login", description = "User logs into their account by providing username and password.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Logged in successfully",
                            content = @Content(mediaType = "String",
                                    examples = @ExampleObject(value = "password is correct"))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "String",
                                    examples = @ExampleObject(value = "Username not found: username1"))),
                    @ApiResponse(responseCode = "401", description = "Wrong username or password",
                            content = @Content(mediaType = "String",
                                    examples = @ExampleObject(value = "Wrong username or password")))
            })
    public ResponseEntity<LoginResponse> loginHandler(@RequestParam String username, @RequestParam String password) {
        try {
            LoginResponse r = l.auth(username, password);
            return new ResponseEntity(r, HttpStatus.OK);
        } catch (UserNotFoundException notFoundException) {
            return new ResponseEntity(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (WrongPasswordException wrongPasswordException) {
            return new ResponseEntity(wrongPasswordException.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
