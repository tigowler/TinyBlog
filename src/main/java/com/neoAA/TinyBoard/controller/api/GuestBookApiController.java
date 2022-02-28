package com.neoAA.TinyBoard.controller.api;

import com.neoAA.TinyBoard.model.GuestBook;
import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.GuestRepository;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestBookApiController {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/guest")
    List<GuestBook> all(){
        return guestRepository.findAll();
    }

    @PostMapping("/guest")
    GuestBook newComment(@RequestBody GuestBook newGuestBook){
        return guestRepository.save(newGuestBook);
    }

    @GetMapping("/guest/{id}")
    GuestBook one(@PathVariable Long id){
        return guestRepository.findById(id).orElse(null);
    }

    @PutMapping("/guest/{id}")
    GuestBook replaceComment(@RequestBody GuestBook newGuestBook, @PathVariable Long id){
        return guestRepository.findById(id)
                .map(comment -> {
                    comment.setContent(newGuestBook.getContent());
                    comment.setTime(Timestamp.valueOf(LocalDateTime.now()));
                    return guestRepository.save(comment);
                })
                .orElseGet(()->{
                    newGuestBook.setId(id);
                    return guestRepository.save(newGuestBook);
                });
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/guest/{id}")
    void deleteComment(@PathVariable Long id){
        guestRepository.deleteById(id);
    }
}
