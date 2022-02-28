package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Configuration
@RequestMapping
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/setting")
    public String userSetting(Model model, Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName());
        model.addAttribute("posts", user.getPost());
        model.addAttribute("comments", user.getGuestBooks());
        model.addAttribute("postsLoved", user.getPostsLoved());
        return "user-setting";
    }

}
