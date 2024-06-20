package com.cs348.backendservice.repository;

import com.cs348.backendservice.model.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetUsers extends JpaRepository<UserResponse, Integer> {
    // This interface now has methods like findAll(), findById(), save(), deleteById(), etc.
}
