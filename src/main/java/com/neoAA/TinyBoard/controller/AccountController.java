package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.Service.UserService;
import com.neoAA.TinyBoard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login(){
        return "account/login";
    }

    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "account/register";
    }

    @PostMapping("register")
    public String register(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "account/register";
        }
        userService.save(user);
        return "redirect:/";
    }
}
