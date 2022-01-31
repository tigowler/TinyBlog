package com.neoAA.TinyBoard.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class HomeController {

    @GetMapping
    public String index(){
        return "index";
    }
}
