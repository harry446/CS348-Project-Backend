package com.cs348.backendservice.controller;

import com.cs348.backendservice.exceptions.UserNotFoundException;
import com.cs348.backendservice.exceptions.UsernameExistsException;
import com.cs348.backendservice.exceptions.WrongPasswordException;
import com.cs348.backendservice.model.CreateAccountRequest;
import com.cs348.backendservice.repository.CreateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAccountController {
    @Autowired
    private CreateAccount createAccount;

    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccountHandler(@RequestBody CreateAccountRequest accountRequest) {

        try {
            createAccount.insertRow(accountRequest.getUsername(), accountRequest.getPassword(), accountRequest.getEmail(),
                    accountRequest.getPhone(), accountRequest.getIdentity(), accountRequest.is_accessible());
        } catch (UsernameExistsException existsException) {
            return new ResponseEntity(existsException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity("Account created for user " + accountRequest.getUsername(), HttpStatus.OK);
    }
}

