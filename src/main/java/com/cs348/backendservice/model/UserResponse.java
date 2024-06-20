package com.cs348.backendservice.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(nullable = false, length = 16)
    private String username;

    @Column(nullable = false, length = 16)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 10)
    private String phone;

    @Column(nullable = false, length = 10)
    private String identity;

    @Column(nullable = false)
    private Integer booking_num;

    @Column(nullable = false)
    private Boolean is_accessible;
}
