package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.GuestBook;
import com.neoAA.TinyBoard.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        return "guest/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        model.addAttribute("guestBook", new GuestBook());
        return "guest/form";
    }

    @PostMapping("/form")
    public String commentsSubmit(@ModelAttribute GuestBook guestBook){
        guestBook.setTime(Timestamp.valueOf(LocalDateTime.now()));
        guestRepository.save(guestBook);
        return "redirect:/guest";
    }
}
