package com.amit.UserServiceApplication.repository;

import com.amit.UserServiceApplication.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByContact(String contact);
}
