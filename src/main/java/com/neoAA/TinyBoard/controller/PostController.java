package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String post(Model model, @RequestParam(required = true) Long id){
        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post);

        return "post/post";
    }

    @GetMapping("/form")
    public String postForm(Model model, @RequestParam(required = false) Long id){
        if(id==null){
            model.addAttribute("post", new Post());
        } else {
            Post post = postRepository.findById(id).orElse(null);
            model.addAttribute("post", post);
        }
        return "post/post-form";
    }

    @PostMapping("/form")
    public String form(@ModelAttribute Post post){
        postRepository.save(post);
        return "redirect:/";
    }
}
