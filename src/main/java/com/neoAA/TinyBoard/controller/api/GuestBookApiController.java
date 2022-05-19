package com.neoAA.TinyBoard.controller.api;

import com.neoAA.TinyBoard.model.GuestBook;
import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.GuestRepository;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    //LOVE
    @Secured("ROLE_USER")
    @PutMapping("/guest/love/{id}")
    void loveComment(@PathVariable Long id, Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName());
        GuestBook guestBook = guestRepository.findById(id).orElse(null);

        user.getGuestBooksLoved().add(guestBook);
        userRepository.save(user);
    }

    //UNLOVE
    @Secured("ROLE_USER")
    @PutMapping("/guest/unlove/{id}")
    void unLoveComment(@PathVariable Long id, Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName());
        List<GuestBook> newLovedComments = new ArrayList<>();
        for (GuestBook g : user.getGuestBooksLoved()){
            if (Objects.equals(g.getId(), id)) continue;
            newLovedComments.add(g);
        }

        user.getGuestBooksLoved().clear();
        user.getGuestBooksLoved().addAll(newLovedComments);
        userRepository.save(user);
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
        GuestBook guestBook = guestRepository.findById(id)
                .orElse(null);
        List<GuestBook> guestBooks = guestBook.getUser().getGuestBooks();
        List<GuestBook> newGuestBooks = new ArrayList<>();
        for(GuestBook book : guestBooks){
            if (book.getId()!=id){
                newGuestBooks.add(book);
            }
        }
        guestBook.getUser().setGuestBooks(newGuestBooks);

        List<User> userList = userRepository.findAll();
        for (User user: userList){
            List<GuestBook> guestBookList = user.getGuestBooksLoved();
            List<GuestBook> nGuestBooks = new ArrayList<>();
            for (GuestBook book: guestBookList){
                if (book.getId()!=id){
                    nGuestBooks.add(book);
                }
            }
            user.setGuestBooksLoved(nGuestBooks);
        }
        guestRepository.deleteById(id);
    }
}
