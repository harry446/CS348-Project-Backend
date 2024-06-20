package com.cs348.backendservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private int userId;
    private String status;
    private String name;
    private int age;

    public UserResponse(int userId, String status, String name, int age) {
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.age = age;
    }

}

