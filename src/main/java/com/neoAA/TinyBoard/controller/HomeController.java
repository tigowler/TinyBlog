package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping
    public String index(Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }

}
