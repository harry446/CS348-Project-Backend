package com.cs348.backendservice.service;

import com.cs348.backendservice.exceptions.WrongPasswordException;
import com.cs348.backendservice.model.LoginResponse;
import com.cs348.backendservice.repository.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    @Autowired
    private Login l;

    public LoginResponse auth(String username, String password) throws Exception {
        try {
            int count = l.check(username, password);
            if (count == 0) {
                throw new WrongPasswordException("Wrong username or password");
            }

            return new LoginResponse(l.getUid(username));
        } catch (Exception e) {
            throw e;
        }
    }
}

