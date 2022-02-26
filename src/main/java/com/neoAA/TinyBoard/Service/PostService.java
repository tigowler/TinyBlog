package com.neoAA.TinyBoard.Service;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.PostRepository;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
