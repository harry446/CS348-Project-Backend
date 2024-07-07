package com.cs348.backendservice.service;

import com.cs348.backendservice.exceptions.WrongPasswordException;
import com.cs348.backendservice.repository.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    @Autowired
    private Login l;

    public void auth(String username, String password) throws Exception {
        try {
            String truePass = l.retrievePass(username);
            if (!truePass.equals(password)) {
                throw new WrongPasswordException("Wrong username or password");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

