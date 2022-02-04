package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @GetMapping("/form")
    public String postForm(Model model){
        return "post/post-form";
    }
}
