package com.neoAA.TinyBoard.Service;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.model.Role;
import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.PostRepository;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post save(String username, Post post){
        User user = userRepository.findByUsername(username);
        post.setUser(user);
        return postRepository.save(post);
    }

    public boolean isOwner(Authentication authentication, Post post){
        boolean isOwner = false;
        //사용자가 로그인 하지 않은 경우 글 작성자와 현재 로그인한 사용자가 동일한지 검증하지 않음
        if (authentication!=null && post!=null){
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            isOwner = Objects.equals(user.getId(), post.getUser().getId());
        }

        return isOwner;
    }
}
