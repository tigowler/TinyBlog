package com.neoAA.TinyBoard.repository;

import com.neoAA.TinyBoard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
