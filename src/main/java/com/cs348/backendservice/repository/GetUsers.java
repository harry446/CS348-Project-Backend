package com.cs348.backendservice.repository;

import com.cs348.backendservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetUsers extends JpaRepository<User, Integer> {
    // This interface now has methods like findAll(), findById(), save(), deleteById(), etc.
}
