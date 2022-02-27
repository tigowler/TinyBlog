package com.neoAA.TinyBoard.controller.api;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    List<User> all(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/user/{id}")
    User one(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/user/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.getPost().clear();
                    user.getPost().addAll(newUser.getPost());
                    for (Post post : user.getPost()){
                        post.setUser(user);
                    }
                    return userRepository.save(user);
                })
                .orElseGet(()->{
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
