package com.cs348.backendservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userId;

    public User() {
    }
    public User(String userId) {
        this.userId = userId;
    }

}
