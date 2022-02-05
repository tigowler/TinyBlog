package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/form")
    public String postForm(Model model){
        model.addAttribute("post", new Post());
        return "post/post-form";
    }

    @PostMapping("/form")
    public String form(@ModelAttribute Post post){
        postRepository.save(post);
        return "redirect:/";
    }
}
