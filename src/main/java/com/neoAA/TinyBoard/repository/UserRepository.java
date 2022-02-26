package com.neoAA.TinyBoard.repository;

import com.neoAA.TinyBoard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
