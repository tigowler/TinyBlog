package com.neoAA.TinyBoard.repository;

import com.neoAA.TinyBoard.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"post"})
    List<User> findAll();

    User findByUsername(String username);
}
