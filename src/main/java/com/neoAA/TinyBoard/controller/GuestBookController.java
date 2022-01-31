package com.neoAA.TinyBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class GuestBookController {

    @GetMapping
    public String guestBook(Model model){
        return "guest";
    }
}
