package com.neoAA.TinyBoard.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@Configuration
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index(){
        return "index";
    }


}
