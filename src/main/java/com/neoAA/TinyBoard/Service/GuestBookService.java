package com.neoAA.TinyBoard.Service;

import com.neoAA.TinyBoard.model.GuestBook;
import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.GuestRepository;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class GuestBookService {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private UserRepository userRepository;

    public GuestBook save(String username, GuestBook guestBook){
        User user = userRepository.findByUsername(username);
        guestBook.setUser(user);
        guestBook.setTime(Timestamp.valueOf(LocalDateTime.now()));
        return guestRepository.save(guestBook);
    }

    public Long findAuthUser(Authentication authentication){
        //로그인하지 않은 사용자는 검증하지 않음
        if (authentication!=null){
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            return user.getId();
        }
        return -1L;
    }
}
