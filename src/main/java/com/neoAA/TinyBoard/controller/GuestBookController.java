package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.GuestBook;
import com.neoAA.TinyBoard.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guest")
public class GuestBookController {

    @Autowired
    private GuestRepository guestRepository;

    @GetMapping
    public String guestBook(Model model){
        List<GuestBook> mentions = guestRepository.findAll();
        model.addAttribute("mentions", mentions);
        return "guest";
    }
}
