package com.neoAA.TinyBoard.repository;

import com.neoAA.TinyBoard.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    List<Post> findByTitleOrContent(String title, String content);
    Post findByUserId(Long user_id);
}
