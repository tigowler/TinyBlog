package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.GuestBook;
import com.neoAA.TinyBoard.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        List<GuestBook> mentions = guestRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        model.addAttribute("mentions", mentions);

        return "guest/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        if(id==null){
            model.addAttribute("guestBook", new GuestBook());
        } else {
            GuestBook guestBook = guestRepository.findById(id).orElse(null);
            guestBook.setTime(Timestamp.valueOf(LocalDateTime.now()));
            model.addAttribute("guestBook", guestBook);
        }
        return "guest/form";
    }

    @PostMapping("/form")
    public String commentsSubmit(@Valid @ModelAttribute GuestBook guestBook, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "guest/form";
        }
        guestBook.setTime(Timestamp.valueOf(LocalDateTime.now()));
        guestRepository.save(guestBook);
        return "redirect:/guest";
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id){
        guestRepository.deleteById(id);
        return "redirect:/guest";
    }
}
