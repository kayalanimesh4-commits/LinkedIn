package com.linkedIn.userservice.repository;

import com.linkedIn.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean findByEmail(String email);
}
